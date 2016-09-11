package mylearncode.undergrowth.spring.mw.springinaction.springcore;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class InjectObject {

	private String name;
	private int age;
	private boolean right;
	private InjectObjectParam param;
	private List<String> list;
	private Set<String> set;
	private Map<String, InjectObjectParam> map;
	private Properties properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public InjectObjectParam getParam() {
		return param;
	}

	public void setParam(InjectObjectParam param) {
		this.param = param;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, InjectObjectParam> getMap() {
		return map;
	}

	public void setMap(Map<String, InjectObjectParam> map) {
		this.map = map;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	@Override
	public String toString() {
		return "InjectObject [name=" + name + ", age=" + age + ", right=" + right + ", param=" + param + ", list="
				+ list + ", set=" + set + ", map=" + map + ", properties=" + properties + "]";
	}

}
