package com.shopping.controller;

import com.shopping.common.pojo.EUTreeNode;
import com.shopping.common.pojo.ShopResult;
import com.shopping.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 说明：内容分类管理
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/6 14:34
 */
@Controller
@RequestMapping("/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
        return list;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ShopResult createContentCategory(Long parentId,String name){
        ShopResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }

}
