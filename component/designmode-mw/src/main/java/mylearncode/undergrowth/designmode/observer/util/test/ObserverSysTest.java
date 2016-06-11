package mylearncode.undergrowth.designmode.observer.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mylearncode.undergrowth.designmode.observer.util.ObserverSys;
import mylearncode.undergrowth.designmode.observer.util.SubjectSys;

public class ObserverSysTest {

	@Test
	public void test() {
		SubjectSys subject=new SubjectSys();
		ObserverSys objectSys=new ObserverSys(subject);
		//�ı�״̬
		subject.setChangedStatus();
		subject.notifyObservers();
		
	}

}
