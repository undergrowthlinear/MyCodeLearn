package mycodelearn.undergrowth.basiclearn.lang.test;

public enum Operation {
	ADD("add"),MULTI("multi"),DIVIDE("divide"),MINUS("minus");
	private String name;
	Operation(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
}
