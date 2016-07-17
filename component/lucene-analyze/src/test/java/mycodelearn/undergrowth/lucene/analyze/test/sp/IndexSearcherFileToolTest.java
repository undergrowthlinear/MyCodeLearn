package mycodelearn.undergrowth.lucene.analyze.test.sp;

import java.io.IOException;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
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
public class IndexSearcherFileToolTest {

	private String indexPath = "D:\\lucene\\sp\\indextool\\index";
	private String docPath = "D:\\lucene\\sp\\indextool\\docs";
	private Logger logger = LoggerFactory.getLogger(IndexSearcherFileToolTest.class);

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

}
