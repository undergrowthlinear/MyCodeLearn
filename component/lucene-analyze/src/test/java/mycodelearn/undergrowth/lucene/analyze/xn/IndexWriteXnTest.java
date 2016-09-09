package mycodelearn.undergrowth.lucene.analyze.xn;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenlb.mmseg4j.analysis.SimpleAnalyzer;

public class IndexWriteXnTest {

	private Logger logger = LoggerFactory.getLogger(IndexWriteXnTest.class);

	ExecutorService es;
	ScheduledExecutorService fixExecutorService;

	public void before() throws IOException {
		es = Executors.newCachedThreadPool();
		fixExecutorService = Executors.newSingleThreadScheduledExecutor();
	}

	public static void main(String[] args) {
		IndexWriteXnTest indexWriteXnTest = new IndexWriteXnTest();
		// Lucene Document的域名
		String indexPath = "D:\\lucene\\51ctoLucene35\\mmsegAnalyzerDemo\\index";
		String dicPath = "D:\\lucene\\data";
		Analyzer analyzer = new SimpleAnalyzer(dicPath);
		Directory directory = null;
		IndexWriter iwriter = null;
		IndexReader ireader = null;
		IndexSearcher isearcher = null;
		try {
			indexWriteXnTest.before();
			// 建立内存索引对象
			// directory = new RAMDirectory();
			directory = FSDirectory.open(new File(indexPath));
			// 配置IndexWriterConfig
			IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_35, analyzer);
			iwriter = new IndexWriter(directory, iwConfig);
			iwriter.deleteAll();
			indexWriteXnTest.addIndex(iwriter);

			// 搜索过程**********************************
			// 实例化搜索器
			ireader = IndexReader.open(directory);
			isearcher = new IndexSearcher(ireader);

			indexWriteXnTest.search(isearcher, analyzer);
			System.in.read();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ireader != null) {
				try {
					ireader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (directory != null) {
				try {
					directory.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void search(final IndexSearcher isearcher, final Analyzer analyzer) {
		es.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				long startTime = System.currentTimeMillis();
				try {
					for (int i = 0;; i++) {
						String keyword = "米线";
						String fieldName = "name";
						// String keyword = "计算机算法";
						// 使用QueryParser查询分析器构造Query对象
						QueryParser qp = new QueryParser(Version.LUCENE_35, fieldName, analyzer);
						qp.setDefaultOperator(QueryParser.AND_OPERATOR);
						Query query = qp.parse(keyword);
						// 采用TermQuery
						// Query query=new TermQuery(new Term(fieldName,
						// keyword));
						//System.out.println("Query = " + query);

						// 搜索相似度最高的5条记录
						TopDocs topDocs = isearcher.search(query, 5);
						System.out.println(i+"次查询,Query = " + query+"命中：" + topDocs.totalHits);
					}
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	private void addIndex(final IndexWriter iwriter) {
		es.submit(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				// TODO Auto-generated method stub
				for (int i = 0;; i++) {
					long startTime = System.currentTimeMillis();
					Document document = createDocument(i);
					iwriter.addDocument(document);
					logger.info("新建"+i+"索引总耗时:"+(System.currentTimeMillis()-startTime));
				}
				// logger.info("新建索引总耗时:"+(System.currentTimeMillis()-startTime));
				// return null;
			}
		});
		final int periodTime = 60;
		fixExecutorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					iwriter.commit();
					logger.info("每隔" + periodTime + "时间开始提交索引数据");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, 10, periodTime, TimeUnit.SECONDS);

	}

	private Document createDocument(int i) {
		Document document = new Document();
		document.add(new Field("id", i + "", Store.NO, Index.NOT_ANALYZED));
		document.add(new Field("name", "test_蒙自源,云南米线_" + i, Store.YES, Index.ANALYZED));
		document.add(new Field("address", "test_广东省广州市天河区电信规划设计院_" + i, Store.YES, Index.ANALYZED));
		document.add(new Field("phone", "test_13786788_" + i, Store.YES, Index.NOT_ANALYZED));
		document.add(new Field("description", "test_江山好改本性难移_" + i, Store.YES, Index.ANALYZED));
		return document;
	}
}
