package com.shopping.controller;

import com.shopping.common.pojo.ShopResult;
import com.shopping.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 说明：
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/7 21:30
 */
@Controller
public class IndexManagerController {
    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/index/import")
    @ResponseBody
    public ShopResult indexImport() throws Exception{
        ShopResult shopResult = searchItemService.importAllItemToIndex();
        return shopResult;
    }
}
