package mycodelearn.undergrowth.crawler.mw.test.sp;

import org.junit.Test;

import mycodelearn.undergrowth.crawler.mw.sp.HelloWorld;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="undergrowth@126.com">undergrowth</a>
* @date 2016年7月13日
* @version 1.0.0
 */
public class HelloWorldTest {

	private String indexPath="D:\\lucene\\sp\\helloworld\\index";
	private String docPath="D:\\lucene\\sp\\helloworld\\docs";
	
	
	@Test
	public void testIndex(){
		HelloWorld helloWorld=new HelloWorld();
		helloWorld.index(indexPath, docPath);
	}
	
	@Test
	public void testSearch(){
		HelloWorld helloWorld=new HelloWorld();
		helloWorld.search(indexPath, "content", "friend", 100);
	}
	
}
