package com.shopping.sk.service;

import com.shopping.common.pojo.ShopResult;
import com.shopping.pojo.TbSeckill;
import com.shopping.sk.service.pojo.OrderInfo;

import java.util.List;

/**
 * 说明：订单业务层接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 22:15
 */
public interface SkService {
    ShopResult createOrder(OrderInfo orderInfo);

    void startSK();

    void endSK();

    List<TbSeckill> getSKList();

    TbSeckill getSKById(Long itemId);
}
