package mylearncode.undergrowth.designmode.factory;

/**
 * ŦԼ����
 * @author Administrator
 *
 */
public class NYCheesePizza extends Pizza {
	
	IngredientFactory ingredientFactory=new NYIngredientFactory();
	public NYCheesePizza(){
		setName(NYCheesePizza.class.getSimpleName());
	}
	

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		System.out.println("��ʼ׼��������");
		System.out.println("���:"+ingredientFactory.createDough());
		System.out.println("����:"+ingredientFactory.createCheese());
		System.out.println("����:"+ingredientFactory.createClam());
	}
}
