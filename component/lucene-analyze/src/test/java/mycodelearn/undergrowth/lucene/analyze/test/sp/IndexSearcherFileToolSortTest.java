package mycodelearn.undergrowth.lucene.analyze.test.sp;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mycodelearn.undergrowth.lucene.analyze.mw.sp.IndexFileTool;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.SearchFileTool;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月13日
 * @version 1.0.0
 */
public class IndexSearcherFileToolSortTest {

	private String indexPath = "D:\\lucene\\sp\\indextoolsort\\index";
	private String docPath = "D:\\lucene\\sp\\indextoolsort\\docs";
	private Logger logger = LoggerFactory.getLogger(IndexSearcherFileToolSortTest.class);

	@Test
	public void testIndex() {
		IndexFileTool indexTool = new IndexFileTool();
		indexTool.index(indexPath, docPath);
	}


	@Test
	public void testSearch() {
		SearchFileTool indexSearcher = new SearchFileTool();
		indexSearcher.search(indexPath, "content", "Apache", 10);
	}

	@Test
	public void testSearchSortINDEXORDER() {
		SearchFileTool indexSearcher = new SearchFileTool();
		indexSearcher.search(indexPath, "content", "Apache", 10, Sort.INDEXORDER);
	}

	@Test
	public void testSearchSortRELEVANCE() {
		SearchFileTool indexSearcher = new SearchFileTool();
		indexSearcher.search(indexPath, "content", "Apache", 10, Sort.RELEVANCE);
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
	public void testSearchQuerySortInt() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			indexSearcher.search(indexPath, query, 50, new Sort(new SortField("size", SortField.INT)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchQuerySortString() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			indexSearcher.search(indexPath, query, 50, new Sort(new SortField("name", SortField.STRING)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void testSearchQuerySortMultiField() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			indexSearcher.search(indexPath, query, 50, new Sort(new SortField("size", SortField.INT),
					 SortField.FIELD_SCORE));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchQuerySortMultiField2() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			indexSearcher.search(indexPath, query, 50, new Sort(new SortField("size", SortField.INT),
					new SortField("name", SortField.STRING), SortField.FIELD_SCORE));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
