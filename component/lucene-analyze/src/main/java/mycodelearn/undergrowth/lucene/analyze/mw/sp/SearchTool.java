package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用) createIndex //1.创建Directory
 * //2.创建IndexWriter //3.创建Document //4.为Document创建Field
 * //5.使用IndexWriter将Document写入索引 //6.关闭InderWriter
 * 
 * Search: //1.创建Directory //2.创建IndexReader //3.使用IndexReader创建IndexSearcher
 * //4.创建Query //5.使用IndexSearcher搜索Query,返回TopDocs //6.显示TopDocs的ScoreDoc
 * //7.通过IndexReader/docId获取Document //8.通过Document的get获取字段 //9.关闭IndexReader
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月13日
 * @version 1.0.0
 */
public class SearchTool {

	private Logger logger = LoggerFactory.getLogger(SearchTool.class);
	private ToolData data = new ToolData();
	private static IndexReader indexReader = null;

	public SearchTool() {
	}

	public void search(String indexPath, String fieldName, String searchValue, int num) {
		try {
			// 1.创建Directory
			Directory directory = getDirectory(indexPath);
			// 2.创建IndexReader
			indexReader = getIndexReader(directory);
			// 3.使用IndexReader创建IndexSearcher
			IndexSearcher searcher = getIndexSearcher(indexReader);
			// 4.创建Query
			Query query = createQuery(fieldName, searchValue);
			// 5.使用IndexSearcher搜索Query,返回TopDocs
			TopDocs topDocs = searcher.search(query, num);
			// 6.显示TopDocs的ScoreDoc
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			disSearchResult(indexPath, fieldName, searchValue, num, searcher, scoreDocs);
			// 9.关闭IndexSearcher
			searcher.close();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search(String indexPath, Query query, int num) {
		try {
			// 1.创建Directory
			Directory directory = getDirectory(indexPath);
			// 2.创建IndexReader
			indexReader = getIndexReader(directory);
			// 3.使用IndexReader创建IndexSearcher
			IndexSearcher searcher = getIndexSearcher(indexReader);
			// 4.创建Query
			// 5.使用IndexSearcher搜索Query,返回TopDocs
			TopDocs topDocs = searcher.search(query, num);
			// 6.显示TopDocs的ScoreDoc
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			disSearchResult(indexPath, query.toString(), null, num, searcher, scoreDocs);
			// 9.关闭IndexSearcher
			searcher.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchPage(String indexPath, Query query, int indexPage, int pageNum) {
		try {
			// 1.创建Directory
			Directory directory = getDirectory(indexPath);
			// 2.创建IndexReader
			indexReader = getIndexReader(directory);
			// 3.使用IndexReader创建IndexSearcher
			IndexSearcher searcher = getIndexSearcher(indexReader);
			// 4.创建Query
			// 5.使用IndexSearcher搜索Query,返回TopDocs
			TopDocs topDocs = searcher.search(query, indexPage * pageNum);
			// 6.显示TopDocs的ScoreDoc
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			ScoreDoc[] pageScoreDocs = new ScoreDoc[pageNum];
			int start = (indexPage - 1) * pageNum;
			int end = indexPage * pageNum;
			int count = 0;
			for (int i = start; i < end; i++) {
				pageScoreDocs[count++] = scoreDocs[i];
			}
			disSearchResult(indexPath, query.toString(), null, indexPage * pageNum, searcher, pageScoreDocs);
			// 9.关闭IndexSearcher
			searcher.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disReaderInfo(String indexPath) throws IOException {
		indexReader = getIndexReader(FSDirectory.open(new File(indexPath)));
		logger.info("maxDoc:" + indexReader.maxDoc());
		logger.info("numDeletedDocs:" + indexReader.numDeletedDocs());
		logger.info("numDocs:" + indexReader.numDocs());
	}

	/**
	 * 创建IndexSearcher
	 * 
	 * @param indexReader
	 * @return
	 */
	private IndexSearcher getIndexSearcher(IndexReader indexReader) {
		// TODO Auto-generated method stub
		IndexSearcher indexSearcher = null;
		try {
			IndexReader reader = IndexReader.openIfChanged(indexReader);
			if (reader != null) {
				indexReader.close();// 关闭旧的InderReader
				indexReader = reader;
			}
			indexSearcher = new IndexSearcher(indexReader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return indexSearcher;
	}

	private IndexReader getIndexReader(Directory directory) {
		// TODO Auto-generated method stub
		if (indexReader == null) {
			synchronized (SearchTool.class) {
				if (indexReader == null) {
					try {
						indexReader = IndexReader.open(directory);
					} catch (CorruptIndexException e) {
						// TODO Auto-generated catch blosck
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return indexReader;
	}

	private void disSearchResult(String indexPath, String fieldName, String searchValue, int num,
			IndexSearcher searcher, ScoreDoc[] scoreDocs) throws IOException {
		logger.info("indexPath:" + indexPath + "\tfieldName:" + fieldName + "\tsearchValue:" + searchValue + "\tnum:"
				+ num);
		for (int i = 0; i < scoreDocs.length; i++) {
			// 7.通过IndexReader/docId获取Document
			Document document = searcher.doc(scoreDocs[i].doc);
			// 8.通过Document的get获取字段
			logger.info(scoreDocs[i].doc+":"+data.fieldNames[0] + ":" + document.get(data.fieldNames[0]) + "\t" + data.fieldNames[1] + ":"
					+ document.get(data.fieldNames[1]) + "\t" + data.fieldNames[2] + ":"
					+ document.get(data.fieldNames[2]) + "\t" + data.fieldNames[3] + ":"
					+ document.get(data.fieldNames[3]) + "\t" + data.fieldNames[4] + ":"
					+ document.get(data.fieldNames[4]));
		}
	}

	private Query createQuery(String fieldName, String searchValue) throws ParseException {
		Analyzer a = createAnalyzer();
		QueryParser parser = new QueryParser(Version.LUCENE_35, fieldName, a);
		Query query = parser.parse(searchValue);
		logger.info(query.toString());
		return query;
	}

	private Directory getDirectory(String indexPath) throws IOException {
		Directory directory = FSDirectory.open(new File(indexPath));
		return directory;
	}

	private Analyzer createAnalyzer() {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		return analyzer;
	}

	public Analyzer getAnalyzer() {
		return createAnalyzer();
	}

}
