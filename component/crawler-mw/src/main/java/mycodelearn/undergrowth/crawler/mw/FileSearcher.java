package mycodelearn.undergrowth.crawler.mw;

import java.io.File;
import java.nio.file.Path;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class FileSearcher {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 需要查询的文本
		String queryString = "委内";
		// 索引目录
		File indexDir = new File("d:\\lucene\\index");
		// 得到索引目录
		FSDirectory directory = FSDirectory.open(indexDir.toPath());
		// 获得读取对象
		IndexReader r = DirectoryReader.open(directory);
		// 获得搜索器对象
		IndexSearcher searcher = new IndexSearcher(r);
		// 查询条件
		Term term = new Term("contents", queryString);
		TermQuery query = new TermQuery(term);
		// 进行搜索 并返回结果
		TopDocs docs = searcher.search(query, 10);
		System.out.println("总共有:" + docs.totalHits);
		ScoreDoc[] scoreDocs = docs.scoreDocs;
		// 遍历结果集
		for (ScoreDoc scoreDoc : scoreDocs) {
			// System.out.println("scoreDoc.doc:"+scoreDoc.doc+"
			// scoreDoc.score:"+scoreDoc.score+"
			// scoreDoc.shardIndex:"+scoreDoc.shardIndex);
			System.out.println(" scoreDoc:" + scoreDoc);
			Document document = searcher.doc(scoreDoc.doc);
			System.out.println("file:" + document.get("path"));
			System.out.println("contents:" + document.get("contents"));
		}
	}

}
