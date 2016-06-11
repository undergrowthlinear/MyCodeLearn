package mylearncode.undergrowth.designmode.state;

/**
 * û��5��Ǯ��״̬
 * @author Administrator
 *
 */
public class NoMoneyState implements State {

	GumballMachine gumballMachine;
	
	public NoMoneyState(GumballMachine gumballMachine) {
		super();
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertCoins() {
		// TODO Auto-generated method stub
		System.out.println("��Ͷ��5��Ǯ");
		gumballMachine.setState(gumballMachine.getHasMoneyState());
	}

	@Override
	public void coinsHopper() {
		// TODO Auto-generated method stub
		System.out.println("��û��ͶǮ��������Ǯ");
	}

	@Override
	public void pressSold() {
		// TODO Auto-generated method stub
		System.out.println("��û��ͶǮ���������ǹ�");
	}

	@Override
	public void soldCandy() {
		// TODO Auto-generated method stub
		System.out.println("��û��ͶǮ���������ǹ�");
	}

}
