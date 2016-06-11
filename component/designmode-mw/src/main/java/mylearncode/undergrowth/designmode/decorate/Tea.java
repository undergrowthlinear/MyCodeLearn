package mylearncode.undergrowth.designmode.decorate;

public class Tea extends Baverage {

	public Tea(){
		description=Tea.class.getSimpleName();
	}
	
	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return Price.TeaPrice;
	}

}
