package com.shopping.coupon.service.impl;

import com.shopping.common.pojo.ShopResult;
import com.shopping.coupon.service.CouponService;
import com.shopping.mapper.TbCouponLogsMapper;
import com.shopping.mapper.TbCouponMapper;
import com.shopping.mapper.TbCouponReceiveMapper;
import com.shopping.order.service.pojo.OrderInfo;
import com.shopping.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 说明：优惠券业务层实现类
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/25 19:34
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private TbCouponMapper tbCouponMapper;

    @Autowired
    private TbCouponReceiveMapper tbCouponReceiveMapper;

    @Autowired
    private TbCouponLogsMapper tbCouponLogsMapper;

    /**
     * 说明：查询所有优惠券
     */
    @Override
    public List<TbCoupon> getAllTbCoupons() {
        return tbCouponMapper.selectByExample(null);
    }

    /**
     * 说明：根据用户id查找可用优惠券
     */
    @Override
    public List<TbCoupon> getTbCouponsByUserId(Long userId) {
        //添加查询条件
        TbCouponReceiveExample example = new TbCouponReceiveExample();
        TbCouponReceiveExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        //优惠券状态，1为已使用，0为已领取未使用，-1为已过期
        criteria.andStatusEqualTo(0);
        //执行查询
        List<TbCouponReceive> list = tbCouponReceiveMapper.selectByExample(example);
        //创建coupon列表
        List<TbCoupon> coupons = new ArrayList<>();
        //遍历用户已领取优惠券列表，查询优惠券详情
        for (int i = 0; i < list.size(); i++) {
            TbCouponReceive tbCouponReceive =  list.get(i);
            TbCoupon tbCoupon = tbCouponMapper.selectByPrimaryKey(tbCouponReceive.getCouponId());
            //将查询到的优惠券加入列表
            coupons.add(tbCoupon);
        }
        //返回结果
        return coupons;
    }

    /**
     * 说明：领取优惠券
     */
    @Override
    public ShopResult receiveTbCoupon(Long userId, Long couponId) {
        //查询已领取优惠券列表，判断用户是否能领取该优惠券
        //创建查询条件
        TbCouponReceiveExample example = new TbCouponReceiveExample();
        TbCouponReceiveExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andCouponIdEqualTo(couponId);
        //执行查询
        List<TbCouponReceive> list = tbCouponReceiveMapper.selectByExample(example);
        //判断集合中是否有对象，若有，则返回无法领取，没有则执行领取操作
        if(list != null && list.size() > 0){
            return ShopResult.ok("对不起，您已领取过该优惠券，无法重复领取！");
        }
        //查询优惠券详情
        TbCoupon coupon = tbCouponMapper.selectByPrimaryKey(couponId);
        //创建领取记录对象
        TbCouponReceive receive = new TbCouponReceive();
        receive.setCouponId(couponId);
        receive.setCouponMoney(coupon.getMoney());
        receive.setCreateTime(new Date());
        receive.setFullMoney(coupon.getFullMoney());
        //状态，1为已使用，0为已领取未使用，-1为已过期
        receive.setStatus(0);
        receive.setUserId(userId);
        //加入数据库
        tbCouponReceiveMapper.insert(receive);
        //返回成功信息
        return ShopResult.ok("领取成功！快去购物吧~");
    }

    /**
     * 说明：使用优惠券
     */
    @Override
    public void useTbCoupon(Long userId, Long couponId, OrderInfo orderInfo,String orderOriginalAmount) {
        //查找优惠券领取记录
        //创建查询条件
        TbCouponReceiveExample example = new TbCouponReceiveExample();
        TbCouponReceiveExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andCouponIdEqualTo(couponId);
        //执行查询
        List<TbCouponReceive> list = tbCouponReceiveMapper.selectByExample(example);
        //判断集合中是否有对象
        if(list != null && list.size() > 0){
            //修改优惠券使用状态：1为已使用，0为已领取未使用，-1为已过期
            TbCouponReceive receive = list.get(0);
            receive.setStatus(1);
            //存入数据库
            tbCouponReceiveMapper.updateByPrimaryKey(receive);
            //创建优惠券消费记录
            TbCouponLogs log = new TbCouponLogs();
            log.setCouponAmount(receive.getCouponMoney());
            log.setCouponReceiveId(receive.getId());
            log.setCreateTime(receive.getCreateTime());
            log.setOrderOriginalAmount(orderOriginalAmount);
            log.setOrderFinalAmount(orderInfo.getPayment());
            log.setOrderId(orderInfo.getOrderId());
            log.setUserId(userId);
            //日志状态: 默认为0，支付回调成功后为1
            log.setStatus(0);
            //插入日志表
            tbCouponLogsMapper.insert(log);
        }

    }

    /**
     * 说明：支付成功后的回调
     */
    @Override
    public void updateLog(String orderId) {
        //创建查询条件
        TbCouponLogsExample example = new TbCouponLogsExample();
        TbCouponLogsExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        //取出日志记录
        List<TbCouponLogs> list = tbCouponLogsMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            //修改日志状态: 默认为0，支付回调成功后为1
            TbCouponLogs log = list.get(0);
            log.setStatus(1);
            //更新数据库
            tbCouponLogsMapper.updateByPrimaryKey(log);
        }
    }
}
