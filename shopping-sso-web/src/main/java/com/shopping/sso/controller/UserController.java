package com.shopping.sso.controller;

import com.shopping.common.pojo.ShopResult;
import com.shopping.common.utils.CookieUtils;
import com.shopping.common.utils.ExceptionUtil;
import com.shopping.pojo.TbUser;
import com.shopping.sso.service.UserLoginService;
import com.shopping.sso.service.UserRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 说明：用户系统控制层
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 9:13
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private UserLoginService userLoginService;

    @Value("${COOKIE_TOKEN_KEY}")
    private String COOKIE_TOKEN_KEY;

    /**
     * 说明：检查数据合法性
     */
    @RequestMapping(value = "check/{param}/{type}",method = RequestMethod.GET)
    @ResponseBody
    public ShopResult checkUserInfo(@PathVariable String param,@PathVariable Integer type){
        ShopResult result = userRegisterService.checkUserInfo(param, type);
        return result;
    }

    /**
     * 说明：创建用户
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ShopResult createUser(TbUser user){
        try {
            ShopResult result = userRegisterService.createUser(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ShopResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 说明：登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ShopResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response){
        ShopResult shopResult = userLoginService.login(username, password);
        if(shopResult.getStatus() == 200){
            //取出token
            String token = shopResult.getData().toString();
            //在返回结果之前，设置cookie(即将token写入cookie)
            //1.cookie怎么跨域？
            //2.如何设置cookie的有效期？
            CookieUtils.setCookie(request,response,COOKIE_TOKEN_KEY,token);
        }
        //返回结果
        return  shopResult;
    }

    /**
     * 说明：用token查询用户信息
     */
    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token,String callback){
        ShopResult result = null;
        try {
            result = userLoginService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = ShopResult.build(500,ExceptionUtil.getStackTrace(e));
        }

        //判断是否为jsonp调用
        if(StringUtils.isBlank(callback)){
            return result;
        }else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
    }

    @RequestMapping("/logout/{token}")
    @ResponseBody
    public ShopResult logout(@PathVariable String token){
        ShopResult result = null;
        try {
            result = userLoginService.logout(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = ShopResult.build(500,ExceptionUtil.getStackTrace(e));
        }
        return result;
    }
}
