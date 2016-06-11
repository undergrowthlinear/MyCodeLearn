package mylearncode.undergrowth.designmode.compound;

/**
 * ����Ѽ
 * @author Administrator
 *
 */
public class BeiJingDuck implements Quackable {

	//����ӡ��Ƴ���֪ͨ�۲��ߵ�����ί�и���������д���
	QuackObservable quackObservable;
	
	public BeiJingDuck() {
		this.quackObservable = new ObservableAssist(this);
	}

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		System.out.println(BeiJingDuck.class.getName()+"\t"+"����Ѽ,quack");
		 notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		// TODO Auto-generated method stub
		quackObservable.registerObserver(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		quackObservable.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		quackObservable.notifyObservers();
	}

}
