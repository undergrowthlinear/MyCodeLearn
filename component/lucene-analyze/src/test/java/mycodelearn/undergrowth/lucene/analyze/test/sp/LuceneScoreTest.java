package mycodelearn.undergrowth.lucene.analyze.test.sp;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;


/**
 * 在Lucene中score
 * score(q,d)   =   coord(q,d)  ·  queryNorm(q)  ·  ∑( tf(t in d)  ·  idf(t)2  ·  t.getBoost() ·  norm(t,d) )

                                                                       t in q
                                                                       
norm(t,d)   =   doc.getBoost()  ·  lengthNorm(field)  ·  ∏f.getBoost()
field f in d named as t
 * 简单说是由 tf * idf * boost * lengthNorm计算得出的。 
tf：是查询的词在文档中出现的次数的平方根 
idf：表示反转文档频率，观察了一下所有的文档都一样，所以那就没什么用处，不会起什么决定作用。 
	idf----如何计算
	idf:1+log(numDocs/(frqDocs+1))
boost：激励因子，可以通过setBoost方法设置，需要说明的通过field和doc都可以设置，所设置的值会同时起作用
lengthNorm：是由搜索的field的长度决定了，越长文档的分值越低。 
	lengthNorm----如何计算
	lengthNorm(field) = (1.0 / Math.sqrt(numTerms))
还有个问题，为什么一次查询后最大的分值总是1.0呢？ 
因为Lucene会把计算后，最大分值超过1.0的分值作为分母，其他的文档的分值都除以这个最大值，计算出最终的得分。
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="undergrowth@126.com">undergrowth</a>
* @date 2016年7月25日
* @version 1.0.0
 */
public class LuceneScoreTest {
	public String INDEX_STORE_PATH = "lucene/index";

	Directory directory = null;
	Analyzer analyzer = null;

	@Before
	public void before() {
		try {
			INDEX_STORE_PATH = LuceneScoreTest.class.getClassLoader().getResource(INDEX_STORE_PATH).getPath();
			directory = FSDirectory.open(new File(INDEX_STORE_PATH));
			analyzer = new StandardAnalyzer(Version.LUCENE_35);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testIndex() {
		try {
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, analyzer);
			IndexWriter writer = new IndexWriter(directory, config);
			writer.deleteAll();
			Document doc1 = new Document();
			Document doc2 = new Document();
			Document doc3 = new Document();
			Document doc4 = new Document();
			Document doc5 = new Document();

			Field f1 = new Field("bookname", "bc bc", Field.Store.YES, Field.Index.ANALYZED);
			Field f2 = new Field("bookname", "ab bc", Field.Store.YES, Field.Index.ANALYZED);
			f2.setBoost(2f);
			Field f3 = new Field("bookname", "ab bc cd", Field.Store.YES, Field.Index.ANALYZED);
			Field f4 = new Field("bookname", "bc", Field.Store.YES, Field.Index.ANALYZED);
			Field f5 = new Field("bookname", "bd", Field.Store.YES, Field.Index.ANALYZED);
			
			doc1.add(f1);
			doc2.add(f2);
			doc2.setBoost(2f);
			doc3.add(f3);
			doc4.add(f4);
			doc5.add(f5);

			writer.addDocument(doc1);
			writer.addDocument(doc2);
			writer.addDocument(doc3);
			writer.addDocument(doc4);
			writer.addDocument(doc5);

			writer.close();
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSearch() {
		try {
			IndexReader indexReader = IndexReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(indexReader);
			Term term=new Term("bookname", "bc");
			PhraseQuery q=new PhraseQuery();
			q.add(term);
			//TermQuery q = new TermQuery(term);
			TopDocs hits = searcher.search(q, 500);
			ScoreDoc[] scoreDocs = hits.scoreDocs;
			for (int i = 0; i < scoreDocs.length; i++) {
				Document doc = searcher.doc(scoreDocs[i].doc);
				System.out.print(doc.get("bookname") + "\t\t");
				System.out.println(scoreDocs[i].score);
				System.out.println(searcher.explain(q, scoreDocs[i].doc));//
			}
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchBoost() {
		try {
			IndexReader indexReader = IndexReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(indexReader);
			TermQuery q = new TermQuery(new Term("bookname", "bc"));
			q.setBoost(2f);
			TopDocs hits = searcher.search(q, 500);
			ScoreDoc[] scoreDocs = hits.scoreDocs;
			for (int i = 0; i < scoreDocs.length; i++) {
				Document doc = searcher.doc(scoreDocs[i].doc);
				System.out.print(doc.get("bookname") + "\t\t");
				System.out.println(scoreDocs[i].score);
				System.out.println(searcher.explain(q, scoreDocs[i].doc));//
			}
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
