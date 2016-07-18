package mycodelearn.undergrowth.lucene.analyze.test.sp;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.util.Version;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mycodelearn.undergrowth.lucene.analyze.mw.sp.AnalyzerTool;
import mycodelearn.undergrowth.lucene.analyze.mw.sp.MyStopAnalyzer;

public class MyStopAnalyzerTest {

	private Logger logger = LoggerFactory.getLogger(MyStopAnalyzerTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMyStopAnalyzer() {
		String text = "how are you thank you";
		AnalyzerTool analyzerTool = new AnalyzerTool();
		Analyzer sa = new StopAnalyzer(Version.LUCENE_35);
		Analyzer msa = new MyStopAnalyzer();
		analyzerTool.disTokenStreamInfo(text, sa);
		logger.info("--------分割线--------");
		analyzerTool.disTokenStreamInfo(text, msa);
		logger.info("--------分割线--------");

	}
	
	@Test
	public void testMyStopAnalyzerAddStopWords() {
		String text = "how are you thank you";
		AnalyzerTool analyzerTool = new AnalyzerTool();
		Analyzer sa = new StopAnalyzer(Version.LUCENE_35);
		Analyzer msa = new MyStopAnalyzer(new String[]{"you"});
		analyzerTool.disTokenStreamInfo(text, sa);
		logger.info("--------分割线--------");
		analyzerTool.disTokenStreamInfo(text, msa);
		logger.info("--------分割线--------");

	}

}
