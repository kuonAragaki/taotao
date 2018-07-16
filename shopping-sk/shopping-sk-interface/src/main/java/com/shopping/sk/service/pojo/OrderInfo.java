package com.shopping.sk.service.pojo;

import com.shopping.pojo.TbOrder;
import com.shopping.pojo.TbOrderItem;
import com.shopping.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：订单详情页面视图对象
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 22:15
 */
public class OrderInfo extends TbOrder implements Serializable {
    //商品列表
    private List<TbOrderItem> orderItems;
    //收货地址
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
