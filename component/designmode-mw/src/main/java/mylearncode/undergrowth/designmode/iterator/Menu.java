package mylearncode.undergrowth.designmode.iterator;

import java.util.Iterator;

/**
 * ���ԭ��
 *   ��һԭ��һ����Ӧ��ֻ��һ������仯��ԭ��
 *   �ھ�--��������һ�������ģ����ܵĴﵽ��һĿ�Ļ�����
 *   ���ھ�--��ʾһ�������ģ��ֻ֧��һ��/һ����ع���
 * 
 * ������ģʽ��
 *   �ṩһ��˳����ʾۺ϶���Ԫ�صķ��������ֲ���¶���ڲ�Ԫ��
 * �������������ۺ϶���������װ��һ�������С�
 * 
 * ʾ����
 *   �������˵����������͡��й������
 * ͨ���˵��ӿ� �ṩ���ͻ�ͳһ�ķ��ʲ˵���ķ�ʽ ������֪���˵���Ĺ���ʽ
 * 
 * ���ǵ��������ڲ˵���Ƕ�ײ˵�ʱ ����˵�ж��˵�Ƕ��ʱ ����Ҫʹ�����ģʽ
 * @author Administrator
 *
 */
public interface Menu {
	/**
	 * ���ز˵���ĵ�����
	 * @return
	 */
   public Iterator createIterator();
   
}