package mylearncode.undergrowth.designmode.adapter.test;
import java.util.Enumeration;
import java.util.Iterator;

import org.junit.Test;

import mylearncode.undergrowth.designmode.adapter.EnumeIteratorAdapter;
import mylearncode.undergrowth.designmode.adapter.NumEnum;

public class EnumeIteratorAdapterTest {

	@Test
	public void test() {
		String[] data={"百度","谷歌","IBM","Oracle","Microsoft","HP","Lenovo","Tecent"};
		Enumeration enumeration=new NumEnum(data);
		Iterator iterator=new EnumeIteratorAdapter(enumeration);
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
