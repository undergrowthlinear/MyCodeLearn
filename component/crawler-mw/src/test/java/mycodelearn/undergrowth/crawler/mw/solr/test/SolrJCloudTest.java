package mycodelearn.undergrowth.crawler.mw.solr.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mycodelearn.undergrowth.crawler.mw.solr.SolrJCloud;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月22日
 * @version 1.0.0
 */
public class SolrJCloudTest {

	private Logger logger = LoggerFactory.getLogger(SolrJCloudTest.class);
	private SolrJCloud solrJColud = null;
	String zkHostString = "192.168.126.130:9983";

	@Before
	public void before() {
		solrJColud = new SolrJCloud(zkHostString);
	}

	@Test
	public void testSolrJCloud() {
		solrJColud.solrJCloudQuery();
	}

	@Test
	public void testDelDocs() {
		solrJColud.delDocs();
	}

	@Test
	public void testAddDocs() {
		solrJColud.addDocs();
	}

	@Test
	public void testAddBeans() {
		solrJColud.addBeans();
	}

}
