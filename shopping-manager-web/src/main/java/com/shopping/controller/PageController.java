package com.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 说明：页面跳转controller
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/5 8:10
 */
@Controller
public class PageController {
    /**
     * 说明：打开首页
     */
    @RequestMapping("/")
    public String showIndex(){return "index";}

    /**
     * 说明：打开其他页面
     */
    @RequestMapping("/{page}")
    public String showpage(@PathVariable String page){
        return page;
    }
}
