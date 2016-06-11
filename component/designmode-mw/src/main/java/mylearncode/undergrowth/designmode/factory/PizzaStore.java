package mylearncode.undergrowth.designmode.factory;

/**
 * ���ԭ��
 * 		��������ԭ�������ڳ��󣬶��Ǿ���ʵ��
 * 
 * ���еĹ����������ڷ�װ����Ĵ���
 * ���ڽ��ͻ����������Ӧ�ý���
 * 
 * �򵥹�������һ�����д����������ϸ��
 * 
 * ��������ģʽ��
 *   �����������ʼ��ʲô���Ķ��󣬼������ʵ�����Ƴٵ������н���
 * ��������ʹ�ü̳У�������Ĵ���ί�и�������д���  
 *   
 * ���󹤳�ģʽ��
 *   ������һ���ӿڣ��ṩ�˴�����������ļ��壬���ǲ�ָ�����崴��ʲô����  
 * ���󹤳�ʹ����ϸ����������ļ��� 
 * 
 * 
 * ʵ����
 * ������ļ���ģʽ
 *    ����������кܶ���˵�
 *     ��ͬ�ļ��˵������������Կ�ζ��ͬ ��������������������ԭ�϶�������һ�µ�
 * @author Administrator
 *
 */

public abstract class PizzaStore {
	
	/**
	 * ������
	 */
	public void orderPizza(String type){
		
		//�򵥹�����ʽ�����ǻ������� ��һ���ض���ʵ��  ������Ҫ�����ڳ���
		//Pizza pizza=PizzaSimpleFactory.createPizza(type);
		
		Pizza pizza=createPizza(type);
		//��֤��������������һ��
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
	}
	/**
	 * ʹ�ù�������ģʽ  ������Ĵ����Ƴٵ�������
	 * @return
	 */
	public abstract Pizza createPizza(String type);
	
}
