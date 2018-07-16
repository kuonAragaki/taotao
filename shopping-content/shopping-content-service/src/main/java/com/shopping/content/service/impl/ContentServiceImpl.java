package com.shopping.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.common.pojo.EUDataGridResult;
import com.shopping.common.pojo.ShopResult;
import com.shopping.content.jedis.JedisClient;
import com.shopping.content.service.ContentService;
import com.shopping.mapper.TbContentMapper;
import com.shopping.pojo.TbContent;
import com.shopping.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 说明：内容管理
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/6 15:55
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    @Override
    public ShopResult insertContent(TbContent content) {
        //补全pojo内容
        Date date = new Date();
        content.setCreated(date);
        content.setUpdated(date);
        //添加操作
        contentMapper.insert(content);

        //做缓存同步，清除redis中内容分类id对应的缓存信息
        jedisClient.hdel(CONTENT_KEY,content.getCategoryId().toString());

        return ShopResult.ok();
    }

    @Override
    public EUDataGridResult getContentList(int page, int rows, long contentCid) {
        //开始分页
        PageHelper.startPage(page,rows);

        //根据内容分类id查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExample(example);

        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //获取记录总条数
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public String getContentList() {
        return contentMapper.selectByExample(null).toString();
    }

    @Override
    public List<TbContent> getContentList(long contentCid) {
        //查询数据库之前，先查询缓存，并且添加缓存不能影响正常业务逻辑
        try {
            String json = jedisClient.hget(CONTENT_KEY, contentCid + "");
            //判断是否命中缓存，即判断json字符串是否为null或""
            if(StringUtils.isNotBlank(json)){
                //把这个json转换成list集合
                List<TbContent> list = JSONObject.parseArray(json,TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据内容分类id查询内容列表
        TbContentExample example = new TbContentExample();
        //设置查询条件
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExample(example);
        //向缓存汇总保存结果，并且添加缓存不能影响正常业务逻辑
        try {
            jedisClient.hset(CONTENT_KEY,contentCid+"",JSON.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
