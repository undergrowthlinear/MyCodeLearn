package mylearncode.undergrowth.designmode.strategy;

/**
 * ���ԭ��
 * 1����Խӿڱ�̣�����ʵ��
 * 2��������ϣ����ü̳�
 * 3����װ�仯
 * 
 * ����ģʽ�����㷨�ط�װ������ʹʹ���㷨�Ŀ�����㷨֮���໥����
 * 
 * ʾ����Ѽ����Ϸ
 * 
 * ÿ��Ѽ�Ӷ�����Ӿ����Ҫ��ʾ�Լ�����ۣ�����ÿ��Ѽ�ӻ����������ض�����Ϊ��
 * ���仯���ض���Ϊ���з�װ������ɽӿڣ����ṩ�仯��Ϊ��ʵ���࣬
 * ��ÿ��Ѽ�����ض�����Ϊʱ��ֻ��ʵ���ض�����Ϊʵ���༴�ɣ����ɴ��븴��
 *  ͬʱ��ÿ��Ѽ�ӿɳ��в�ͬ��Ϊ�Ľӿڣ�������ʱ���ɶ�̬�ı�Ѽ�ӵ���Ϊ��������Խӿڱ�̵ĺô�
 *  ��ÿ��Ѽ�ӳ��в�ͬ��Ϊ�Ľӿ���Ϊ��Ա����ʱ��Ѽ�Ӻ���Ϊ֮�����϶ȼ����� ��
 *  ���ı���Ϊ�Ĳ���ʱ��Ѽ�ӵ�ԭ�д��붼Ҫ���ñ仯�����㷨�ر仯��ʹ���㷨�Ŀ��岻�䣬��ǿ������Ŀ�ά����
 *  
 *  
 *  ���� Ѽ��
 * @author Administrator
 *
 */
public abstract class Duck {

	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;
	
	/**
	 * ��Ӿ��Ϊ
	 */
	public void swim(){
		System.out.println("����һֻѼ�ӣ����һ���Ӿ");
	}
	
	/**
	 * ��ʾѼ�ӵ���ۣ���Ϊ��ͬ��Ѽ�ӵ�����ǲ�ͬ�ģ����������Լ�ʵ��
	 */
	public abstract void display();

	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}

	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}

	public FlyBehavior getFlyBehavior() {
		return flyBehavior;
	}

	public QuackBehavior getQuackBehavior() {
		return quackBehavior;
	}
	
}
