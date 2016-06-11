package mylearncode.undergrowth.designmode.state;

public class SoldState implements State {

	GumballMachine gumballMachine;
	public SoldState(GumballMachine gumballMachine) {
		// TODO Auto-generated constructor stub
		this.gumballMachine=gumballMachine;
	}

	@Override
	public void insertCoins() {
		// TODO Auto-generated method stub
		System.out.println("�����۳��ǹ�����ҪͶ��");
	}

	@Override
	public void coinsHopper() {
		// TODO Auto-generated method stub
		System.out.println("�����۳��ǹ�����Ҫ�˱�");
	}

	@Override
	public void pressSold() {
		// TODO Auto-generated method stub
		System.out.println("�����۳��ǹ�����Ҫ�ٰ����۳���ť��");
	}

	@Override
	public void soldCandy() {
		// TODO Auto-generated method stub
		gumballMachine.releaseCandy();
		if(gumballMachine.getCount()>0){
			gumballMachine.setState(gumballMachine.getNoMoneyState());
		}else {
			System.out.println("û���ǹ��ˣ������ǹ�����״̬");
			gumballMachine.setState(gumballMachine.getSoldOutState());
		}
	}

}
