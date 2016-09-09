package mycodelearn.undergrowth.common.json.test;

public class Department {

	private String name;
	private String type;
	private int no;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Department(String name, String type, int no) {
		super();
		this.name = name;
		this.type = type;
		this.no = no;
	}

	public Department() {
		super();
	}

}
