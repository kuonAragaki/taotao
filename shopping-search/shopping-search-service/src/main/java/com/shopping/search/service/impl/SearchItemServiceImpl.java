package com.shopping.search.service.impl;

import com.shopping.common.pojo.SearchItem;
import com.shopping.common.pojo.ShopResult;
import com.shopping.search.mapper.ItemMapper;
import com.shopping.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：将商品数据导入索引库，业务层实现类
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/7 20:58
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SolrServer solrServer;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public ShopResult importAllItemToIndex() {

        try {
            //1.查询所有商品数据
            List<SearchItem> itemList = itemMapper.getItemList();
            //2.创建一个SolrService对象
            for (SearchItem searchItem : itemList) {
                //3.为每个商品创建一个SolrInputDocument对象
                SolrInputDocument document = new SolrInputDocument();
                //4.为文档添加域
                document.addField("id",searchItem.getId());
                document.addField("item_title",searchItem.getTitle());
                document.addField("item_sell_point", searchItem.getSell_point());
                document.addField("item_price", searchItem.getPrice());
                document.addField("item_image", searchItem.getImage());
                document.addField("item_category_name", searchItem.getCategory_name());
                document.addField("item_desc", searchItem.getItem_des());
                //5.向索引库中添加文档
                solrServer.add(document);
            }
            //提交
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //6.返回ShopResult,当你纠结返回值是什么的时候，你就可以使用它
        return ShopResult.ok();
    }
}
