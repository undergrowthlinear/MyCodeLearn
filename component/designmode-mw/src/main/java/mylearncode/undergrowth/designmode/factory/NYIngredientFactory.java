package mylearncode.undergrowth.designmode.factory;

public class NYIngredientFactory implements IngredientFactory {

	@Override
	public Dough createDough() {
		// TODO Auto-generated method stub
		return new ThinDough();
	}

	@Override
	public Clam createClam() {
		// TODO Auto-generated method stub
		return new FreshClam();
	}

	@Override
	public Cheese createCheese() {
		// TODO Auto-generated method stub
		return new SweetCheese();
	}

}
