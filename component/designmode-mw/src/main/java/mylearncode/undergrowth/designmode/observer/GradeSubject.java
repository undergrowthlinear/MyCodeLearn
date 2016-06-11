package mylearncode.undergrowth.designmode.observer;

import java.util.ArrayList;
import java.util.List;

public class GradeSubject implements Subject {

	/**
	 * ��Ź۲���
	 */
	private List<MObserver> managerMObserver =new ArrayList<>();
	/**
	 * ��¼״̬�Ƿ�ı�
	 */
	private boolean changed;
	private int gradeMax=100;
	private double averageGrade=98.5;
	
	@Override
	public void registerObserver(MObserver observer) {
		// TODO Auto-generated method stub
		if(!managerMObserver.contains(observer)){
			managerMObserver.add(observer);
		}
	}

	@Override
	public void removeObserver(MObserver observer) {
		// TODO Auto-generated method stub
		if(managerMObserver.contains(observer)){
			managerMObserver.remove(observer);
		}
	}

	/**
	 * ������
	 */
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		if(isChanged()){
			for (MObserver observer : managerMObserver) {
				observer.update(this);
			}
		}
	}

	/**
	 * ������
	 */
	@Override
	public void notifyObservers(Object[] data) {
		// TODO Auto-generated method stub
		if (isChanged()) {
			for (MObserver observer : managerMObserver) {
				observer.update(data);
			}
		}
	}

	public List<MObserver> getManagerMObserver() {
		return managerMObserver;
	}

	public void setManagerMObserver(List<MObserver> managerMObserver) {
		this.managerMObserver = managerMObserver;
	}

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

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}



}
