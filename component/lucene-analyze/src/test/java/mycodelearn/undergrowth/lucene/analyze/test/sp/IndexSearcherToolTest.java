package mycodelearn.undergrowth.lucene.analyze.test.sp;

import java.io.IOException;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.junit.Test;

import mycodelearn.undergrowth.lucene.analyze.mw.sp.IndexTool;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.SearchTool;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月13日
 * @version 1.0.0
 */
public class IndexSearcherToolTest {

	private String indexPath = "D:\\lucene\\sp\\indextool\\index";
	private String docPath = "D:\\lucene\\sp\\indextool\\docs";

	@Test
	public void testIndex() {
		IndexTool indexTool = new IndexTool();
		indexTool.index(indexPath, docPath);
	}

	@Test
	public void testDelete() {
		IndexTool indexTool = new IndexTool();
		PhraseQuery query = new PhraseQuery();
		query.add(new Term("id", "1"));
		indexTool.delete(indexPath, query);
	}

	@Test
	public void testUpdate() {
		IndexTool indexTool = new IndexTool();
		Term term = new Term("content", "tencent");
		indexTool.update(indexPath, term);
	}

	@Test
	public void testSearch() {
		SearchTool indexSearcher = new SearchTool();
		indexSearcher.search(indexPath, "content", "tencent", 100);
	}
	

	@Test
	public void testSearchQuery() {
		try {
			SearchTool indexSearcher = new SearchTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("email");
			indexSearcher.search(indexPath, query, 20);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchQuerryPage() {
		try {
			SearchTool indexSearcher = new SearchTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("email");
			indexSearcher.searchPage(indexPath, query, 1, 20);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDisReaderInfo() throws IOException {
		SearchTool indexSearcher = new SearchTool();
		indexSearcher.disReaderInfo(indexPath);
	}

}
