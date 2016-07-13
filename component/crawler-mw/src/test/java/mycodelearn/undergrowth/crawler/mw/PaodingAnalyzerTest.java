package mycodelearn.undergrowth.crawler.mw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月12日
 * @version 1.0.0
 */
public class PaodingAnalyzerTest {

	private IndexWriter write = null;
	private String indexDir = "d:\\lucene\\index\\";
	private String dataDir = "d:\\lucene\\docs";

	
	
	@Test
	public void testPaoding() throws Exception{
		Analyzer analyzer = new PaodingAnalyzer();
		String  indexStr = "我的QQ号码是58472399"; 
        StringReader reader = new StringReader(indexStr); 
        TokenStream ts = analyzer.tokenStream(indexStr, reader); 
       System.out.println(ts);
	}

	@Test
	public void testCreate() throws IOException {
		final Path docDir = Paths.get(dataDir);
		indexDocs(write, docDir);
	}

	/**
	 * 创建索引文件
	 * 
	 * @param write2
	 * @param docDir
	 * @throws IOException
	 */
	private void indexDocs(final IndexWriter writer, Path docDir) throws IOException {
		// TODO Auto-generated method stub
		if (Files.isDirectory(docDir)) {
			Files.walkFileTree(docDir, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					try {
						indexDoc(writer, file);
					} catch (IOException ignore) {
						// don't index files that can't be read.
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} else {
			indexDoc(writer, docDir);
		}
	}

	private void indexDoc(IndexWriter writer, Path file) throws IOException {
		// TODO Auto-generated method stub
		try (InputStream inputStream = Files.newInputStream(file)) {
			Document document = new Document();
			Field titleField = new StringField("title", "test:" + file.toString(), Field.Store.YES);
			document.add(titleField);
			document.add(new TextField("contents", new BufferedReader(new InputStreamReader(inputStream))));
			if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
				System.out.println("add document:" + document.toString());
				writer.addDocument(document);
			} else {
				System.out.println("update document:" + document.toString());
				writer.updateDocument(new Term("title", "test:" + file.toString()), document);
			}
		}
	}

	

}
