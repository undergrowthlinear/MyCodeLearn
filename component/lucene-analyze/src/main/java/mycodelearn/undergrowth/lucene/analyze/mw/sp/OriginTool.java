package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
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
public class OriginTool {

	private Logger logger = LoggerFactory.getLogger(OriginTool.class);
	private String[] fieldNames = { "id", "email", "content", "attach", "date" };
	private String[] ids = { "1", "2", "3", "4", "5" };
	private String[] emails = { "undergrowth@126.com", "undergrowth@163.com", "undergrowth@gamil.com",
			"undergrowth@qq.com", "undergrowth@139.com" };
	private String[] contents = { "126 email welcome you", "163 email is wangyi product",
			"gmail email email is google ,it is great", "qq email is tencent,it is free",
			"139 email is mobile,it charge money" };
	private int[] attachs = { 5, 7, 2, 9, 3 };
	private Date[] dates = null;

	private static IndexReader indexReader = null;
	private static IndexWriter indexWriter = null;

	public OriginTool() {
		initDatesData();
	}

	private void initDatesData() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		dates = new Date[attachs.length];
		for (int i = 0; i < attachs.length; i++) {
			try {
				dates[i] = format.parse("2016-7-" + (15 - i));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void index(String indexPath, String docPath) {
		try {
			// 1.创建Directory
			Directory directory = getDirectory(indexPath);
			// 2.创建IndexWriter
			indexWriter = getIndexWriter(directory);
			// 3.创建Document
			writeDocument(indexWriter, null);
			// 6.提交InderWriter
			indexWriter.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public void delete(String indexPath, Query query) {
		try {
			logger.info("query:"+query);
			// 1.创建Directory
			Directory directory = getDirectory(indexPath);
			// 2.创建IndexWriter
			indexWriter = getIndexWriter(directory);
			indexWriter.deleteDocuments(query);
			// 6.提交InderWriter
			indexWriter.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(String indexPath, Term term) {
		try {
			logger.info("query:"+term);
			// 1.创建Directory
			Directory directory = getDirectory(indexPath);
			// 2.创建IndexWriter
			indexWriter = getIndexWriter(directory);
			indexWriter.updateDocument(term, addDocumentField(null, 0));;
			// 6.提交InderWriter
			indexWriter.commit();
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
			synchronized (OriginTool.class) {
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
			logger.info(fieldNames[0] + ":" + document.get(fieldNames[0]) + "\t" + fieldNames[1] + ":"
					+ document.get(fieldNames[1]) + "\t" + fieldNames[2] + ":" + document.get(fieldNames[2]) + "\t"
					+ fieldNames[3] + ":" + document.get(fieldNames[3]) + "\t" + fieldNames[4] + ":"
					+ document.get(fieldNames[4]));
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

	private void writeDocument(IndexWriter indexWriter, File file) throws FileNotFoundException, IOException {
		// 4.为Document创建Field
		indexWriter.deleteAll();
		for (int i = 0; i < ids.length; i++) {
			Document document = addDocumentField(file, i);
			// 5.使用IndexWriter将Document写入索引
			indexWriter.addDocument(document);
		}
	}

	/**
	 * 单例保存indexWriter
	 * 
	 * @param directory
	 * @return indexWriter
	 * @throws IOException
	 */
	private IndexWriter getIndexWriter(Directory directory) throws IOException {
		if (indexWriter == null) {
			synchronized (OriginTool.class) {
				if (indexWriter == null) {
					Analyzer analyzer = createAnalyzer();
					IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_35, analyzer);
					indexWriter = new IndexWriter(directory, conf);
				}
			}
		}
		return indexWriter;
	}

	private Analyzer createAnalyzer() {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		return analyzer;
	}
	
	public Analyzer getAnalyzer() {
		return createAnalyzer();
	}

	private Document addDocumentField(File file, int i) throws FileNotFoundException {
		Document document = new Document();
		// indexed tokenized stored
		document.add(new Field(fieldNames[0], ids[i], Field.Store.YES, Field.Index.ANALYZED));
		document.add(new Field(fieldNames[1], emails[i], Field.Store.YES, Field.Index.ANALYZED));
		document.add(new Field(fieldNames[2], new StringReader(contents[i])));
		document.add(new NumericField(fieldNames[3], Field.Store.YES, true).setIntValue(attachs[i]));
		document.add(new NumericField(fieldNames[4], Field.Store.YES, true).setLongValue(dates[i].getTime()));
		logger.info("add document:" + document);
		return document;
	}

}
