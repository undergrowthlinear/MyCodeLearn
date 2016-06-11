package mylearncode.undergrowth.designmode.compound;

/**
 * Ѽ�ӹ��� ������ͬ�����Ѽ��
 * @author Administrator
 *
 */
public class DuckFactory extends AbstractDuckFactory {

	@Override
	public Quackable createMallardDuck() {
		// TODO Auto-generated method stub
		return new MallardDuck();
	}

	@Override
	public Quackable createRedheadDuck() {
		// TODO Auto-generated method stub
		return new RedheadDuck();
	}

	@Override
	public Quackable createBeiJingDuck() {
		// TODO Auto-generated method stub
		return new BeiJingDuck();
	}

	@Override
	public Quackable createFlockDuck() {
		// TODO Auto-generated method stub
		return new FlockDuck();
	}

}
