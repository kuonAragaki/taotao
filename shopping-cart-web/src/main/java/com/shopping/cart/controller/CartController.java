package com.shopping.cart.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shopping.common.pojo.ShopResult;
import com.shopping.common.utils.CookieUtils;
import com.shopping.pojo.TbItem;
import com.shopping.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：购物车控制层
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 19:04
 */
@Controller
public class CartController {
    @Autowired
    private ItemService itemService;

    @Value("${COOKIE_TT_CART}")
    private String COOKIE_TT_CART;

    @Value("${COOKIE_CART_EXPIRE}")
    private Integer COOKIE_CART_EXPIRE;

    /**
     * 说明：从Cookie中取出购物车列表
     */
    private List<TbItem> getCartList(HttpServletRequest request){
        //使用CookieUtils取购物车列表
        String json = CookieUtils.getCookieValue(request, COOKIE_TT_CART, true);
        //判断Cookie中是否有值
        if(StringUtils.isBlank(json)){
            //没有值就返回一个空List
            return new ArrayList();
        }
        //把json转换成List对象
        List<TbItem> list = JSONObject.parseArray(json, TbItem.class);
        return list;
    }

    /**
     * 说明：向购物车添加商品
     */
    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response){
        //先从Cookie中查询购物车列表
        List<TbItem> cartList = getCartList(request);
        //判断购物车列表中是否有此商品
        boolean flag = false;
        for (TbItem tbItem : cartList) {
            /**
             * 由于tbItem的ID与参数中的itemId都是包装类型的Long，要比较是否相等不能用==
             * 那样比较的是对象的地址而不是值，为了让它们比较的是值，可以使用.longValue来获取值
             */
            if(tbItem.getId() == itemId.longValue()){
                //如果购物车列表中存在此商品，数量要相加
                tbItem.setNum(tbItem.getNum() + num);//可以用商品的库存字段作为购物车商品数量
                flag = true;
                break;
            }
        }
        if(!flag){
            //如果没有，则根据商品id查询商品信息，调用商品服务实现
            TbItem tbItem = itemService.getItemById(itemId);
            //设置商品数量
            tbItem.setNum(num);
            //取一张图片
            String image = tbItem.getImage();
            if(StringUtils.isNotBlank(image)){
                tbItem.setImage(image.split(",")[0]);
            }
            //添加到购物车列表
            cartList.add(tbItem);
        }
        //把这个购物车写入Cookie
        CookieUtils.setCookie(request,response,COOKIE_TT_CART, JSON.toJSONString(cartList),COOKIE_CART_EXPIRE,true);
        //返回添加成功页面
        return "cartSuccess";
    }

    /**
     * 说明：显示购物车列表
     */
    @RequestMapping("/cart/cart")
    public String showCartList(HttpServletRequest request){
        List<TbItem> list = getCartList(request);
        request.setAttribute("cartList",list);
        return "cart";
    }

    /**
     * 说明：更新购物车商品数量
     */
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public ShopResult updateNum(@PathVariable Long itemId, @PathVariable Integer num,HttpServletRequest request,HttpServletResponse response){
        //取购物车商品列表
        List<TbItem> cartList = getCartList(request);
        //遍历商品列表找到商品
        for (TbItem tbItem : cartList) {
            if(tbItem.getId() == itemId.longValue()){
                //更新商品数量
                tbItem.setNum(num);
                break;
            }
        }
        //写入Cookie
        CookieUtils.setCookie(request,response,COOKIE_TT_CART,JSON.toJSONString(cartList),COOKIE_CART_EXPIRE,true);
        //返回结果
        return ShopResult.ok();
    }

    /**
     * 说明：删除购物车中商品
     */
    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId,HttpServletRequest request,HttpServletResponse response){
        //取购物车商品列表
        List<TbItem> cartList = getCartList(request);
        //找到对应商品
        for (TbItem tbItem : cartList) {
            if(tbItem.getId().longValue() == itemId){
                //删除商品
                cartList.remove(tbItem);
                //退出循环
                break;
            }
        }
        //写入Cookie
        CookieUtils.setCookie(request,response,COOKIE_TT_CART,JSON.toJSONString(cartList),COOKIE_CART_EXPIRE,true);
        //返回逻辑视图，需要做redirect跳转
        return "redirect:/cart/cart.html";
    }

}
