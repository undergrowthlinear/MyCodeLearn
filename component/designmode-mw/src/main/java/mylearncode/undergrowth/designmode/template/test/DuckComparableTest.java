package mylearncode.undergrowth.designmode.template.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import mylearncode.undergrowth.designmode.template.DuckComparable;

public class DuckComparableTest {

	@Test
	public void test() {
		DuckComparable[] ducks = { new DuckComparable(100, "��ͷѼ"),
				new DuckComparable(85, "��ͷѼ"), new DuckComparable(110, "��ͷѼ") 
		, new DuckComparable(57, "��ͷѼ")};
		System.out.println("===================δ�����Ѽ��===================");
		display(ducks);
		System.out.println("===================�������Ѽ��===================");
		Arrays.sort(ducks);
		display(ducks);
	}

	private void display(DuckComparable[] ducks) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ducks.length; i++) {
			System.out.println(ducks[i]);
		}
	}
	
	

}
