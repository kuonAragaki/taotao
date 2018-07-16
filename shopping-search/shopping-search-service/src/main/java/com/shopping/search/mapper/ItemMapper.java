package com.shopping.search.mapper;

import com.shopping.common.pojo.SearchItem;

import java.util.List;

/**
 * 说明：搜索业务接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/7 19:52
 */
public interface ItemMapper {

    List<SearchItem> getItemList();

    SearchItem getItemById(long id);
}
