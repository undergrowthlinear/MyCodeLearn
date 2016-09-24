package mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire;

public class ConfigurableStudent {

	private String name = getClass().getName();

	public ConfigurableStudent(QualifierConfigurableStudent student) {
		// TODO Auto-generated constructor stub
		this.name = ConfigurableStudent.class.getName() + "_" + student.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
