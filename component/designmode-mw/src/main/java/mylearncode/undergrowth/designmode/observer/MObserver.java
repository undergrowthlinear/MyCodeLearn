package mylearncode.undergrowth.designmode.observer;

/**
 * �۲��ߵ����ü�ʹ�����Լ���״̬
 * @author Administrator
 *
 */
public interface MObserver {
	public void update();
	public void update(Object[] data);
	public void update(Subject subject);
}
