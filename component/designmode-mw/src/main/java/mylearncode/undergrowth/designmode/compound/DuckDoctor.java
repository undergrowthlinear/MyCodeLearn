/**
 * 
 */
package mylearncode.undergrowth.designmode.compound;

/**
 * Ѽ��ҽ�� �۲�Ѽ�ӵĽ���Ϊ
 * @author Administrator
 *
 */
public class DuckDoctor implements Observer {

	/**
	 * 
	 */
	public DuckDoctor() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.undergrowth.compound.Observer#update(com.undergrowth.compound.QuackObservable)
	 */
	@Override
	public void update(QuackObservable quackObservable) {
		// TODO Auto-generated method stub
		System.out.println(DuckDoctor.class.getName()+"\t�۲쵽��Ѽ�ӵĶ���Ϊ��"+quackObservable);
	}

}
