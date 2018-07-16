package com.shopping.sso.service.impl;

import com.shopping.common.pojo.ShopResult;
import com.shopping.common.utils.IDUtils;
import com.shopping.mapper.TbUserMapper;
import com.shopping.pojo.TbUser;
import com.shopping.pojo.TbUserExample;
import com.shopping.sso.service.UserRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 说明：用户注册业务层实现类
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 8:34
 */
@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private TbUserMapper userMapper;

    @Override
    public ShopResult checkUserInfo(String param, int type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        //判断要校验的数据类型，来设置不同的查询条件
        //1、2、3分别代表username、phone、email
        if(type == 1){
            criteria.andUsernameEqualTo(param);
        }else if(type == 2){
            criteria.andPhoneEqualTo(param);
        }else if(type == 3){
            criteria.andEmailEqualTo(param);
        }
        //执行查询
        List<TbUser> list = userMapper.selectByExample(example);
        if(list == null || list.size() == 0){
            return ShopResult.ok(true);
        }
        return ShopResult.ok(false);
    }

    @Override
    public ShopResult createUser(TbUser user) {
        //校验数据的合法性
        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return ShopResult.build(400,"用户名和密码不能为空");
        }
        //校验用户名是否重复
        ShopResult shopResult = checkUserInfo(user.getUsername(), 1);
        if(!(boolean) shopResult.getData()){
            return shopResult.build(400,"用户名重复");
        }
        //校验手机号是否重复
        if(user.getPhone() != null){
            shopResult = checkUserInfo(user.getPhone(), 2);
            if(!(boolean) shopResult.getData()){
                return shopResult.build(400,"手机号重复");
            }
        }
        //校验邮箱是否重复
        if(user.getEmail() != null){
            shopResult = checkUserInfo(user.getEmail(), 3);
            if(!(boolean) shopResult.getData()){
                return shopResult.build(400,"邮箱重复");
            }
        }
        //补全TbUser对象的属性
        user.setId(IDUtils.genItemId());
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        //把密码进行MD5加密
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        //插入到数据库
        userMapper.insert(user);
        //返回结果
        return ShopResult.ok();
    }
}
