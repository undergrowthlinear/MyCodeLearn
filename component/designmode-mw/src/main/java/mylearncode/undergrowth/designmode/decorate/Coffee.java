package mylearncode.undergrowth.designmode.decorate;

public class Coffee extends Baverage {

	public Coffee(){
		description=Coffee.class.getSimpleName();
	}
	
	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return Price.CoffeePrice;
	}

}
