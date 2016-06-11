package mylearncode.undergrowth.designmode.decorate;

public class HoneyIngredient extends IngredientBaverage {

	Baverage baverage;
	
	public HoneyIngredient(Baverage baverage)
	{
		this.baverage=baverage;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return HoneyIngredient.class.getSimpleName()+"\t"+baverage.getDescription();
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return Price.HoneyPrice+baverage.cost();
	}

}
