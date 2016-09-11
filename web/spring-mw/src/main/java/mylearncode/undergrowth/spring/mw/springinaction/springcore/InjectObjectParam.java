package mylearncode.undergrowth.spring.mw.springinaction.springcore;

public class InjectObjectParam {

	private String address;
	private double price;

	public InjectObjectParam(String address, double price) {
		super();
		this.address = address;
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "InjectObjectParam [address=" + address + ", price=" + price + "]";
	}

}
