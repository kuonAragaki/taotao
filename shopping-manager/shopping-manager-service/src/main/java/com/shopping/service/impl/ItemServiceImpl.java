package com.shopping.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.common.pojo.EUDataGridResult;
import com.shopping.common.pojo.ShopResult;
import com.shopping.common.utils.IDUtils;
import com.shopping.mapper.TbItemDescMapper;
import com.shopping.mapper.TbItemMapper;
import com.shopping.mapper.TbItemParamItemMapper;
import com.shopping.pojo.TbItem;
import com.shopping.pojo.TbItemDesc;
import com.shopping.pojo.TbItemExample;
import com.shopping.pojo.TbItemParamItem;
import com.shopping.service.ItemService;
import com.shopping.service.jedis.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * 说明：商品业务实现类
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/4 19:26
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "topicDestination")
    private Topic topicDestination;

    @Autowired
    private TbItemParamItemMapper paramItemMapper;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    @Autowired
    private JedisClient jedisClient;

    /**
     * 说明：根据id查找商品
     */
    @Override
    public TbItem getItemById(long itemId) {
        //先查询缓存
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":BASE");
            if(StringUtils.isNotBlank(json)){
                TbItem item = JSONObject.parseObject(json, TbItem.class);
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果缓存中没有命中，name查询数据库
        TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
        //添加到缓存
        try {
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":BASE",JSON.toJSONString(item));
            //设置过期时间
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":BASE",REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * 说明：商品列表查询
     */
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();
        //开始分页
        PageHelper.startPage(page,rows);
        List<TbItem> list = tbItemMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //设置记录总条数
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 说明：新增商品
     */
    @Override
    public ShopResult createItem(TbItem item, String desc) throws Exception {
        //生成商品id
        final long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //商品状态，1-正常，2-下架,3-删除
        item.setStatus((byte) 1);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //插入到商品表
        tbItemMapper.insert(item);
        //商品描述
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        //插入商品描述
        tbItemDescMapper.insert(itemDesc);

        //商品添加完成后发送一个MQ消息
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                //创建一个消息对象
                //要在匿名内部类访问局部变量itemId,用final修饰
                TextMessage message = session.createTextMessage(itemId + "");
                return message;
            }
        });
        return ShopResult.ok();
    }

    /**
     * 说明：删除商品
     */
    @Override
    public ShopResult deleteItem(Long[] ids) throws Exception {
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            tbItemMapper.deleteByPrimaryKey(id);
            tbItemDescMapper.deleteByPrimaryKey(id);
        }
        return ShopResult.ok();
    }

    /**
     * 说明：编辑商品
     */
    @Override
    public ShopResult updateItem(TbItem item, String desc) throws Exception {
        final Long itemId = item.getId();
        //获取当前日期作为修改日期
        Date date = new Date();
        item.setUpdated(date);
        tbItemMapper.updateByPrimaryKeySelective(item);
        //更新商品描述表
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setUpdated(date);
        itemDesc.setItemDesc(desc);
        tbItemDescMapper.updateByPrimaryKeySelective(itemDesc);
        //商品修改完成后发送一个MQ消息
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                //创建一个消息对象
                //要在匿名内部类访问局部变量itemId,用final修饰
                TextMessage message = session.createTextMessage(itemId + "");
                return message;
            }
        });
        return ShopResult.ok();
    }

    @Override
    public TbItemDesc getDescById(long itemId) {
        //先查询缓存
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":DESC");
            if(StringUtils.isNotBlank(json)){
                TbItemDesc tbItemDesc = JSONObject.parseObject(json,TbItemDesc.class);
                return tbItemDesc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果缓存中没有命中，name查询数据库
        TbItemDesc ItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        //添加到缓存
        try {
            jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":DESC", JSON.toJSONString(ItemDesc));
            //设置过期时间
            jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":DESC",REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ItemDesc;
    }


    @Override
    public TbItemParamItem getParamItemById(long itemId) {
            return paramItemMapper.selectByPrimaryKey(itemId);
        }
}
