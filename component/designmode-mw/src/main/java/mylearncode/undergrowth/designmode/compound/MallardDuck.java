package mylearncode.undergrowth.designmode.compound;

/**
 * ��ͷѼ
 * 
 * @author Administrator
 * 
 */
public class MallardDuck implements Quackable {

	// ����ӡ��Ƴ���֪ͨ�۲��ߵ�����ί�и���������д���
	QuackObservable quackObservable;

	public MallardDuck() {
		this.quackObservable = new ObservableAssist(this);
	}

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		System.out.println(MallardDuck.class.getName() + "\t" + "��ͷѼ,quack");
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
