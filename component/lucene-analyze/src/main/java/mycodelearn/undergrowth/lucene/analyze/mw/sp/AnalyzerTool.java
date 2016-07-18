package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用) 分词器工具
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月17日
 * @version 1.0.0
 */
public class AnalyzerTool {

	private Logger logger = LoggerFactory.getLogger(AnalyzerTool.class);

	public void disTokenStreamInfo(String text, Analyzer analyzer) {
		try {
			TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
			logger.info("text:" + text+"\t"+"analyzer:"+analyzer.toString());
			while (tokenStream.incrementToken()) {
				// 碗模型
				CharTermAttribute cta = tokenStream.addAttribute(CharTermAttribute.class);
				OffsetAttribute oa = tokenStream.addAttribute(OffsetAttribute.class);
				PositionIncrementAttribute pia = tokenStream.addAttribute(PositionIncrementAttribute.class);
				TypeAttribute ta = tokenStream.addAttribute(TypeAttribute.class);
				logger.info(pia.getPositionIncrement() + ":" + cta + "[" + oa.startOffset() + "-" + oa.endOffset() + "]"
						+ ":" + ta.type());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
