package com.shopping.sk.controller;

import com.shopping.common.pojo.ShopResult;
import com.shopping.pojo.TbItem;
import com.shopping.pojo.TbSeckill;
import com.shopping.pojo.TbUser;
import com.shopping.service.ItemService;
import com.shopping.sk.service.SkService;
import com.shopping.sk.service.pojo.OrderInfo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：订单确认页面控制层
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 21:33
 */
@Controller
public class SkController {

    @Value("${COOKIE_CART_KEY}")
    private String COOKIE_CART_KEY;

    @Autowired
    private SkService skService;

    @Autowired
    private ItemService itemService;

    /**
     * 说明：生成订单详情
     */
    @RequestMapping("/sk/order/order-cart/{itemId}")
    public String showOrderCart(@PathVariable Long itemId,HttpServletRequest request){
        //查询商品
        TbItem item = itemService.getItemById(itemId);
        item.setNum(1);
        List<TbItem> cartList = new ArrayList<>();
        cartList.add(item);
        //取用户id
        TbUser user = (TbUser)request.getAttribute("user");
        System.out.println(user.getUsername());
        // 根据用户id来查询收货地址列表，现在数据库里面没有收货地址列表这个表，所以只能是使用静态数据
        // 从数据库中查询支付方式列表
        // 传递给页面
        request.setAttribute("cartList",cartList);
        //返回一个逻辑视图
        return "order-cart";
    }

    @RequestMapping(value = "/sk/order/create",method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, Model model, HttpServletRequest request){
        //取用户id
        TbUser user = (TbUser) request.getAttribute("user");
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        //调用服务生成订单
        ShopResult result = skService.createOrder(orderInfo);
        System.out.println(result.getData());
        String data = (String) result.getData();
        if(data.equals("1")){
            model.addAttribute("message","对不起，现在不是秒杀时间");
            return "error/exception";
        }else if(data.equals("2")){
            model.addAttribute("message","对不起，商品已抢完");
            return "error/exception";
        }
        //取订单号并传递给页面
        String orderId = result.getData().toString();
        model.addAttribute("orderId",orderId);
        model.addAttribute("payment",orderInfo.getPayment());
        //预计送达时间是三天后
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(3);
        model.addAttribute("date",dateTime.toString("yyyy-MM-dd"));
        //返回逻辑视图
        return "success";
    }

    /**
     * 说明：获取秒杀列表
     */
    @RequestMapping("/skList")
    public String skList(Model model){
        List<Map<String,Object>> list = new ArrayList<>();
        List<TbSeckill> skList = skService.getSKList();
        //循环查询商品信息
        if(skList != null && skList.size() > 0){
            for (int i = 0; i < skList.size(); i++) {
                TbSeckill seckill =  skList.get(i);
                TbItem item = itemService.getItemById(seckill.getItemId());
                Map<String, Object> map = new HashMap<>();
                map.put("seckill",seckill);
                map.put("item",item);
                list.add(map);
            }
        }
        model.addAttribute("list",list);
        return "sk-list";
    }

}
