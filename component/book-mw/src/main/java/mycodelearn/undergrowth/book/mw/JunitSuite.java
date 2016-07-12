package mycodelearn.undergrowth.book.mw;

public class JunitSuite {

	private String name;

	public String sayHello() {
		return "sayHello " + getName();
	}

	public String sayJunitSuite() {
		return "sayJunitSuite " + getName();
	}

	public String sayJunitSuiteWhat() {
		return "sayJunitSuiteWhat " + getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
