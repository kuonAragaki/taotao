package com.shopping.content.service;

import com.shopping.common.pojo.EUDataGridResult;
import com.shopping.common.pojo.ShopResult;
import com.shopping.pojo.TbContent;

import java.util.List;

/**
 * 说明：内容管理接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/6 15:50
 */
public interface ContentService {
    ShopResult insertContent(TbContent content);
    EUDataGridResult getContentList(int page,int rows,long contentCid);
    String getContentList();
    List<TbContent> getContentList(long contentCid);
}
