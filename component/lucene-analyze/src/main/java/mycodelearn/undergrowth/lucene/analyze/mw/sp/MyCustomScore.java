package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.function.CustomScoreProvider;
import org.apache.lucene.search.function.CustomScoreQuery;
import org.apache.lucene.search.function.ValueSourceQuery;

public class MyCustomScore {

	
	
	public class MyCustomScoreQuery extends CustomScoreQuery{

		public MyCustomScoreQuery(Query subQuery, ValueSourceQuery valSrcQuery) {
			super(subQuery, valSrcQuery);
		}
		
		@Override
		protected CustomScoreProvider getCustomScoreProvider(IndexReader reader) throws IOException {
			// TODO Auto-generated method stub
			return new MyCustomScoreProvider(reader);
		}
	}
	
	public class MyCustomScoreProvider extends CustomScoreProvider{

		private String[] filenames;
		
		public MyCustomScoreProvider(IndexReader reader) {
			super(reader);
			try {
				filenames=FieldCache.DEFAULT.getStrings(reader, "name");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public float customScore(int doc, float subQueryScore, float valSrcScore) throws IOException {
			// TODO Auto-generated method stub
			String filename=filenames[doc];
			if (filename.endsWith("dd")) {
				return subQueryScore*valSrcScore*100;
			}
			return super.customScore(doc, subQueryScore, valSrcScore);
		}
	}
	
}
