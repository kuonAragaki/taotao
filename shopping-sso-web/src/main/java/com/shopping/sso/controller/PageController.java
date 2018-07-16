package com.shopping.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 说明：注册、登录页面跳转
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 14:46
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/register")
    public String showRegister(){
        return "register";
    }

    @RequestMapping("login")
    public String showLogin(String redirect, Model model){
        model.addAttribute("redirect",redirect);
        return "login";
    }
}
