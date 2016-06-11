package mylearncode.undergrowth.designmode.observer.util;

/**
 * java �������Դ��Ĺ۲���ģʽ  �ɹ۲���(Observable)�͹۲���(Observer)
 * Observable ʵ���� �۲��ߵ�ע�ᡢ�Ƴ���֪ͨ ���������࣬�����ǽӿڣ�����״̬�ı仯ֻ��ͨ���̳������ܸı�
 * Observer �ṩ�˸��²���
 */
import java.util.Observable;

public class SubjectSys extends Observable {
	private int gradeMax=100;
	private double averageGrade=98.5;
	public int getGradeMax() {
		return gradeMax;
	}
	public void setGradeMax(int gradeMax) {
		this.gradeMax = gradeMax;
	}
	public double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	public void setChangedStatus()
	{
		setChanged();
	}
}
