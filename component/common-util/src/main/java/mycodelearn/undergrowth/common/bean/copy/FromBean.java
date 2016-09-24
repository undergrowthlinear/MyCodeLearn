package mycodelearn.undergrowth.common.bean.copy;

public class FromBean {
	private String name;
	private int age;
	private String address;
	private String idno;
	private double money;
	private FromSecondBean fromSecondBean;

	
	public FromSecondBean getFromSecondBean() {
		return fromSecondBean;
	}

	public void setFromSecondBean(FromSecondBean fromSecondBean) {
		this.fromSecondBean = fromSecondBean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "FromBean [name=" + name + ", age=" + age + ", address=" + address + ", idno=" + idno + ", money="
				+ money + ", fromSecondBean=" + fromSecondBean + "]";
	}

	
	
}
