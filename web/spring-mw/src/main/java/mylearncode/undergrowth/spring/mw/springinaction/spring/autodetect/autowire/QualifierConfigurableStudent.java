package mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire;

public class QualifierConfigurableStudent {
	private String name=getClass().getName();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
