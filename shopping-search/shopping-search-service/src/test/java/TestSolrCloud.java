import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * Created by baiqiang on 2017/10/11.
 */
public class TestSolrCloud
{
//    @Test
//    public void testSolrCloud() throws Exception {
//        // 操作步骤：
//        // 第一步，创建一个SolrServer对象，需要使用CloudSolrServer子类对象，它有一个构造方法，
//        // 构造方法有一个参数，叫做zkHost，是一个字符串类型，也即zookeeper的地址列表
//        CloudSolrServer solrServer = new CloudSolrServer("192.168.200.128:2180,192.168.200.128:2182,192.168.200.128:2183");
//        // 第二步，需要指定默认的collection
//        solrServer.setDefaultCollection("collection2");
//        // 第三步，向索引库中添加文档，和单机版一致
//        SolrInputDocument document = new SolrInputDocument();
//        document.setField("id", "1");
//        document.setField("item_title", "测试商品");
//        document.setField("item_price", "199");
//        solrServer.add(document);
//        // 第四步，提交
//        solrServer.commit();
//    }
//    // 删除文档
//    @Test
//    public void testDeleteDocument() throws Exception {
//        // 第一步，创建一个SolrServer对象，应该使用CloudSolrServer对象，它有一个构造方法，
//        // 构造方法有一个参数，叫做zkHost，是一个字符串类型，也即zookeeper的地址列表
//        CloudSolrServer solrServer = new CloudSolrServer("192.168.200.128:2180,192.168.200.128:2182,192.168.200.128:2183");
//        // 第二步，需要指定默认的collection
//        solrServer.setDefaultCollection("collection2");
//        solrServer.deleteByQuery("*:*");
//        solrServer.commit();
//    }
}
