package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKTokenizer;

import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MaxWordSeg;
import com.chenlb.mmseg4j.analysis.MMSegTokenizer;

public class SamewordAnalyzer extends Analyzer {

	private SamewordContext samewordContext;
	
	public SamewordAnalyzer(SamewordContext samewordContext){
		this.samewordContext=samewordContext;
		
	}
	
	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		// TODO Auto-generated method stub
		//return new SamewordFilter(new LowerCaseFilter(Version.LUCENE_35, new IKTokenizer(reader,true)),samewordContext);
		//使用mmseg分词器
		return new SamewordFilter(new MMSegTokenizer(new MaxWordSeg(Dictionary.getInstance()), reader), samewordContext);
	}

}
