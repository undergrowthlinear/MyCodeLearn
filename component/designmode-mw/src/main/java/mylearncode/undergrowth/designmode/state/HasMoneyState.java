package mylearncode.undergrowth.designmode.state;

import java.util.Random;

public class HasMoneyState implements State {

	GumballMachine gumballMachine;
	// ��¼�ǹ��������ģʽ
	Random randomWinner = new Random(System.currentTimeMillis());

	public HasMoneyState(GumballMachine gumballMachine) {
		// TODO Auto-generated constructor stub
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertCoins() {
		// TODO Auto-generated method stub
		System.out.println("�Ѿ�Ͷ�����ˣ���Ҫ��Ͷ����");
	}

	@Override
	public void coinsHopper() {
		// TODO Auto-generated method stub
		System.out.println("�˱ҳɹ�");
		gumballMachine.setState(gumballMachine.getNoMoneyState());
	}

	@Override
	public void pressSold() {
		// TODO Auto-generated method stub
		System.out.println("�㰴���۳��ǹ���ť�������۳��ǹ�");
		int winner = randomWinner.nextInt(10);
		//ʮ��֮һ���� ����Ӯ��ģʽ
		if (winner == 0 && gumballMachine.getCount() > 1) {
			gumballMachine.setState(gumballMachine.getWinnerState());
		} else {
			gumballMachine.setState(gumballMachine.getSoldState());
		}

	}

	@Override
	public void soldCandy() {
		// TODO Auto-generated method stub
		System.out.println("��û�в����������۳��ǹ�");
	}

}
