package mylearncode.undergrowth.designmode.state;

/**
 * �ǹ�����
 * 
 * @author Administrator
 * 
 */
public class GumballMachine {

	//״̬�л�����5��״̬֮��
	State noMoneyState;
	State hasMoneyState;
	State soldState;
	State soldOutState;
	State winnerState;
	// ��ǰ״̬
	State state;
	//�ǹ�ʣ����
	int count;

	public GumballMachine(int numCandy) {
		noMoneyState = new NoMoneyState(this);
		hasMoneyState = new HasMoneyState(this);
		winnerState = new WinnerState(this);
		soldState = new SoldState(this);
		soldOutState = new SoldOutState(this);
		this.count = numCandy;
		if (numCandy > 0) {
			state = noMoneyState;
		}
	}

	public void insertCoins(){
		state.insertCoins();
	}
	public void coinsHopper(){
		state.coinsHopper();
	}
	public void pressSold(){
		state.pressSold();
		state.soldCandy();
	}
	
	void releaseCandy(){
		if(count!=0){
			System.out.println("����һ���ǹ�");
			count=count-1;
		}
	}
	
	
	public State getNoMoneyState() {
		return noMoneyState;
	}

	public State getHasMoneyState() {
		return hasMoneyState;
	}

	public State getSoldState() {
		return soldState;
	}

	public State getSoldOutState() {
		return soldOutState;
	}

	public State getWinnerState() {
		return winnerState;
	}

	public State getState() {
		return state;
	}

	public int getCount() {
		return count;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "GumballMachine [state=" + state + ", count=" + count + "]";
	}

	
}
