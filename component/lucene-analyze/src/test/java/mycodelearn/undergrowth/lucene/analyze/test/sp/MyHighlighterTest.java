package mycodelearn.undergrowth.lucene.analyze.test.sp;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mycodelearn.undergrowth.lucene.analyze.mw.sp.MyHighlighter;

public class MyHighlighterTest {

	private Logger logger = LoggerFactory.getLogger(MyHighlighterTest.class);
	private MyHighlighter myhi=null;
	
	@Before
	public void setUp() throws Exception {
		myhi=new MyHighlighter();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHighlighter() {
		String text="好好学习，天天向上，响应习大大的号召";
		Query query=new TermQuery(new Term("content", "学习"));
		logger.info(myhi.highlighter(query, text));
	}
	
	@Test
	public void testhighlighterSR() {
		String text="好好学习，天天向上，响应习大大的号召";
		myhi.highlighterSR("content", "学习", text);
	}

}
