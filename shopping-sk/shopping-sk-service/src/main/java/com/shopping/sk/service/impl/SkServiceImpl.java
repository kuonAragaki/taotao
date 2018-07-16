package com.shopping.sk.service.impl;

import com.alibaba.fastjson.JSON;
import com.shopping.common.pojo.ShopResult;
import com.shopping.mapper.TbItemMapper;
import com.shopping.pojo.TbItem;
import com.shopping.pojo.TbOrderItem;
import com.shopping.pojo.TbOrderShipping;
import com.shopping.sk.service.SkService;
import com.shopping.sk.service.jedis.JedisClient;
import com.shopping.sk.service.pojo.OrderInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 说明：订单业务层实现类
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 22:20
 */
@Service
public class SkServiceImpl implements SkService {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${SK_NUMBER}")
    private Long SK_NUMBER;

    @Value("${SK_ID}")
    private Long SK_ID;

    @Value("${SK_PRICE}")
    private Long SK_PRICE;

    private Long oldPrice;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    @Value("${ORDER_ID_INIT}")
    private String ORDER_ID_INIT;
    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;

    @Scheduled(cron = "0 42 14 * * ?")
    @Override
    public void startSK() {
        System.out.println("-----开启秒杀-----");
        //向redis中存入商品件数
        jedisClient.set("sk:"+SK_ID+":number",SK_NUMBER.toString());
        //查询商品
        TbItem item = itemMapper.selectByPrimaryKey(SK_ID);
        //保存商品原价格
        oldPrice = item.getPrice();
        System.out.println("oldPrice====="+oldPrice);
        //修改商品价格
        item.setPrice(SK_PRICE);
        //删除商品详情页面缓存
        jedisClient.del(REDIS_ITEM_KEY+":"+SK_ID+":BASE");
        jedisClient.del(REDIS_ITEM_KEY+":"+SK_ID+":DESC");
        //存入数据库
        itemMapper.updateByPrimaryKey(item);
    }

    @Scheduled(cron = "0 40 15 * * ?")
    @Override
    public void endSK() {
        System.out.println("-----结束秒杀-----");
        //删除redis中的商品件数
        jedisClient.del("sk:"+SK_ID+":number");
        //开启监视
        jedisClient.watch("sk:"+SK_ID+":number");
        //查询商品
        TbItem item = itemMapper.selectByPrimaryKey(SK_ID);
        //修改商品价格
        System.out.println("oldPrice-----"+oldPrice);
        item.setPrice(oldPrice);
        //删除商品详情页面缓存
        jedisClient.del(REDIS_ITEM_KEY+":"+SK_ID+":BASE");
        jedisClient.del(REDIS_ITEM_KEY+":"+SK_ID+":DESC");
        //存入数据库
        itemMapper.updateByPrimaryKey(item);
    }

    @Override
    public ShopResult createOrder(OrderInfo orderInfo) {
        //查询redis中的商品件数
        String number = jedisClient.get("sk:" + SK_ID + ":number");
        //判断是否有商品
        if(StringUtils.isBlank(number)){
            //秒杀未开始或者已经结束
            return ShopResult.ok("1");
        }else if(Integer.parseInt(number)<=0){
            //查询商品
            TbItem item = itemMapper.selectByPrimaryKey(SK_ID);
            //修改商品价格
            System.out.println("oldPrice-----"+oldPrice);
            item.setPrice(oldPrice);
            //删除商品详情页面缓存
            jedisClient.del(REDIS_ITEM_KEY+":"+SK_ID+":BASE");
            jedisClient.del(REDIS_ITEM_KEY+":"+SK_ID+":DESC");
            //存入数据库
            itemMapper.updateByPrimaryKey(item);
            return ShopResult.ok("2");
        }
        //商品数减一
        long decr = jedisClient.decr("sk:" + SK_ID + ":number");
        System.out.println("商品剩余件数："+decr);
        //生成一个订单号，使用Redis的incr命令来生成
        // ORDER_GEN_KEY就是对应订单号生成的key
        // 判断订单号生成的key是否存在
        if(!jedisClient.exists(ORDER_GEN_KEY)){
            //如果不存在，要设置初始值
            jedisClient.set(ORDER_GEN_KEY,ORDER_ID_INIT);
        }
        String orderId = String.valueOf(jedisClient.incr(ORDER_GEN_KEY));
        //向订单表插入数据
        orderInfo.setOrderId(orderId);
        //免邮费
        orderInfo.setPostFee("0");
        //订单状态
        // 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        Date date = new Date();
        orderInfo.setCreateTime(date);
        orderInfo.setUpdateTime(date);
        //向redis存入数据
        jedisClient.lpush("sk:"+SK_ID+":"+orderId+":orderInfo", JSON.toJSONString(orderInfo));
        //向订单明细表插入数据
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem orderItem : orderItems) {
            //生成一个订单明细表的主键
            Long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            orderItem.setId(orderDetailId.toString());
            //设置订单id
            orderItem.setOrderId(orderId);
            //向redis存入数据
            jedisClient.lpush("sk:"+SK_ID+":"+orderId+":orderItem", JSON.toJSONString(orderItem));
        }
        //向订单物流信息表插入数据
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        //向redis存入数据
        jedisClient.lpush("sk:"+SK_ID+":"+orderId+":orderShipping", JSON.toJSONString(orderShipping));
        //返回订单号
        return ShopResult.ok(orderId);
    }

}
