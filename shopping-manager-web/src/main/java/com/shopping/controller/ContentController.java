package com.shopping.controller;

import com.shopping.common.pojo.EUDataGridResult;
import com.shopping.common.pojo.ShopResult;
import com.shopping.content.service.ContentService;
import com.shopping.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 说明：内容管理控制层
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/6 16:02
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getContentList(int page, int rows ,Long contentCategoryId){
        return contentService.getContentList(page ,rows ,contentCategoryId);
    }

    @RequestMapping("/save")
    @ResponseBody
    public ShopResult insertContent(TbContent content){
        System.out.println("title-controller====="+content.getTitle());
        ShopResult result = contentService.insertContent(content);
        return result;
    }
}
