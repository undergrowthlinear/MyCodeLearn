package mycodelearn.undergrowth.dubbo.test;

import java.util.concurrent.atomic.AtomicInteger;

public class DemoServiceImpl implements DemoService {

	private AtomicInteger count = new AtomicInteger(0);

	@Override
	public Person sayHello(String name) {
		// TODO Auto-generated method stub
		Person person = new Person();
		person.setAge(count.incrementAndGet());
		person.setName(name);
		return person;
	}

}
