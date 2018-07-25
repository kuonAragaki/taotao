package com.shopping.coupon.service;

import com.shopping.common.pojo.ShopResult;
import com.shopping.order.service.pojo.OrderInfo;
import com.shopping.pojo.TbCoupon;


import java.util.List;

/**
 * 说明：优惠券业务层接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/25 15:30
 */
public interface CouponService {
    /**
     * 说明：查询所有优惠券
     */
    List<TbCoupon> getAllTbCoupons();

    /**
     * 说明：根据用户id查找优惠券
     */
    List<TbCoupon> getTbCouponsByUserId(Long userId);

    /**
     * 说明：领取优惠券
     */
    ShopResult receiveTbCoupon(Long userId,Long couponId);

    /**
     * 说明：使用优惠券
     */
    void useTbCoupon(Long userId,Long couponId, OrderInfo orderInfo,String orderOriginalAmount);

    /**
     * 说明：支付成功后的回调
     */
    void updateLog(String orderId);
}
