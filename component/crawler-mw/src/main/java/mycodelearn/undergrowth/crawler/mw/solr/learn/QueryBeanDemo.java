package mycodelearn.undergrowth.crawler.mw.solr.learn;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class QueryBeanDemo {
  public static final String SOLR_URL = "http://192.168.126.130:9983/solr";

  public static void main(String[] args) throws SolrServerException,
      IOException {
    // http://172.168.63.233:8983/solr/collection1/select?q=description%3A%E6%80%BB%E7%9B%AE%E6%A0%87&facet=true&facet.field=author_s
	  SolrClient server = new HttpSolrClient.Builder(SOLR_URL).build();
  //  server.setMaxRetries(1);
   // server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
    //server.setConnectionTimeout(5000); // 5 seconds to establish TCP
    // server.setRequestWriter(new BinaryRequestWriter());

    SolrQuery query = new SolrQuery();
    query.setQuery("description:改革");
    query.setStart(0);
    query.setRows(2);
    query.setFacet(true);
    query.addFacetField("author_s");

    QueryResponse response = server.query("collection",query);
    // 搜索得到的结果数
    System.out.println("Find:" + response.getResults().getNumFound());
    // 输出结果
    int iRow = 1;
    
    //response.getBeans存在BUG,将DocumentObjectBinder引用的Field应该为 org.apache.solr.client.solrj.beans.Field
    SolrDocumentList list = response.getResults();
    //DocumentObjectBinderL binder = new DocumentObjectBinderL();
    //List<NewsBean> beanList=binder.getBeans(NewsBean.class, list);
    List<NewsBean> beanList=null;
    for(NewsBean news:beanList){
      System.out.println(news.getId());
    }

    for (SolrDocument doc : response.getResults()) {
      System.out.println("----------" + iRow + "------------");
      System.out.println("id: " + doc.getFieldValue("id").toString());
      System.out.println("name: " + doc.getFieldValue("name").toString());
      iRow++;
    }
    for (FacetField ff : response.getFacetFields()) {
      System.out.println(ff.getName() + "," + ff.getValueCount() + ","
          + ff.getValues());
    }
  }
}
