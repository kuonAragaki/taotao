package com.shopping.sso.service;

import com.shopping.common.pojo.ShopResult;

/**
 * 说明：用户登录业务接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 10:50
 */
public interface UserLoginService {
    public ShopResult login(String username,String password);

    public ShopResult getUserByToken(String token);

    public ShopResult logout(String token);
}
