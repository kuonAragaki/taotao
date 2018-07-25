package com.shopping.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.shopping.common.pojo.ShopResult;
import com.shopping.common.utils.CookieUtils;
import com.shopping.order.service.OrderService;
import com.shopping.order.service.pojo.OrderInfo;
import com.shopping.pojo.TbItem;
import com.shopping.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：订单确认页面控制层
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 21:33
 */
@Controller
public class OrderController {

    @Value("${COOKIE_CART_KEY}")
    private String COOKIE_CART_KEY;

    @Autowired
    private OrderService orderService;

    /**
     * 说明：从Cookie中取出购物车列表
     */
    private List<TbItem> getCartList(HttpServletRequest request){
        //使用CookieUtils取购物车列表
        String json = CookieUtils.getCookieValue(request, COOKIE_CART_KEY, true);
        //判断Cookie中是否有值
        if(StringUtils.isBlank(json)){
            //没有值就返回一个空List
            return new ArrayList();
        }
        //把json转换成List对象
        List<TbItem> list = JSONObject.parseArray(json, TbItem.class);
        return list;
    }

    /**
     * 说明：生成订单详情
     */
    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request){
        //取购物车商品列表
        List<TbItem> cartList = getCartList(request);
        //取用户id
        TbUser user = (TbUser)request.getAttribute("user");
        System.out.println(user.getUsername());
        // 根据用户id来查询收货地址列表，现在数据库里面没有收货地址列表这个表，所以只能是使用静态数据
        // 从数据库中查询支付方式列表
        // 传递给页面
        request.setAttribute("cartList",cartList);
        //返回一个逻辑视图
        return "order-cart";
    }

    @RequestMapping(value = "/order/create",method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, Model model,HttpServletRequest request){
        //取用户id
        TbUser user = (TbUser) request.getAttribute("user");
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        //调用服务生成订单
        ShopResult result = orderService.createOrder(orderInfo);
        //取订单号并传递给页面
        String orderId = result.getData().toString();
        model.addAttribute("orderId",orderId);
        model.addAttribute("payment",orderInfo.getPayment());

        //预计送达时间是三天后
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(3);
        model.addAttribute("date",dateTime.toString("yyyy-MM-dd"));
        //返回逻辑视图
        if(orderInfo.getPaymentType() == 4){
            //payid为支付方式，1代表货到付款，4代表在线支付
            //payid为4时进入付款页面
            return "pay";
        }
        //货到付款时直接返回成功页面
        return "success";
    }

}
