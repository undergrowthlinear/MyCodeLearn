package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.util.Map;

public class SimpleSamewordContext implements SamewordContext {

	Map<String, String[]> samewordsMap;

	public SimpleSamewordContext(Map<String, String[]> samewordsMap) {
		this.samewordsMap = samewordsMap;
	}

	@Override
	public String[] getSamewords(String name) {
		// TODO Auto-generated method stub
		return samewordsMap.get(name);
	}

	@Override
	public String toString() {
		return "SimpleSamewordContext [samewordsMap=" + samewordsMap + "]";
	}
	
	

}
