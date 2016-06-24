package mycodelearn.undergrowth.learn.spring.inaction.rs;

public class RSServiceHelloImpl implements RSServiceHello {

	@Override
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "hello remote service "+name;
	}

}
