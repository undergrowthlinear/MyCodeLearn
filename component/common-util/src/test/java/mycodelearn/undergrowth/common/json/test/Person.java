package mycodelearn.undergrowth.common.json.test;

import java.util.List;

public class Person {
	public Person(String name, String hexName, int age) {
		super();
		this.name = name;
		HexName = hexName;
		this.age = age;
	}

	public Person() {
		super();
	}

	private String name;
	private String HexName;
	private int age;
	List<Department> departments;

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHexName() {
		return HexName;
	}

	public void setHexName(String hexName) {
		HexName = hexName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", HexName=" + HexName + ", age=" + age + "]";
	}
}
