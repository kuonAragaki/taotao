package com.shopping.content.service;

import com.shopping.common.pojo.EUTreeNode;
import com.shopping.common.pojo.ShopResult;

import java.util.List;

/**
 * 说明：内容分类管理业务层接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/6 11:22
 */
public interface ContentCategoryService {
    List<EUTreeNode> getCategoryList(long parentId);
    ShopResult insertContentCategory(long parentId,String name);
}
