package com.shopping.sso.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shopping.common.pojo.ShopResult;
import com.shopping.mapper.TbUserMapper;
import com.shopping.pojo.TbUser;
import com.shopping.pojo.TbUserExample;
import com.shopping.sso.service.UserLoginService;
import com.shopping.sso.service.jedis.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * 说明：用户登录业务实现类
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/11 10:54
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    JedisClient jedisClient;

    @Value("${SESSION_PRE}")
    private String SESSION_PRE;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    /**
     * 说明：登录
     */
    @Override
    public ShopResult login(String username, String password) {
        //判断用户名和密码是否正确
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if(list == null || list.size() == 0){
            return ShopResult.build(400,"用户名或密码错误");
        }
        //校验密码，密码需要进行md5加密后再校验
        TbUser user = list.get(0);
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            return ShopResult.build(400,"用户名或密码错误");
        }
        //生成一个token
        String token = UUID.randomUUID().toString();
        //把用户信息保存到Redis
        //key就是token，value就是用户对象转为json字符串
        user.setPassword(null);//为了安全，密码不保存入redis
        jedisClient.set(SESSION_PRE+":"+token, JSON.toJSONString(user));
        //设置key的过期时间
        jedisClient.expire(SESSION_PRE+":"+token,SESSION_EXPIRE);
        //返回结果
        return ShopResult.ok(token);
    }

    /**
     * 说明：用token从redis查询用户信息
     */
    @Override
    public ShopResult getUserByToken(String token) {
        //根据token从redis中查询用户信息
        String json = jedisClient.get(SESSION_PRE + ":" + token);
        //判断是否为空
        if(StringUtils.isBlank(json)){
            return ShopResult.build(400,"此session已经过期，请重新登录");
        }
        //更新过期时间
        jedisClient.expire(SESSION_PRE+":"+token,SESSION_EXPIRE);
        //返回用户信息
        return ShopResult.ok(JSONObject.parseObject(json,TbUser.class));
    }

    /**
     * 说明：登出
     */
    @Override
    public ShopResult logout(String token) {
        jedisClient.expire(SESSION_PRE+":"+token,0);
        return ShopResult.ok();
    }
}
