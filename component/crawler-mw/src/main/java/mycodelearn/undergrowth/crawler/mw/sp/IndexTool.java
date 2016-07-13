package mycodelearn.undergrowth.crawler.mw.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* createIndex
//1.创建Directory
//2.创建IndexWriter
//3.创建Document
//4.为Document创建Field
//5.使用IndexWriter将Document写入索引
//6.关闭InderWriter
 * 
 * Search:
 * //1.创建Directory
//2.创建IndexReader
//3.使用IndexReader创建IndexSearcher
//4.创建Query
//5.使用IndexSearcher搜索Query,返回TopDocs
//6.显示TopDocs的ScoreDoc
//7.通过IndexReader/docId获取Document
//8.通过Document的get获取字段
 //9.关闭IndexReader
* @author <a href="undergrowth@126.com">undergrowth</a>
* @date 2016年7月13日
* @version 1.0.0
 */
public class IndexTool {

	private Logger logger = LoggerFactory.getLogger(IndexTool.class);
	private String[] fieldNames={"id","email","content","attach"};
	private String[] ids={"1","2","3","4","5"};
	private String[] emails={"undergrowth@126.com","undergrowth@163.com","undergrowth@gamil.com","undergrowth@qq.com","undergrowth@139.com"};
	private String[] contents={"126 email welcome you","163 is wangyi product","gmail is google ,it is great","qq is tencent,it is free","139 is mobile,it charge money"};
	private int[] attachs={5,7,2,9,3};

	public void index(String indexPath, String docPath) {
		IndexWriter indexWriter = null;
		try {
			// 1.创建Directory
			Directory directory = createDirectory(indexPath);
			// 2.创建IndexWriter
			indexWriter = createIndexWriter(directory);
			// 3.创建Document
			writeDocument(indexWriter, null);
			// 6.关闭InderWriter

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (indexWriter != null)
					indexWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void search(String indexPath, String fieldName, String searchValue, int num) {
		IndexReader reader = null;
		try {
			// 1.创建Directory
			Directory directory = createDirectory(indexPath);
			// 2.创建IndexReader
			reader = DirectoryReader.open(directory);
			// 3.使用IndexReader创建IndexSearcher
			IndexSearcher searcher = new IndexSearcher(reader);
			// 4.创建Query
			Query query = createQuery(fieldName, searchValue);
			// 5.使用IndexSearcher搜索Query,返回TopDocs
			TopDocs topDocs = searcher.search(query, num);
			// 6.显示TopDocs的ScoreDoc
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			disSearchResult(indexPath, fieldName, searchValue, num, searcher, scoreDocs);
			// 9.关闭IndexReader

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void disSearchResult(String indexPath, String fieldName, String searchValue, int num,
			IndexSearcher searcher, ScoreDoc[] scoreDocs) throws IOException {
		logger.info("indexPath:" + indexPath + "\tfieldName:" + fieldName + "\tsearchValue:" + searchValue + "\tnum:"
				+ num);
		for (int i = 0; i < scoreDocs.length; i++) {
			// 7.通过IndexReader/docId获取Document
			Document document = searcher.doc(scoreDocs[i].doc);
			// 8.通过Document的get获取字段
			logger.info(fieldNames[0]+":" + document.get(fieldNames[0]) + "\t"+fieldNames[1]+":" + document.get(fieldNames[1]) + "\t"
					+fieldNames[2]+":" + document.get(fieldNames[2])+"\t"+fieldNames[3]+":" +document.get(fieldNames[3]));
		}
	}

	private Query createQuery(String fieldName, String searchValue) throws ParseException {
		Analyzer a = createAnalyzer();
		QueryParser parser = new QueryParser(fieldName, a);
		Query query = parser.parse(searchValue);
		logger.info(query.toString());
		return query;
	}

	private Directory createDirectory(String indexPath) throws IOException {
		Directory directory = FSDirectory.open(Paths.get(indexPath));
		return directory;
	}

	@Deprecated
	private void addDocument(String docPath, IndexWriter indexWriter) throws FileNotFoundException, IOException {
		
	}

	private void writeDocument(IndexWriter indexWriter, File file) throws FileNotFoundException, IOException {
		// 4.为Document创建Field
		for (int i = 0; i < ids.length; i++) {
		Document document = addDocumentField(file,i);
		// 5.使用IndexWriter将Document写入索引
		indexWriter.addDocument(document);
		}
	}

	private IndexWriter createIndexWriter(Directory directory) throws IOException {
		Analyzer analyzer = createAnalyzer();
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		conf.setOpenMode(OpenMode.CREATE);
		IndexWriter indexWriter = new IndexWriter(directory, conf);
		return indexWriter;
	}

	private Analyzer createAnalyzer() {
		Analyzer analyzer = new StandardAnalyzer();
		return analyzer;
	}

	private Document addDocumentField(File file,int i) throws FileNotFoundException {
		Document document = new Document();
		// indexed tokenized stored
			document.add(new StringField(fieldNames[0], ids[i], Field.Store.YES));
			document.add(new TextField(fieldNames[1], emails[i], Field.Store.YES));
			document.add(new TextField(fieldNames[2], new StringReader(contents[i])));
			document.add(new IntPoint(fieldNames[3], attachs[i]));
		logger.info("add document:" + document);
		return document;
	}
	
}
