package com.shopping.search.service;

import com.shopping.common.pojo.ShopResult;

/**
 * 说明：将商品数据导入索引库，业务层接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/7 20:53
 */
public interface SearchItemService {
    public ShopResult importAllItemToIndex();
}
