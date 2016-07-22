package mycodelearn.undergrowth.lucene.analyze.test.sp;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.function.FieldScoreQuery;
import org.apache.lucene.search.function.FieldScoreQuery.Type;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mycodelearn.undergrowth.lucene.analyze.mw.sp.IndexFileTool;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.MyCustomScore;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.MyCustomScore.MyCustomScoreQuery;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.SearchFileTool;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月13日
 * @version 1.0.0
 */
public class IndexSearcherFileToolCustomScoreTest {

	private String indexPath = "D:\\lucene\\sp\\indextoolscore\\index";
	private String docPath = "D:\\lucene\\sp\\indextoolscore\\docs";
	private Logger logger = LoggerFactory.getLogger(IndexSearcherFileToolCustomScoreTest.class);

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
	public void testSearchCustomScore() {
		try {
			SearchFileTool indexSearcher = new SearchFileTool();
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", indexSearcher.getAnalyzer());
			Query query = parser.parse("Apache");
			FieldScoreQuery valSrcQuery=new FieldScoreQuery("size",Type.INT );
			MyCustomScore myCustomScore=new MyCustomScore();
			MyCustomScoreQuery customScoreQuery=myCustomScore.new MyCustomScoreQuery(query, valSrcQuery);
			indexSearcher.search(indexPath, customScoreQuery, 500);;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
