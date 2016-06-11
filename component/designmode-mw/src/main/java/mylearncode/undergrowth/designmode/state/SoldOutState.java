package mylearncode.undergrowth.designmode.state;

public class SoldOutState implements State {

	GumballMachine gumballMachine;
	public SoldOutState(GumballMachine gumballMachine) {
		// TODO Auto-generated constructor stub
		this.gumballMachine=gumballMachine;
	}

	@Override
	public void insertCoins() {
		// TODO Auto-generated method stub
		System.out.println("û���ǹ��ˣ���ҪͶ��");
	}

	@Override
	public void coinsHopper() {
		// TODO Auto-generated method stub
		System.out.println("û���ǹ��ˣ���Ҫ�˱�");
	}

	@Override
	public void pressSold() {
		// TODO Auto-generated method stub
		System.out.println("û���ǹ��ˣ���Ҫ�����۳���ť");
	}

	@Override
	public void soldCandy() {
		// TODO Auto-generated method stub
		System.out.println("û���ǹ���");
	}

}
