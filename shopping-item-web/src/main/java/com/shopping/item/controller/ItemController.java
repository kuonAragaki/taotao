package com.shopping.item.controller;

import com.shopping.item.pojo.Item;
import com.shopping.pojo.TbItem;
import com.shopping.pojo.TbItemDesc;
import com.shopping.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品详情页面展示处理
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.ghh.cn</p>
 * @version 1.0
 */
@Controller
public class ItemController
{

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model) {
        // 根据商品id查询商品基本信息
        TbItem tbItem = itemService.getItemById(itemId);
        // 使用TbItem来初始化Item对象
        Item item = new Item(tbItem);
        // 根据商品id查询商品描述
        TbItemDesc tbItemDesc = itemService.getDescById(itemId);
        item.setNum(1);
        // 传递给页面
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", tbItemDesc);
        // 返回逻辑视图
        return "item";
    }

    @RequestMapping("/item/sk/{itemId}")
    public String showSKItemInfo(@PathVariable Long itemId, Model model) {
        // 根据商品id查询商品基本信息
        TbItem tbItem = itemService.getItemById(itemId);
        // 使用TbItem来初始化Item对象
        Item item = new Item(tbItem);
        // 根据商品id查询商品描述
        TbItemDesc tbItemDesc = itemService.getDescById(itemId);
        //设置秒杀商品件数
        item.setNum(3);
        // 传递给页面
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", tbItemDesc);
        // 返回逻辑视图
        return "item-sk";
    }

}