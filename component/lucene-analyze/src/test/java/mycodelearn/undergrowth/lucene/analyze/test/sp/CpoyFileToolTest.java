package mycodelearn.undergrowth.lucene.analyze.test.sp;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月13日
 * @version 1.0.0
 */
public class CpoyFileToolTest {

	private String docPath = "D:\\lucene\\sp\\indextool\\docs";

	@Test
	public void testCoyyFile() {
		File file=new File(docPath);
		System.out.println(file.listFiles().length);
		for(File file2:file.listFiles()){
			try {
				String pathname=file2.getAbsolutePath().substring(0,file2.getAbsolutePath().indexOf("."))+System.currentTimeMillis()+".dd";
				System.out.println(pathname);
				File destFile=new File(pathname);
				FileUtils.copyFile(file2, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

}
