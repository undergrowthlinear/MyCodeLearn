package mylearncode.undergrowth.designmode.decorate;

/**
 * ���ԭ�� ����-�ر�ԭ�� ����չ���ţ����޸Ĺر�
 * 
 * װ��ģʽ�� ��̬��Ϊ������Ӹ��ӹ���,�ṩ��һ����չ���ƣ��ȼ̳и�������չ��
 * 
 * �漰���ĸ�� 
 * װ����(Ϊ����������ӹ��ܵĶ���)�뱻װ����(����ӹ��ܵĶ���)
         ��Ϻ�ί�п�������ʱ��̬�ļ����µ���Ϊ
 * װ������ӵ�б�װ���ߵĳ������࣬����ָ��װ���ߣ�����װ���߽��в���ʱ����װ���߼�ί��װ����ִ����ز���
 * װ���ߺͱ�װ���߱���ӵ����ͬ�ĳ������࣬����װ�����滻��װ����
 * 
 * 
 * ʵ���� ���ڼ��㲻ͬ���������ϼ��������ܷ��� ���ϣ� ����(10Ԫ1��)����Ҷ(12Ԫһ��)���̲�(8Ԫ1��) ���ϣ�
 * Ħ��(4Ԫ1��)������(3Ԫ1��)������(2Ԫ1��)
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public abstract class Baverage {
	/**
	 * ���ڶ����ϵ�����
	 */
	public String description;

	/**
	 * ���ڼ��㲻ͬ���ϵļ�Ǯ
	 * 
	 * @return
	 */
	public abstract double cost();

	public String getDescription() {
		return description;
	}

}
