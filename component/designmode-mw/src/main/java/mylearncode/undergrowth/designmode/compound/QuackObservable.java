/**
 * 
 */
package mylearncode.undergrowth.designmode.compound;



/**
 * �ɹ۲��߻��߳�֮Ϊ����
 *   ע�ᡢ�Ƴ���֪ͨ�۲���
 * 
 * @author Administrator
 *
 */
public interface QuackObservable {
	public void registerObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers();
}
