package com.shopping.controller;

import com.shopping.common.pojo.EUTreeNode;
import com.shopping.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 说明：商品分类管理控制层
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/5 16:08
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("list")
    @ResponseBody
    public List<EUTreeNode> getCatList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<EUTreeNode> list = itemCatService.getCatList(parentId);
        return list;
    }
}
