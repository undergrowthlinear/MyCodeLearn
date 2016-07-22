package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
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
public class IndexFileTool {

	private Logger logger = LoggerFactory.getLogger(IndexFileTool.class);
	private String[] data = { "name", "path", "content", "size", "lastTime","length","id" };
	private static IndexWriter indexWriter = null;
	private HashSet<Integer> randomNumSet=new HashSet<>();
	private Random randomNum=new Random();

	public IndexFileTool() {

	}

	public void index(String indexPath, String docPath) {
		try {
			// 1.创建Directory
			Directory directory = getDirectory(indexPath);
			// 2.创建IndexWriter
			indexWriter = getIndexWriter(directory);
			// 3.创建Document
			writeDocument(indexWriter, new File(docPath));
			// 6.提交InderWriter
			indexWriter.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(String indexPath, Query query) {
		try {
			logger.info("query:" + query);
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

	public void update(String indexPath, Term term, String updateFileNamePath) {
		try {
			logger.info("query:" + term);
			// 1.创建Directory
			Directory directory = getDirectory(indexPath);
			// 2.创建IndexWriter
			indexWriter = getIndexWriter(directory);
			indexWriter.updateDocument(term, addDocumentField(new File(updateFileNamePath)));
			;
			// 6.提交InderWriter
			indexWriter.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Directory getDirectory(String indexPath) throws IOException {
		Directory directory = FSDirectory.open(new File(indexPath));
		return directory;
	}

	private void writeDocument(IndexWriter indexWriter, File file) throws FileNotFoundException, IOException {
		// 4.为Document创建Field
		indexWriter.deleteAll();
		for (File indexFile : file.listFiles()) {
			Document document = addDocumentField(indexFile);
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
			synchronized (IndexFileTool.class) {
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

	private Document addDocumentField(File file) throws FileNotFoundException {
		Document document = new Document();
		// indexed tokenized stored
		// "name","path","content","size","lastTime"
		document.add(new Field(data[0], file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		document.add(new Field(data[1], file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		document.add(new Field(data[2], new FileReader(file)));
		document.add(new NumericField(data[3], Field.Store.YES, true).setIntValue((int) (file.length())));
		document.add(new NumericField(data[4], Field.Store.YES, true).setLongValue(file.lastModified()));
		document.add(new Field(data[5], String.valueOf(file.length()), Field.Store.YES, Field.Index.NOT_ANALYZED));
		document.add(new Field(data[6], String.valueOf(getRandm()), Field.Store.YES, Field.Index.NOT_ANALYZED));
		logger.info("add document:" + document);
		return document;
	}

	private int getRandm() {
		int ran=randomNum.nextInt(1000);
		while (!randomNumSet.add(ran)) {
			ran=randomNum.nextInt(1000);
		};
		return ran;
	}

}
