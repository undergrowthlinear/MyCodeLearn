package mylearncode.undergrowth.designmode.factory;

/**
 * ŦԼ����
 * @author Administrator
 *
 */
public class NYClamPizza extends Pizza {
	public NYClamPizza(){
		setName(NYClamPizza.class.getSimpleName());
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		System.out.println(NYClamPizza.class.getSimpleName()+"\t׼��������...");
	}
}
