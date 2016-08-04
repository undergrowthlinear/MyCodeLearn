package mycodelearn.undergrowth.lucene.analyze.test.sp;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;

import mycodelearn.undergrowth.lucene.analyze.mw.sp.IndexSearchManagerTool;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月13日
 * @version 1.0.0
 */
public class IndexSearcherManagerToolTest {

	private String indexPath = "D:\\lucene\\sp\\indexsearchmanagertool\\index";
	private String docPath = "D:\\lucene\\sp\\indexsearchmanagertool\\docs";

	private IndexSearchManagerTool indexTool = null;

	@Before
	public void before() {
		indexTool = new IndexSearchManagerTool(indexPath);
	}

	@Test
	public void testIndex() {
		indexTool.getNrtManagerFileTool().index(indexPath, docPath);
		indexTool.getNrtManagerFileTool().commit();
	}

	@Test
	public void testDelete() {
		PhraseQuery query = new PhraseQuery();
		query.add(new Term("id", "147"));
		indexTool.getNrtManagerFileTool().delete(indexPath, query);
	}

	@Test
	public void testUpdate() {
		Term term = new Term("content", "tencent");
		indexTool.getNrtManagerFileTool().update(indexPath, term, docPath + "\\" + "con1.txt");
	}

	@Test
	public void testSearch() {
		for (int i = 0; i < 5; i++) {
			indexTool.getSearcherManagerFileTool().search(indexPath, "content", "Apache", 100);
			testDelete();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		indexTool.getNrtManagerFileTool().commit();
	}

	@Test
	public void testSearchQuery() {
		try {
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content",
					indexTool.getSearcherManagerFileTool().getAnalyzer());
			Query query = parser.parse("Apache");
			indexTool.getSearcherManagerFileTool().search(indexPath, query, 20);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchQuerryPage() {
		try {
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content",
					indexTool.getSearcherManagerFileTool().getAnalyzer());
			Query query = parser.parse("Apache");
			indexTool.getSearcherManagerFileTool().searchPage(indexPath, query, 1, 10);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
