package mycodelearn.undergrowth.lucene.analyze.test.sp;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mycodelearn.undergrowth.lucene.analyze.mw.sp.IndexFileTool;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.MyQueryParser;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.SearchFileTool;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月13日
 * @version 1.0.0
 */
public class IndexSearcherFileToolCustomQueryParserTest {

	private String indexPath = "D:\\lucene\\sp\\indextoolqueryparser\\index";
	private String docPath = "D:\\lucene\\sp\\indextoolqueryparser\\docs";
	private Logger logger = LoggerFactory.getLogger(IndexSearcherFileToolCustomQueryParserTest.class);

	@Test
	public void testIndex() {
		IndexFileTool indexTool = new IndexFileTool();
		indexTool.index(indexPath, docPath);
	}

	
	@Test
	public void testSearch() {
		SearchFileTool indexSearcher = new SearchFileTool();
		indexSearcher.search(indexPath, "content", "Apache", 500);
	}

	@Test
	public void testSearchCustomQueryParser() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new MyQueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			indexSearcher.search(indexPath, query, 500);;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchCustomQueryParserWildcardQuery() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new MyQueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("*Apache");
			indexSearcher.search(indexPath, query, 500);;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	@Test
	public void testSearchCustomQueryParserRangeQuery() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new MyQueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("size:[100 TO 500]");
			indexSearcher.search(indexPath, query, 500);;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	

}
