package mycodelearn.undergrowth.lucene.analyze.test.sp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;

import mycodelearn.undergrowth.lucene.analyze.mw.sp.AnalyzerTool;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.SamewordAnalyzer;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.SamewordContext;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.SimpleSamewordContext;

public class SamewordAnalyzerTest {

	private Logger logger = LoggerFactory.getLogger(SamewordAnalyzerTest.class);

	private SamewordContext samewordContext;
	private String indexPath = "D:\\lucene\\sp\\sameword\\index";

	@Before
	public void setUp() throws Exception {
		Map<String, String[]> samewordsMap = new HashMap<String, String[]>();
		samewordsMap.put("我", new String[] { "咱", "me" });
		samewordsMap.put("中华", new String[] { "大陆", "天朝" });
		samewordContext = new SimpleSamewordContext(samewordsMap);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSamewordAnalyzer() {
		SamewordAnalyzer ana = new SamewordAnalyzer(samewordContext);
		String text = "how are you thank you";
		AnalyzerTool analyzerTool = new AnalyzerTool();
		analyzerTool.disTokenStreamInfo(text, ana);
	}

	@Test
	public void testSamewordAnalyzerIndexSearch() {
		try {
			SamewordAnalyzer analyzer = new SamewordAnalyzer(samewordContext);
			String text = "how are you thank you";
			Directory directory = new RAMDirectory();
			IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_35, analyzer);
			IndexWriter indexWriter = new IndexWriter(directory, conf);
			Document doc = new Document();
			doc.add(new Field("content", text, Field.Store.YES, Field.Index.ANALYZED));
			indexWriter.addDocument(doc);
			indexWriter.close();
			// 查询
			IndexReader indexReader = IndexReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			/*
			 * QueryParser parser = new QueryParser(Version.LUCENE_35,
			 * "content", analyzer); Query query = parser.parse("how");
			 */
			TermQuery query = new TermQuery(new Term("content", "whos"));
			logger.info("query:" + query);
			TopDocs topDocs = indexSearcher.search(query, 50);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if (scoreDocs != null) {
				for (ScoreDoc scoreDoc : scoreDocs) {
					logger.info("scoreDocs.length:" + scoreDocs.length);
					logger.info("content:" + indexSearcher.doc(scoreDoc.doc));
				}

			}
			indexSearcher.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSamewordAnalyzerChina() {
		SamewordAnalyzer ana = new SamewordAnalyzer(samewordContext);
		Analyzer ikAnalyzer = new IKAnalyzer(true);
		Analyzer mmsAna = new MMSegAnalyzer();
		//String text = "每一个词汇单元都是TokenStream 通过incrementToken获取下一个词汇单元每一个词汇单元都有 CharTermAttribute  OffsetAttribute PositionIncrementAttribute TypeAttribute过滤器处理每一个词汇单元TokenStream";
		String text = "我处在中华人民共和国";
		AnalyzerTool analyzerTool = new AnalyzerTool();
		analyzerTool.disTokenStreamInfo(text, ana);
		System.out.println("------------------------分割线------------------------");
		analyzerTool.disTokenStreamInfo(text, ikAnalyzer);
		System.out.println("------------------------分割线------------------------");
		analyzerTool.disTokenStreamInfo(text, mmsAna);
	}

	@Test
	public void testSamewordAnalyzerIndexSearchChina() {
		try {
			SamewordAnalyzer analyzer = new SamewordAnalyzer(samewordContext);
			String text = "我处在中华人民共和国";
			Directory directory = FSDirectory.open(new File(indexPath));
			IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_35, analyzer);
			IndexWriter indexWriter = new IndexWriter(directory, conf);
			Document doc = new Document();
			doc.add(new Field("content", text, Field.Store.YES, Field.Index.ANALYZED));
			indexWriter.addDocument(doc);
			indexWriter.close();
			// 查询
			IndexReader indexReader = IndexReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			/*
			 * QueryParser parser = new QueryParser(Version.LUCENE_35,
			 * "content", analyzer); Query query = parser.parse("how");
			 */
			TermQuery query = new TermQuery(new Term("content", "天朝"));
			logger.info("query:" + query);
			TopDocs topDocs = indexSearcher.search(query, 50);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if (scoreDocs != null) {
				for (ScoreDoc scoreDoc : scoreDocs) {
					logger.info("scoreDocs.length:" + scoreDocs.length);
					logger.info("content:" + indexSearcher.doc(scoreDoc.doc));
				}

			}
			indexSearcher.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
