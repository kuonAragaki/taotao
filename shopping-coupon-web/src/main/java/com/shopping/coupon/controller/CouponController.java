package com.shopping.coupon.controller;

import com.alibaba.fastjson.JSON;
import com.shopping.common.pojo.ShopResult;
import com.shopping.coupon.service.CouponService;
import com.shopping.pojo.TbCoupon;
import com.shopping.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 说明：优惠券系统控制层
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/25 20:37
 */
@Controller
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 说明：查看所有优惠券列表
     */
    @RequestMapping("/couponList")
    public String couponList(Model model){
        //查询所有优惠券
        List<TbCoupon> coupons = couponService.getAllTbCoupons();
        //存入model
        model.addAttribute("coupons",coupons);
        //跳转到优惠券列表页面
        return "coupon-list";
    }


    /**
     * 说明：领取优惠券
     */
    @RequestMapping("/coupon/receive")
    @ResponseBody
    public ShopResult receive(Long couponId ,HttpServletRequest request){
        //取用户id
        TbUser user = (TbUser) request.getAttribute("user");
        //领取操作
        ShopResult result = couponService.receiveTbCoupon(user.getId(), couponId);
        //返回结果
        return result;
    }
}
