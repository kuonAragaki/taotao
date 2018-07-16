package com.shopping.search.controller;

import com.shopping.common.pojo.SearchResult;
import com.shopping.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 说明：搜索服务控制层
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/8 9:55
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @Value("${ITEM_ROWS}")
    private Integer ITEM_ROWS;

    @RequestMapping("/search")
    public String searchItem(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model)throws Exception{
        queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
        //调用服务搜索商品信息
        SearchResult searchResult = searchService.search(queryString, page, ITEM_ROWS);
        //使用Model向页面传递参数
        model.addAttribute("query",queryString);
        model.addAttribute("totalPages",searchResult.getTotalPage());
        model.addAttribute("itemList",searchResult.getItemList());
        model.addAttribute("page",page);

//        int a = 1/0; //全局异常页面调试代码

        //返回逻辑视图
        return "search";
    }

    /**
     * 说明：打开其他页面
     */
    @RequestMapping("/{page}")
    public String showpage(@PathVariable String page){
        return page;
    }

}
