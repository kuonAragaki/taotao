package com.shopping.service;

import com.shopping.common.pojo.EUTreeNode;

import java.util.List;

/**
 * 说明：商品分类业务接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/5 14:53
 */
public interface ItemCatService {
    List<EUTreeNode> getCatList(long parentId);
}
