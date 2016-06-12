package mycodelearn.undergrowth.interview.lc;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
 -Xms10M -Xmx10M -Xmn2M -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError 
 -XX:HeapDumpPath=D:\OutOfMemoryTest.hprof 
 -XX:+PrintGCDetails -Xloggc:D:\OutOfMemoryTest.log
 
 另一种描述:
 jmap -dump:file=D:/javaDump.hprof,format=b 3614
 jhat -J-Xmx1024m D:/OutOfMemoryTest.hprof  
 http://localhost:7000/
 
 更多:
 JProfiler
 
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月12日
* @version 1.0.0
 */
public class OutOfMemoryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size=1024*1024;
		byte[] byt1=new byte[10*size];
		while(true);
	}

}
