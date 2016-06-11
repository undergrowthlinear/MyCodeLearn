package mylearncode.undergrowth.designmode.factory;

public class NYPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		// TODO Auto-generated method stub
		Pizza pizza=null;
		if("Cheese".equals(type))
			pizza=new NYCheesePizza();
		else if("Clam".equals(type)){
			pizza=new NYClamPizza();
		}
		return pizza;
	}

}
