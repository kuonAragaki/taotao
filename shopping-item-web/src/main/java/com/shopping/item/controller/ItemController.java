package com.shopping.item.controller;

import com.shopping.item.pojo.Item;
import com.shopping.pojo.TbItem;
import com.shopping.pojo.TbItemDesc;
import com.shopping.pojo.TbSeckill;
import com.shopping.service.ItemService;
import com.shopping.sk.service.SkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

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

    @Autowired
    private SkService skService;

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
        //获取服务器当前时间
        Date date = new Date();
        // 根据商品id查询商品秒杀信息
        TbSeckill sk = skService.getSKById(itemId);
        //取时间
        long startTime = sk.getStartTime().getTime();
        long endTime = sk.getEndTime().getTime();
        System.out.println(startTime);
        // 传递给页面
        model.addAttribute("endTime",endTime);
        model.addAttribute("startTime",startTime);
        model.addAttribute("sk",sk);
        model.addAttribute("now",date.getTime());
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", tbItemDesc);
        // 返回逻辑视图
        return "item-sk";
    }



}