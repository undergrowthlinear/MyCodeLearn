package mycodelearn.undergrowth.lucene.analyze.test.sp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mycodelearn.undergrowth.lucene.analyze.mw.sp.FilterAccessor;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.FilterAccessorImpl;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.IndexFileTool;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.MyIdFilter;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.SearchFileTool;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月13日
 * @version 1.0.0
 */
public class IndexSearcherFileToolFilterTest {

	private String indexPath = "D:\\lucene\\sp\\indextoolfilter\\index";
	private String docPath = "D:\\lucene\\sp\\indextoolfilter\\docs";
	private Logger logger = LoggerFactory.getLogger(IndexSearcherFileToolFilterTest.class);

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
	public void testSearchFilter() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			TermRangeFilter filter=new TermRangeFilter("length", "150", "500", true, true);
			indexSearcher.search(indexPath, query, 500, filter);;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchFilterInt() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			NumericRangeFilter<Integer> filter=NumericRangeFilter.newIntRange("size", 150, 500, true, true);
			indexSearcher.search(indexPath, query, 500, filter);;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchQueryFilter() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			NumericRangeQuery<Integer> queryInt=NumericRangeQuery.newIntRange("size", 100, 500, true, true);
			QueryWrapperFilter filter=new QueryWrapperFilter(queryInt);
			indexSearcher.search(indexPath, query, 500, filter);;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchCustomFilter() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			List<String> accessList=new ArrayList<>();
			accessList.addAll(Arrays.asList("33","272"));
			FilterAccessor filterAccessor=new FilterAccessorImpl(accessList,"id");
			//filterAccessor.set();
			filterAccessor.clear();
			Filter filter=new MyIdFilter(filterAccessor);
			indexSearcher.search(indexPath, query, 500, filter);;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
