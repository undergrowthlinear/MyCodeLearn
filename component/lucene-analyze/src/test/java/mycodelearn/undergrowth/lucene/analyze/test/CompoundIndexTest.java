package mycodelearn.undergrowth.lucene.analyze.test;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mycodelearn.undergrowth.lucene.analyze.mw.CompoundIndex;

public class CompoundIndexTest {

	CompoundIndex compoundIndex = null;
	private String indexPath = "D:\\lucene\\51ctoLucene35\\compound\\index";
	private String docPath = "D:\\lucene\\51ctoLucene35\\compound\\docs";

	@Before
	public void setUp() throws Exception {
		compoundIndex = new CompoundIndex();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIndex() {
		compoundIndex.index(indexPath, docPath);
	}

	@Test
	public void testBooleanQuerySearch() {
		TermQuery query=new TermQuery(new Term("name_size", "con1.txt 353"));
		compoundIndex.search(indexPath, query, 10);
	}

}
