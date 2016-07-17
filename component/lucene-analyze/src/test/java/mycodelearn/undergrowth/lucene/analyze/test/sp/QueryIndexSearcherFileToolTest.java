package mycodelearn.undergrowth.lucene.analyze.test.sp;

import java.io.IOException;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mycodelearn.undergrowth.lucene.analyze.mw.sp.IndexFileTool;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.SearchFileTool;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.SearchTool;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月13日
 * @version 1.0.0
 */
public class QueryIndexSearcherFileToolTest {

	private String indexPath = "D:\\lucene\\sp\\indextool\\index";
	private String docPath = "D:\\lucene\\sp\\indextool\\docs";
	private Logger logger = LoggerFactory.getLogger(QueryIndexSearcherFileToolTest.class);

	@Test
	public void testIndex() {
		IndexFileTool indexTool = new IndexFileTool();
		indexTool.index(indexPath, docPath);
	}

	@Test
	public void testDelete() {
		IndexFileTool indexTool = new IndexFileTool();
		PhraseQuery query = new PhraseQuery();
		query.add(new Term("id", "1"));
		indexTool.delete(indexPath, query);
	}

	@Test
	public void testUpdate() {
		IndexFileTool indexTool = new IndexFileTool();
		Term term = new Term("content", "tencent");
		indexTool.update(indexPath, term, docPath + "\\" + "con1.txt");
	}

	@Test
	public void testSearch() {
		SearchFileTool indexSearcher = new SearchFileTool();
		indexSearcher.search(indexPath, "content", "Apache", 10);
	}

	@Test
	public void testSearchQuery() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			indexSearcher.search(indexPath, query, 50);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchQuerryPage() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			for (int i = 1; i < 6; i++) {
				indexSearcher.searchPage(indexPath, query, i, 10);
				logger.info("-----------分割线,以上是第" + i + "页-----------");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchQuerryPageAfter() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			for (int i = 1; i < 6; i++) {
				indexSearcher.searchPageAfter(indexPath, query, i, 10);
				logger.info("-----------分割线,以上是第" + i + "页-----------");
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDisReaderInfo() throws IOException {
		SearchFileTool indexSearcher = new SearchFileTool();
		indexSearcher.disReaderInfo(indexPath);
	}
	
	@Test
	public void testTermQuery() {
		SearchFileTool indexSearcher = new SearchFileTool();
		//精确查找
		Query query=new TermQuery(new Term("content", "apache"));
		indexSearcher.search(indexPath, query, 100);
	}
	
	@Test
	public void testTermRangeQuery() {
		SearchFileTool indexSearcher = new SearchFileTool();
		Query query=new TermRangeQuery("length", "300", "500", true, true);
		indexSearcher.search(indexPath, query, 500);
	}
	
	@Test
	public void testNumericRangeQuery() {
		SearchFileTool indexSearcher = new SearchFileTool();
		Query query=NumericRangeQuery.newIntRange("size", 200, 500, true, true);
		indexSearcher.search(indexPath, query, 500);
	}
	
	@Test
	public void testPrefixQuery() {
		SearchFileTool indexSearcher = new SearchFileTool();
		Query query=new PrefixQuery(new Term("name", "con"));
		indexSearcher.search(indexPath, query, 500);
	}
	
	@Test
	public void testWildcardQuery() {
		SearchFileTool indexSearcher = new SearchFileTool();
		Query query=new WildcardQuery(new Term("name", "*con*"));
		indexSearcher.search(indexPath, query, 500);
	}
	
	@Test
	public void testFuzzyQuery() {
		SearchFileTool indexSearcher = new SearchFileTool();
		Query query=new FuzzyQuery(new Term("content", "apach"));
		indexSearcher.search(indexPath, query, 500);
	}
	
	@Test
	public void testBooleanQuery() {
		SearchFileTool indexSearcher = new SearchFileTool();
		BooleanQuery query=new BooleanQuery();
		Query queryName=new WildcardQuery(new Term("name", "*con*"));
		Query queryContent=new TermQuery(new Term("content", "apache"));
		query.add(queryName, Occur.SHOULD);
		query.add(queryContent, Occur.SHOULD);
		indexSearcher.search(indexPath, query, 500);
	}
	
	@Test
	public void testPhraseQuery() {
		SearchFileTool indexSearcher = new SearchFileTool();
		PhraseQuery query=new PhraseQuery();
		query.setSlop(1);
		query.add(new Term("content", "apache"));
		query.add(new Term("content", "exec"));
		indexSearcher.search(indexPath, query, 500);
	}
	
	@Test
	public void testQueryParser() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			//分词查询
			Query query = parser.parse("Apache Exec");
			indexSearcher.search(indexPath, query, 500);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryParser2() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			//通配符查询
			Query query = parser.parse("name:con*");
			indexSearcher.search(indexPath, query, 500);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryParser3() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			//多个词组查询
			Query query = parser.parse("- name:con* + apache");
			indexSearcher.search(indexPath, query, 500);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryParser4() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			//范围查询
			//Query query = parser.parse("length:[100 TO 200]");
			Query query = parser.parse("length:{169 TO 200}");
			//无法进行数字范围的查询
			//Query query = parser.parse("size:{169 TO 200}");
			indexSearcher.search(indexPath, query, 500);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryParser5() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			//Query query = parser.parse("\"Apache Commons Exec\"");
			//间隔查询
			Query query = parser.parse("\"Apache  Exec\"~1");
			indexSearcher.search(indexPath, query, 500);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryParser6() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			//Query query = parser.parse("\"Apache Commons Exec\"");
			//模糊查询
			Query query = parser.parse("Apach~");
			indexSearcher.search(indexPath, query, 500);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
