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
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
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
public class SearchFileTool {

	private Logger logger = LoggerFactory.getLogger(SearchFileTool.class);
	private String[] data = { "name", "path", "content", "size", "lastTime", "length","id"  };
	private static IndexReader indexReader = null;

	public SearchFileTool() {
	}

	public void search(String indexPath, String fieldName, String searchValue, int num) {
		search(indexPath, fieldName, searchValue, num, Sort.RELEVANCE);
	}

	public void search(String indexPath, Query query, int num) {
		search(indexPath, query, num, Sort.RELEVANCE);
	}
	
	public void search(String indexPath, String fieldName, String searchValue, int num,Sort sort) {
		try {
			// 4.创建Query
			Query query = createQuery(fieldName, searchValue);
			search(indexPath, query, num, sort);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search(String indexPath, Query query, int num,Sort sort) {
		try {
			IndexSearcher searcher = getIndexSearcher(indexPath);
			// 4.创建Query
			// 5.使用IndexSearcher搜索Query,返回TopDocs
			TopDocs topDocs = searcher.search(query, num,sort);
			// 6.显示TopDocs的ScoreDoc
			disResult(indexPath, query, num, searcher, topDocs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void disResult(String indexPath, Query query, int num, IndexSearcher searcher, TopDocs topDocs)
			throws IOException {
		disSearchResult(indexPath, query.toString(), null, num, searcher, topDocs.scoreDocs);
		// 9.关闭IndexSearcher
		searcher.close();
	}
	
	public void search(String indexPath, Query query, int num,Filter filter) {
		try {
			IndexSearcher searcher = getIndexSearcher(indexPath);
			// 4.创建Query
			logger.info("filter:"+filter.toString());
			// 5.使用IndexSearcher搜索Query,返回TopDocs
			TopDocs topDocs = searcher.search(query,filter, num);
			disResult(indexPath, query, num, searcher, topDocs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void searchPage(String indexPath, Query query, int indexPage, int pageNum) {
		try {
			IndexSearcher searcher = getIndexSearcher(indexPath);
			// 4.创建Query
			// 5.使用IndexSearcher搜索Query,返回TopDocs
			int num = indexPage * pageNum;
			TopDocs topDocs = searcher.search(query, num);
			// 6.显示TopDocs的ScoreDoc
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			int disNum = 0;
			int start = (indexPage - 1) * pageNum;
			if (scoreDocs.length == num) {
				disNum = pageNum;
			} else if (scoreDocs.length > (indexPage - 1) * pageNum && scoreDocs.length < num) {
				disNum = scoreDocs.length - (indexPage - 1) * pageNum;
			} else {
				disNum = 0;
				start = scoreDocs.length;
			}
			ScoreDoc[] pageScoreDocs = new ScoreDoc[disNum];
			int count = 0;
			for (int i = start; i < scoreDocs.length; i++) {
				pageScoreDocs[count++] = scoreDocs[i];
			}
			disSearchResult(indexPath, query.toString(), null, num, searcher, pageScoreDocs);
			// 9.关闭IndexSearcher
			searcher.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchPageAfter(String indexPath, Query query, int indexPage, int pageNum) {
		try {
			IndexSearcher searcher = getIndexSearcher(indexPath);
			// 4.创建Query
			// 5.使用IndexSearcher搜索Query,返回TopDocs
			ScoreDoc after = getLastScoreDoc(searcher, query, indexPage, pageNum);
			ScoreDoc[] scoreDocs = null;
			if (indexPage != 1 && after == null) {// 表示超出了范围
				scoreDocs = null;
			} else {
				TopDocs topDocs = searcher.searchAfter(after, query, pageNum);
				scoreDocs = topDocs.scoreDocs;
			}
			disSearchResult(indexPath, query.toString(), null, indexPage * pageNum, searcher, scoreDocs);
			// 9.关闭IndexSearcher
			searcher.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private IndexSearcher getIndexSearcher(String indexPath) throws IOException {
		// 1.创建Directory
		Directory directory = getDirectory(indexPath);
		// 2.创建IndexReader
		indexReader = getIndexReader(directory);
		// 3.使用IndexReader创建IndexSearcher
		IndexSearcher searcher = getIndexSearcher(indexReader);
		return searcher;
	}

	/**
	 * 查找前一页的最后一个元素
	 * 
	 * @param searcher
	 * @param query
	 * @param indexPage
	 * @param pageNum
	 * @return
	 * @throws IOException
	 */
	private ScoreDoc getLastScoreDoc(IndexSearcher searcher, Query query, int indexPage, int pageNum)
			throws IOException {
		// TODO Auto-generated method stub
		if (indexPage == 1)
			return null;
		int num = (indexPage - 1) * pageNum;
		TopDocs topDocs = searcher.search(query, num);
		if (topDocs.totalHits < num) {
			return null;
		}
		return topDocs.scoreDocs[num - 1];
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
			synchronized (SearchFileTool.class) {
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
		if (scoreDocs != null && scoreDocs.length > 0) {
			logger.info("scoreDocs.length:"+scoreDocs.length);
			for (ScoreDoc scoreDoc : scoreDocs) {
				// 7.通过IndexReader/docId获取Document
				if (scoreDoc != null) {
					Document document = searcher.doc(scoreDoc.doc);
					// 8.通过Document的get获取字段
					logger.info(scoreDoc.doc + ":" +"["+scoreDoc.score+"]"+ data[0] + ":" + document.get(data[0]) + "\t" + data[1] + ":"
							+ document.get(data[1]) + "\t" + data[2] + ":" + document.get(data[2]) + "\t" + data[3]
							+ ":" + document.get(data[3]) + "\t" + data[4] + ":" + document.get(data[4]) + "\t"
							+ data[5] + ":" + document.get(data[5])+ "\t"+data[6] + ":" + document.get(data[6]));
				}
			}
		} else {
			logger.info("no more result");
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
