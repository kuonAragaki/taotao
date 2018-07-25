package com.shopping.coupon.service;

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
    List<TbCoupon> getTbCouponsByUserId();

}
