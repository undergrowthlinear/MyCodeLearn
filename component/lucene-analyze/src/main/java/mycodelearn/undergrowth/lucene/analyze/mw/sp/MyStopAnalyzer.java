package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月17日
 * @version 1.0.0
 */
public class MyStopAnalyzer extends Analyzer {

	private Set stopWords;

	public MyStopAnalyzer() {
		stopWords = StopAnalyzer.ENGLISH_STOP_WORDS_SET;
	}

	public MyStopAnalyzer(String[] stopWords) {
		this.stopWords = StopFilter.makeStopSet(Version.LUCENE_35, stopWords);
		this.stopWords.add(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		// TODO Auto-generated method stub
		return new StopFilter(Version.LUCENE_35, new LowerCaseFilter(Version.LUCENE_35, new LetterTokenizer(reader)),
				stopWords, true);
	}

}
