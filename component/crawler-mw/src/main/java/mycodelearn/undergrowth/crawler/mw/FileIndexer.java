package mycodelearn.undergrowth.crawler.mw;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class FileIndexer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * String testString=
		 * "委内瑞拉总统马杜罗将于本周末访华。但据外媒报道，委内瑞拉称，美国禁止马杜罗搭乘的飞机过境美国联邦领地波多黎各领空。" +
		 * "根据中方发布的消息，应中国国家主席习近平邀请，马杜罗将于9月21日至24日对中国进行国事访问。";
		 */
		// 索引存放目录
		File indexDir = new File("d:\\lucene\\index\\");
		// 数据文件目录
		File dataDir = new File("d:\\lucene\\docs");
		// 创建一个默认的分词器
		SmartChineseAnalyzer luceneAnalyzer = new SmartChineseAnalyzer(true);
		// 显示默认的停止字符
		/*
		 * Iterator<Object>
		 * setObjects=luceneAnalyzer.getDefaultStopSet().iterator(); for
		 * (Iterator iterator = setObjects; iterator.hasNext();) { char[] type =
		 * (char[]) iterator.next(); System.out.println(type); }
		 */
		// 自定义一个停止字符集
		/*
		 * Collection<String> c=new ArrayList<>(); c.add(","); c.add(" ");
		 * c.add("。"); CharArraySet stopWords=new
		 * CharArraySet(Version.LUCENE_44, c, true); SmartChineseAnalyzer
		 * luceneAnalyzer=new SmartChineseAnalyzer(Version.LUCENE_44,
		 * stopWords); Iterator<Object>
		 * setObjects=luceneAnalyzer.getDefaultStopSet().iterator(); for
		 * (Iterator iterator = setObjects; iterator.hasNext();) { char[] type =
		 * (char[]) iterator.next(); System.out.println(type); }
		 */
		// 引入mmseg4j分词器
		// Analyzer luceneAnalyzer=new ComplexAnalyzer();
		// 得到数据目录下面的所有文件
		File[] dataFile = dataDir.listFiles();
		// 创建一个索引配置对象
		IndexWriterConfig config = new IndexWriterConfig(luceneAnalyzer);
		config.setOpenMode(OpenMode.CREATE);
		// 得到索引存放目录
		Directory directory = FSDirectory.open(indexDir.toPath());
		// 创建一个索引写对象
		IndexWriter indexWriter = new IndexWriter(directory, config);
		// 将需要索引的字段添加到入库文档中 并将入库文档添加到索引中
		for (int i = 0; i < dataFile.length; i++) {
			System.out.println("file:" + dataFile[i].getCanonicalPath());
			Document document = new Document();
			Reader reader = new FileReader(dataFile[i]);
			// 每一行添加path和contents字段
			document.add(new TextField("path", dataFile[i].getCanonicalPath(), Field.Store.YES));
			document.add(new TextField("contents", reader));
			// document.add(new TextField("contents",
			// testString,Field.Store.YES));
			System.out.println("添加path和contents字段");
			indexWriter.addDocument(document);
		}
		// 建立索引文件
		indexWriter.commit();
		// 关闭
		indexWriter.close();
	}

}
