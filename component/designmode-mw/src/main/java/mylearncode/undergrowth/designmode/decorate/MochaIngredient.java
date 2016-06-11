package mylearncode.undergrowth.designmode.decorate;

public class MochaIngredient extends IngredientBaverage {
    
	Baverage baverage;
	
	public MochaIngredient(Baverage baverage){
		this.baverage=baverage;
	}
	
	
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return MochaIngredient.class.getSimpleName()+"\t"+baverage.getDescription();
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return Price.MochaPrice+baverage.cost();
	}

}
