package com.shopping.service;

import com.shopping.common.pojo.EUDataGridResult;
import com.shopping.common.pojo.ShopResult;
import com.shopping.pojo.TbItem;
import com.shopping.pojo.TbItemDesc;
import com.shopping.pojo.TbItemParamItem;

/**
 * 说明：商品业务接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/4 19:22
 */
public interface ItemService {
    TbItem getItemById(long itemId);

    EUDataGridResult getItemList(int page, int rows);

    ShopResult createItem(TbItem item,String desc) throws Exception;

    ShopResult deleteItem(Long[] ids) throws Exception;

    ShopResult updateItem(TbItem item,String desc) throws Exception;

    TbItemDesc getDescById(long itemId);

    TbItemParamItem getParamItemById(long itemId);

}
