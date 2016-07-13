package mycodelearn.undergrowth.crawler.mw.test.sp;

import org.junit.Test;

import mycodelearn.undergrowth.crawler.mw.sp.HelloWorld;
import mycodelearn.undergrowth.crawler.mw.sp.IndexTool;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="undergrowth@126.com">undergrowth</a>
* @date 2016年7月13日
* @version 1.0.0
 */
public class IndexToolTest {

	private String indexPath="D:\\lucene\\sp\\indextool\\index";
	private String docPath="D:\\lucene\\sp\\indextool\\docs";
	
	
	@Test
	public void testIndex(){
		IndexTool indexTool=new IndexTool();
		indexTool.index(indexPath, docPath);
	}
	
	@Test
	public void testSearch(){
		IndexTool indexTool=new IndexTool();
		indexTool.search(indexPath, "content", "tencent", 100);
	}
	
}
