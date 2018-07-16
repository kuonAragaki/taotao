package com.shopping.search.service;

import com.shopping.common.pojo.SearchResult;

/**
 * 说明：搜索服务接口
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/8 9:35
 */
public interface SearchService {
    public SearchResult search(String queryString,int page,int rows) throws Exception;
}
