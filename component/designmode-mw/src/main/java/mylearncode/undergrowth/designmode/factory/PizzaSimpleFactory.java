package mylearncode.undergrowth.designmode.factory;


/**
 * �򵥹��� ��������
 * @author Administrator
 *
 */
public class PizzaSimpleFactory {
	
	public static Pizza createPizza(String type){
		Pizza pizza=null;
		switch (type) {
		case "NYCheese":
			pizza=new NYCheesePizza();
			break;
		default:
			pizza=new CaliforniaCheesePizza();
			break;
		}
		return pizza;
	}
	
}
