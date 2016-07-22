package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.DocIdSet;
import org.apache.lucene.search.Filter;
import org.apache.lucene.util.OpenBitSet;

public class MyIdFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3622192129241491833L;

	private FilterAccessor filterAccess;
	
	
	
	public MyIdFilter(FilterAccessor filterAccess) {
		super();
		this.filterAccess = filterAccess;
	}



	@Override
	public DocIdSet getDocIdSet(IndexReader reader) throws IOException {
		// TODO Auto-generated method stub
		OpenBitSet openBitSet=new OpenBitSet();
		if (filterAccess.getState()) {
			setDoc(openBitSet,reader);
		}else {
			clearDoc(openBitSet,reader);
		}
		return openBitSet;
	}



	private void clearDoc(OpenBitSet openBitSet,IndexReader reader) {
		// TODO Auto-generated method stub
		//填满
		openBitSet.set(0, reader.maxDoc());
		//清楚这些已有的
		long[] bits=searchDocIds(openBitSet, reader);
		for (int i = 0; i < bits.length; i++) {
			openBitSet.clear(bits[i]);
		}
	}



	private void setDoc(OpenBitSet openBitSet,IndexReader reader) {
		// TODO Auto-generated method stub
		long[] bits=searchDocIds(openBitSet, reader);
		//填充这些有的
		for (int i = 0; i < bits.length; i++) {
			openBitSet.set(bits[i]);
		}
	}



	private long[] searchDocIds(OpenBitSet openBitSet, IndexReader reader) {
		long[] docIds=new long[filterAccess.values().length];
		int count=0;
		for(String access:filterAccess.values()){
			try {
				//reader从预缓存中获取数据
				TermDocs termDocs=reader.termDocs(new Term(filterAccess.getField(), access));
				int[] docs=new int[1];
				int[] freqs=new int[1];
				termDocs.read(docs, freqs);
				docIds[count++]=((long)docs[0]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return docIds;
	}

}
