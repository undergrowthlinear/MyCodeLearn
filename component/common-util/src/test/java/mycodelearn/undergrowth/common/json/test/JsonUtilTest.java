package mycodelearn.undergrowth.common.json.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

import mycodelearn.undergrowth.common.json.JsonFastUtil;
import mycodelearn.undergrowth.common.json.JsonUtil;

/**
 * json工具类
 * 
 * @author zhangwu
 * @version 1.0
 * @created 08-8月-2016 10:08:58
 */
public class JsonUtilTest {

	String hex = "57be6414a5302cec2b068f74";
	Person person = null;
	int num = 1000000;
	ExecutorService executor;

	@Before
	public void before() {
		// createPerson();
		// person.setHexName("12");
		executor = Executors.newCachedThreadPool();
	}

	private Person createPerson() {
		person = new Person();
		person.setAge(20);
		person.setName("under");
		person.setHexName(hex);
		List<Department> departments = new ArrayList<>();
		departments.add(new Department(hex, "信息员", 10));
		departments.add(new Department(hex, "信息员", 2));
		departments.add(new Department(hex, "信息员", 3));
		departments.add(new Department(hex, "信息员", 4));
		departments.add(new Department(hex, "信息员", 5));
		person.setDepartments(departments);
		return person;
	}

	@Test
	public void testHexToJson() throws Exception {
		final JsonUtilTest jsonUtilTest = new JsonUtilTest();
		executor.submit(new JacksonRunnable(jsonUtilTest));
		executor.submit(new FastJsonRunnable(jsonUtilTest));
		System.in.read();
	}

	class JacksonRunnable implements Runnable {

		private JsonUtilTest jsonUtilTest;

		public JacksonRunnable(JsonUtilTest jsonUtilTest) {
			// TODO Auto-generated constructor stub
			this.jsonUtilTest = jsonUtilTest;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				long startTime = System.currentTimeMillis();
				int sum = 0;
				for (int i = 0; i < jsonUtilTest.num; i++) {
					Person person = jsonUtilTest.createPerson();
					String jsonPerson = JsonUtil.serialize(person);
					sum += jsonPerson.length();
					JsonUtil.deserialize(jsonPerson, Person.class);
				}
				System.out.println("运行JacksonRunnable----" + jsonUtilTest.num + ",耗时"
						+ (System.currentTimeMillis() - startTime)+"ms,平均耗时"+((System.currentTimeMillis() - startTime)*1000*1000/jsonUtilTest.num) + ",总大小:" + sum);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class FastJsonRunnable implements Runnable {

		private JsonUtilTest jsonUtilTest;

		public FastJsonRunnable(JsonUtilTest jsonUtilTest) {
			// TODO Auto-generated constructor stub
			this.jsonUtilTest = jsonUtilTest;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				long startTime = System.currentTimeMillis();
				int sum = 0;
				for (int i = 0; i < jsonUtilTest.num; i++) {
					Person person = jsonUtilTest.createPerson();
					String jsonPerson = JsonFastUtil.serialize(person);
					sum += jsonPerson.length();
					JsonFastUtil.deserialize(jsonPerson, Person.class);
				}
				System.out.println("运行FastJsonRunnable----" + jsonUtilTest.num + ",耗时"
						+ (System.currentTimeMillis() - startTime)+"ms,平均耗时"+((System.currentTimeMillis() - startTime)*1000*1000/jsonUtilTest.num)  + ",总大小:" + sum);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}