package mylearncode.undergrowth.designmode.template;

/**
 * 
 * ���ԭ��
 *   ������ԭ�򣺱�����ң��һ������
 *   �����뷨���Ŀ�����ڣ���ֹ��������  �ø߲�������õײ����
 *   
 * ģ�巽��ģʽ�� ������һ���㷨�Ŀ�ܣ���������Ϊ���ṩһ�����������ʵ��
 *   ģ�巽���Ͳ��Է�������װ�㷨��ģ��ʹ�ü̳У�����ʹ�����
 *   ��������Ϊģ�巽��������
 * 
 * ���ӣ�
 *   �������ڳ������еķ�����������Ĭ�ϵĻ��߿յ�ʵ�֣����������Ҫ��Ҫ��д
 *   ����������㷨�е�ĳЩ�����Ƿ���Ҫ
 * 
 * ʾ���� ���ȺͲ�ĳ��ݹ��� 
 * ���ȣ���ˮ�󿪡��÷�ˮ���ݿ��ȡ��ѿ��ȵ������ӡ����Ǻ�ţ�� 
 * ��Ҷ����ˮ�󿪡��÷�ˮ���ݲ�Ҷ���Ѳ赹�����ӡ�������
 * 
 * @author Administrator
 * 
 */
public abstract class CaffeineBeverage {

	/**
	 * ��Ϳ��ȵĳ����㷨 ģ�巽������Ϊfinal ������������д
	 */
	public final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		//���Ϲ��� ���Ʋ���
		if(isAddCondiment()){
			addCondiments();
		}
		
	}

	/**
	 * ���ӷ���
	 * @return
	 */
	 boolean isAddCondiment() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * ��ͬ����
	 */
	void boilWater() {
		System.out.println("��ˮ��");
	}

	/**
	 * ���ݷ�ʽ��һ�� ��������ʵ��
	 */
	abstract void brew();

	void pourInCup() {
		// TODO Auto-generated method stub
		System.out.println("�����ϵ�������");
	}

	/**
	 * �ӵ��ϲ�һ�� ��������ʵ��
	 */
	abstract void addCondiments();

}
