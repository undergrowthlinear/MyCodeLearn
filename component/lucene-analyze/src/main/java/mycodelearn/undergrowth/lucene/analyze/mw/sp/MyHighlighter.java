package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月22日
 * @version 1.0.0
 */
public class MyHighlighter {

	private Logger logger = LoggerFactory.getLogger(MyHighlighter.class);

	public String highlighter(Query query, String text) {
		try {
			TokenStream tokenStream = new MMSegAnalyzer().tokenStream("content", new StringReader(text));
			QueryScorer scorer = new QueryScorer(query);
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
			Formatter formatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
			Highlighter highlighter = new Highlighter(formatter, scorer);
			highlighter.setTextFragmenter(fragmenter);
			String string = highlighter.getBestFragment(tokenStream, text);
			return string;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error(e.getMessage());
		} catch (InvalidTokenOffsetsException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return null;
	}

	// 高亮顯示搜索結果
	public void highlighterSR(String field, String searchText, String text) {

		try {
			// 本次示例为了简便直接使用之前实验建立的索引
			Directory directory = new RAMDirectory();
			Analyzer analyzer = new MMSegAnalyzer();
			// 存储
			IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_35, analyzer);
			IndexWriter writer = new IndexWriter(directory, conf);
			Document doc = new Document();
			doc.add(new Field(field, text,Field.Store.YES,Field.Index.ANALYZED));
			writer.addDocument(doc);
			writer.commit();
			// 搜索
			IndexReader reader = IndexReader.open(directory);// 读取目录
			IndexSearcher search = new IndexSearcher(reader);// 初始化查询组件
			QueryParser parser = new QueryParser(Version.LUCENE_35, field, analyzer);
			Query query = parser.parse(searchText);
			TopDocs td = search.search(query, 10000);// 获取匹配上元素的一个docid
			ScoreDoc[] sd = td.scoreDocs;// 加载所有的Documnet文档

			logger.info("本次命中数据:" + sd.length);
			QueryScorer scorer = new QueryScorer(query, field);

			Highlighter highlighter = new Highlighter(scorer);
			highlighter.setTextFragmenter(new SimpleSpanFragmenter(scorer));

			for (ScoreDoc scoreDoc : sd) {
				Document document = search.doc(scoreDoc.doc);
				String content = document.get(field);
				TokenStream tokenStream = TokenSources.getAnyTokenStream(search.getIndexReader(), scoreDoc.doc, field,
						document, analyzer);
				logger.info(highlighter.getBestFragment(tokenStream, content));
			}
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (InvalidTokenOffsetsException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}

}
