package com.shopping.order.service;

import com.shopping.common.pojo.ShopResult;
import com.shopping.order.service.pojo.OrderInfo;

/**
 * 说明：订单业务层接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 22:15
 */
public interface OrderService {
    ShopResult createOrder(OrderInfo orderInfo);

    void updateOrderPay(String orderId);
}
