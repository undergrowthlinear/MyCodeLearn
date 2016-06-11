package mylearncode.undergrowth.designmode.state;

/**
 * 
 * ״̬ģʽ��
 *   ����������ڲ�״̬�ı�ʱ�ı�������Ϊ���������������޸���������
 *   
 * ��װ����״̬����Ϊ��������Ϊί�е���ǰ״̬ 
 * ͨ����״̬��װ��һ���࣬���Ժ�Ҫ��������ֲ�����
 * 
 * ����ģʽ��״̬ģʽ����ͬ����ͼ�����Ǳ˴˵���ͼ��ͬ
 * 
 * State�ӿڶ�����һ�����о���״̬�Ĺ�ͬ�ӿ�
 * Context��һ���࣬������ӵ��һЩ�ڲ�״̬   �ǹ�������Context
 *   
 * ʾ��
 *   �ǹ���ģ��
 *    5��״̬��û��5��Ǯ״̬����5��Ǯ״̬��Ӯ��ģʽ״̬�������ǹ�״̬  ���ǹ�����״̬
 *    4��������Ͷ5��Ǯ����5��Ǯ����ť�۳���ť���۳��ǹ�
 *    
 * 
 * @author Administrator
 *
 */
public interface State {
	
	public void insertCoins();
	public void coinsHopper();
	public void pressSold();
	public void soldCandy();
	
}
