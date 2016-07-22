package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.util.Version;

public class MyQueryParser extends QueryParser {

	public MyQueryParser(Version matchVersion, String f, Analyzer a) {
		super(matchVersion, f, a);
	}

	@Override
	protected org.apache.lucene.search.Query getWildcardQuery(String field, String termStr) throws ParseException {
		// TODO Auto-generated method stub
		throw new ParseException("disable wildcardquery");
	}
	
	
	@Override
	protected org.apache.lucene.search.Query getRangeQuery(String field, String part1, String part2, boolean inclusive)
			throws ParseException {
		// TODO Auto-generated method stub
		if ("size".equals(field)) {
			return NumericRangeQuery.newIntRange(field, Integer.valueOf(part1), Integer.valueOf(part2), inclusive, inclusive);
		}
		return super.getRangeQuery(field, part1, part2, inclusive);
	}
}
