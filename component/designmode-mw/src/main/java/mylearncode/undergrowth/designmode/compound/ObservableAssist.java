/**
 * 
 */
package mylearncode.undergrowth.designmode.compound;

import java.util.ArrayList;
import java.util.List;


/**
 * �ɹ۲��߸����� ���ڹ���۲��� �Ƴ��۲��� ֪ͨ�۲���
 * @author Administrator
 *
 */
public class ObservableAssist implements QuackObservable {

	List<Observer> listObservers=new ArrayList<>();
	QuackObservable quackObservable;
	/**
	 * 
	 */
	public ObservableAssist(QuackObservable quackObservable) {
		// TODO Auto-generated constructor stub
		this.quackObservable=quackObservable;
	}

	/* (non-Javadoc)
	 * @see com.undergrowth.compound.QuackObservable#registerObserver(java.util.Observer)
	 */
	@Override
	public void registerObserver(Observer observer) {
		// TODO Auto-generated method stub
		listObservers.add(observer);
	}

	/* (non-Javadoc)
	 * @see com.undergrowth.compound.QuackObservable#removeObserver(java.util.Observer)
	 */
	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		if (listObservers.contains(observer)) {
			listObservers.remove(observer);
		}
	}

	/* (non-Javadoc)
	 * @see com.undergrowth.compound.QuackObservable#notifyObservers()
	 */
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer observer : listObservers) {
			//֪ͨ�۲���
			observer.update(quackObservable);
		}
	}

}
