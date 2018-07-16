package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.shopping.common.pojo.EUDataGridResult;
import com.shopping.common.pojo.ShopResult;
import com.shopping.pojo.TbItem;
import com.shopping.pojo.TbItemDesc;
import com.shopping.pojo.TbItemParamItem;
import com.shopping.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 说明：控制层
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/4 20:13
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        return  itemService.getItemById(itemId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page,Integer rows){
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ShopResult createItem(TbItem item,String desc) throws  Exception{
        ShopResult result = itemService.createItem(item, desc);
        return result;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ShopResult deleteItem(Long[] ids) throws  Exception{
        ShopResult result = itemService.deleteItem(ids);
        return result;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public ShopResult editItem(TbItem item, String desc) throws  Exception{
        ShopResult result = itemService.updateItem(item, desc);
        return result;
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId) throws  Exception{
//        System.out.println("id====="+itemId);
        TbItemDesc desc = itemService.getDescById(itemId);
        String json = JSON.toJSONString(desc);
        return json;
    }

    @RequestMapping("/param/item/query/{itemId}")
    @ResponseBody
    public String getParamItem(@PathVariable Long itemId) throws  Exception{
        TbItemParamItem paramItem = itemService.getParamItemById(itemId);
        String json = JSON.toJSONString(paramItem);
        return json;
    }
}
