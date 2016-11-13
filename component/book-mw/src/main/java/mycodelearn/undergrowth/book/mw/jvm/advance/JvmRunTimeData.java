package mycodelearn.undergrowth.book.mw.jvm.advance;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 深入理解Java虚拟机
 *              JVM高级特性与最佳实战
 *
 * Java堆OOM
 * Jvm运行期数据
 * 被所有线程共享的区域
 * Java Heap----分配Class实例和数组----(-Xms10M -Xmx10M
 * MethodArea----存放类信息、运行时常量----(-XX:MaxPermSize=10M
 * 每个线程单独的区域
 * VirtualMachineStack----方法栈帧----(-Xss10M
 * NativeMethodStack----本地方法栈帧----(-Xss10M
 * ProgramCounterRegistry----程序计数器----指向当前执行的语句地址
 * 不受JVM控制的直接内存区域
 * DirectMemory----直接在内存中分配,通过引用指向直接内存区域----(-XX:MaxDirectMemorySize=10M
 * @author zhangwu
 * @version 1.0.0
 * @date 2016-11-01-14:59
 */
public class JvmRunTimeData {

	@Test
	public void  heapOOMTest(){
		//-verbose:gc -Xms10M -Xmx10M -Xmn8M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -XX:SurvivorRatio=8
		List<HeapOOM> arrayList=new ArrayList<>();
		while(true){
			arrayList.add(new HeapOOM());
		}
	}


	private class HeapOOM {
	}
}
