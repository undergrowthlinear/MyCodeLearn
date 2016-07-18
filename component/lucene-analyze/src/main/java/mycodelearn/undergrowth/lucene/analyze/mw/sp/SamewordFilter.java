package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.io.IOException;
import java.util.Stack;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SamewordFilter extends TokenFilter {

	private Logger logger = LoggerFactory.getLogger(SamewordFilter.class);

	private SamewordContext samewordContext;
	private CharTermAttribute charTermAttribute;
	private PositionIncrementAttribute positionIncrementAttribute;
	private AttributeSource.State current;
	private Stack<String> samewordStack;

	protected SamewordFilter(TokenStream input, SamewordContext samewordContext) {
		super(input);
		// TODO Auto-generated constructor stub
		this.samewordContext = samewordContext;
		this.charTermAttribute = this.input.addAttribute(CharTermAttribute.class);
		this.positionIncrementAttribute = this.input.addAttribute(PositionIncrementAttribute.class);
		this.samewordStack = new Stack<>();
	}

	@Override
	public boolean incrementToken() throws IOException {
		// TODO Auto-generated method stub
		// logger.info(charTermAttribute.toString());
		// 处理之前放入的同义词
		if (samewordStack.size() > 0) {
			String sa = samewordStack.pop();
			restoreState(current);//恢复到之前的状态
			charTermAttribute.setEmpty();
			charTermAttribute.append(sa);
			positionIncrementAttribute.setPositionIncrement(0);
			return true;
		}

		if (!this.input.incrementToken()) {//判断是否还有下一个元素
			return false;
		}

		if (getSamewords()) {
			current = captureState();// 捕获当前状态
		}

		return true;
	}

	private boolean getSamewords() {
		String[] samewords = samewordContext.getSamewords(charTermAttribute.toString());
		if (samewords != null) {
			for (String sameword : samewords) {
				this.samewordStack.push(sameword);
			}
			return true;
		}
		return false;
	}

}
