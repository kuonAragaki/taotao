package com.shopping.sso.service;

import com.shopping.common.pojo.ShopResult;
import com.shopping.pojo.TbUser;

/**
 * 说明：用户注册业务层接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 8:31
 */
public interface UserRegisterService {
    public ShopResult checkUserInfo(String param,int type);

    public ShopResult createUser(TbUser user);
}
