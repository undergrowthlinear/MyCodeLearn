package mylearncode.undergrowth.designmode.observer;



/**
 * ���ԭ��
 * 	 Ϊ��������֮�������϶ȶ�Ŭ��
 * 
 * �۲���ģʽ��
 *   ����������֮�����һ�Զ��������һ��һ��״̬һ�������ı䣬���һ�������յ�֪ͨ������״̬�ĸ���
 *   ��Ӧ�ļ��ǣ�
 *     ����(1)��۲���(n)  ����˵�ǿɹ۲���(1)��۲���(n)
 *   ����������� ע�ᡢ�Ƴ���֪ͨ�۲���
 *   �۲��ߵ������ǽ��յ��ض������֪ͨ������״̬�������ݵĸ���  
 *   
 *   ����������������۲���֮��Ĵ��ݷ����ֿɷ�Ϊ
 *   ��(push)--�������״̬�����仯ʱ�����������͸��۲���
 *   ��(pull)--�������״̬�����仯ʱ����֪���۲��ߣ��ɹ۲����Լ�������ȡʲô����
 * 
 *   ʾ����
 *    ʹ�ÿγ̳ɼ���ѧ��������չʾ
 *    �γ̳ɼ���Ϊ����  ѧ����Ϊ�۲���
 *    ���γ̳ɼ�����ʦ�ύ��  ��֪ͨÿһ��ѧ�� ������ƽ�����Ƕ��� ��߷��Ƕ��� ��Ȼ ǰ����ѧ������ѡ�����ſ�
 * 
 *   java�����Ҳ�й۲���ģʽ  �ֱ���Observable��Observer
 * 
 * @author Administrator
 *
 */
public interface Subject {
	
	
	
	/**
	 * ע��۲���
	 * @param observer
	 */
	public void registerObserver(MObserver observer);
	/**
	 * �Ƴ��۲���
	 * @param observer
	 */
	public void removeObserver(MObserver observer);
	
	/**
	 * ֪ͨ�۲���  ���ķ�ʽ
	 */
	public void notifyObservers();
	/**
	 * ֪ͨ�۲��� �Ƶķ�ʽ
	 * @param data
	 */
	public void notifyObservers(Object[] data);
}
