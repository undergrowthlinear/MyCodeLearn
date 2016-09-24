package mylearncode.undergrowth.spring.mw.generic.dependency;

public class User {

	private String name = getClass().getName();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
