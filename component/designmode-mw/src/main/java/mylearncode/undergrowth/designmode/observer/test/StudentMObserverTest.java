package mylearncode.undergrowth.designmode.observer.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mylearncode.undergrowth.designmode.observer.GradeSubject;
import mylearncode.undergrowth.designmode.observer.StudentMObserver;
import mylearncode.undergrowth.designmode.observer.StudentOTherMObserver;
import mylearncode.undergrowth.designmode.observer.Subject;

public class StudentMObserverTest {

	@Test
	public void test() {
		GradeSubject subject=new GradeSubject();
		StudentMObserver studentMObserver1=new StudentMObserver(subject);
		StudentOTherMObserver stu=new StudentOTherMObserver(subject);
		//�ı�ɼ�
		subject.setChanged(true);
		//������  ֪ͨ�۲���
		subject.notifyObservers();
		//�ı�ɼ�
		subject.setAverageGrade(70);
		subject.setGradeMax(99);
		subject.notifyObservers(new String[]{"���ݸ�֪ͨ��"});
	}

}
