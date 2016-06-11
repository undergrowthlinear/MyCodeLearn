package mylearncode.undergrowth.designmode.factory;

/**
 * ���� ���ڹ�������
 * @author Administrator
 *
 */
public  abstract class Pizza {
	private String name;
	 Dough dough;
	 Cheese cheese;
	 Clam clam;
	/**
	 * ����������ԭ�ϴӳ��󹤳��л�ȡ
	 */
	public abstract void prepare();
	public void bake(){
		System.out.println(getName()+"\t"+"���������к濾");
	}
	public void cut(){
		System.out.println(getName()+"\t"+"������������Ƭ");
	}
	public void box(){
		System.out.println(getName()+"\t"+"����������װ��");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
