package mycodelearn.undergrowth.crawler.mw;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月12日
 * @version 1.0.0
 */
public class IndexSearchTest {

	private String indexDir = "d:\\lucene\\index\\";
	private String contentField = "contents";
	private String content = "Jerry";
	private int topNum = 100;
	private IndexReader reader = null;
	private IndexSearcher search = null;

	@Before
	public void before() throws IOException {
		reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexDir)));
		search = new IndexSearcher(reader);
	}

	@Test
	public void testSearch() throws IOException, ParseException {
		Query query = createQuery();
		TopDocs topDocs = search.search(query, topNum);
		diResult(topDocs);
	}

	/**
	 * 解析查询
	 * 
	 * @return
	 * @throws ParseException
	 */
	public Query createQuery() throws ParseException {
		Analyzer a = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser(contentField, a);
		Query query = queryParser.parse(content);
		return query;
	}

	/**
	 * 显示结果
	 * 
	 * @param topDocs
	 * @throws IOException
	 */
	public void diResult(TopDocs topDocs) throws IOException {
		ScoreDoc[] hits = topDocs.scoreDocs;
		Document document = null;
		System.out.println("total:" + topDocs.totalHits);
		for (int i = 0; i < hits.length; i++) {
			document = search.doc(hits[i].doc);
			System.out.println("document score detail:" + hits[i]);
			System.out.println("title:" + document.get("title"));
		}
	}

	@After
	public void after() throws IOException {
		reader.close();
	}
}
