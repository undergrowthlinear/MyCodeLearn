/**
 * 
 */
package mylearncode.undergrowth.designmode.compound;

/**
 * Ѽ��װ����  ��Ӽ�����Ϊ
 * @author Administrator
 *
 */
public class QuackCount implements Quackable {

	private static int count=0;
	Quackable quackable;
	/**
	 * 
	 */
	public QuackCount() {
		// TODO Auto-generated constructor stub
	}

	public QuackCount(Quackable quackable) {
		// TODO Auto-generated constructor stub
		this.quackable=quackable;
	}

	/* (non-Javadoc)
	 * @see com.undergrowth.compound.Quackable#quack()
	 */
	@Override
	public void quack() {
		// TODO Auto-generated method stub
		quackable.quack();
		System.out.println("��"+(++count)+"�ν�");
	}

	public static int getCount() {
		return count;
	}

	@Override
	public void registerObserver(Observer observer) {
		// TODO Auto-generated method stub
		quackable.registerObserver(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		quackable.removeObserver(observer);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		quackable.notifyObservers();
	}
    
	
}
