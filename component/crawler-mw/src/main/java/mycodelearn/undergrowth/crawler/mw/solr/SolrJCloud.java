package mycodelearn.undergrowth.crawler.mw.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mycodelearn.undergrowth.crawler.mw.solr.learn.NewsBean;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月22日
 * @version 1.0.0
 */
public class SolrJCloud {

	private Logger logger = LoggerFactory.getLogger(SolrJCloud.class);
	String zkHostString = null;
	CloudSolrClient solr = null;
	public String[] authors = { "张三", "李四", "王五", "赵六", "张飞", "刘备", "关云长" };
	public String[] links = { "http://repository.sonatype.org/content/sites/forge-sites/m2e/",
			"http://news.ifeng.com/a/20140818/41626965_0.shtml",
			"http://news.ifeng.com/a/20140819/41631363_0.shtml?wratingModule_1_9_1",
			"http://news.ifeng.com/topic/19382/", "http://news.ifeng.com/topic/19644/" };
	public Random rand = new Random(47);

	public SolrJCloud(String zkHostString) {
		this.zkHostString = zkHostString;
		solr = new CloudSolrClient.Builder().withZkHost(this.zkHostString).build();
		solr.setDefaultCollection("gettingstarted");
	}

	public void solrJCloudQuery() {
		try {

			SolrQuery query = new SolrQuery();
			query.set("q", "*:*");
			// query.set("qt", "/select");
			// query.set("collection", "gettingstarted");
			logger.info(query.toString());
			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();
			logger.info(list.toString());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addDocs() {
		String[] words = { "中央全面深化改革领导小组", "第四次会议", "审议了国企薪酬制度改革", "考试招生制度改革", "传统媒体与新媒体融合等", "相关内容文件", "习近平强调要",
				"逐步规范国有企业收入分配秩序", "实现薪酬水平适当", "结构合理、管理规范、监督有效", "对不合理的偏高", "过高收入进行调整", "深化考试招生制度改革", "总的目标是形成分类考试",
				"综合评价", "多元录取的考试招生模式", "健全促进公平", "科学选才", "监督有力的体制机制", "着力打造一批形态多样", "手段先进", "具有竞争力的新型主流媒体",
				"建成几家拥有强大实力和传播力", "公信力", "影响力的新型媒体集团" };
		long start = System.currentTimeMillis();
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		for (int i = 1; i <=300; i++) {
			SolrInputDocument doc1 = new SolrInputDocument();
			doc1.addField("id", "id" + i, 1.0f);
			doc1.addField("name", words[i % 21], 1.0f);
			doc1.addField("price", 10 * i);
			docs.add(doc1);
		}
		try {
			// 可以通过三种方式增加docs,其中server.add(docs.iterator())效率最高
			// 增加后通过执行commit函数commit (936ms)
			// server.add(docs);
			// server.commit();

			// 增加doc后立即commit (946ms)
			// UpdateRequest req = new UpdateRequest();
			// req.setAction(ACTION.COMMIT, false, false);
			// req.add(docs);
			// UpdateResponse rsp = req.process(server);

			// the most optimal way of updating all your docs
			// in one http request(432ms)

			UpdateResponse response = solr.add(docs.iterator());
			logger.info(response.toString());
			solr.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		logger.info("time elapsed(ms):" + (System.currentTimeMillis() - start));
	}

	public void delDocs() {
		long start = System.currentTimeMillis();
		try {
			List<String> ids = new ArrayList<String>();
			for (int i = 1; i < 300; i++) {
				ids.add("id" + i);
			}
			solr.deleteById(ids);
			solr.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		logger.info("time elapsed(ms):" + (System.currentTimeMillis() - start));
	}

	public void addBeans() {
		String[] words = { "中央全面深化改革领导小组", "第四次会议", "审议了国企薪酬制度改革", "考试招生制度改革", "传统媒体与新媒体融合等", "相关内容文件", "习近平强调要",
				"逐步规范国有企业收入分配秩序", "实现薪酬水平适当", "结构合理、管理规范、监督有效", "对不合理的偏高", "过高收入进行调整", "深化考试招生制度改革", "总的目标是形成分类考试",
				"综合评价", "多元录取的考试招生模式", "健全促进公平", "科学选才", "监督有力的体制机制", "着力打造一批形态多样", "手段先进", "具有竞争力的新型主流媒体",
				"建成几家拥有强大实力和传播力", "公信力", "影响力的新型媒体集团" };

		long start = System.currentTimeMillis();
		Collection<NewsBean> docs = new ArrayList<NewsBean>();
		// DocumentObjectBinder binder = new DocumentObjectBinder();
		for (int i = 500; i <=800; i++) {
			NewsBean news = new NewsBean();
			news.setId("id" + i);
			news.setName("news" + i);
			news.setAuthor(genAuthors());
			news.setDescription(words[i % 21]);
			news.setRelatedLinks(genLinks());
			// SolrInputDocument doc1 = binder.toSolrInputDocument(news);
			docs.add(news);
		}
		try {
			// server.setRequestWriter(new BinaryRequestWriter());
			// 可以通过二种方式增加docs,其中server.add(docs.iterator())效率最高
			// 增加后通过执行commit函数commit (981ms)
			// server.addBeans(docs);
			// server.commit();

			// the most optimal way of updating all your docs
			// in one http request(481ms)
			solr.addBeans(docs.iterator());
			solr.optimize(); // time elasped 1176ms
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("time elapsed(ms):" + (System.currentTimeMillis() - start));
	}

	public String genAuthors() {
		List<String> list = Arrays.asList(authors).subList(0, rand.nextInt(7));
		String str = "";
		for (String tmp : list) {
			str += " " + tmp;
		}
		return str;
	}

	public List<String> genLinks() {
		return Arrays.asList(links).subList(0, rand.nextInt(5));
	}

}
