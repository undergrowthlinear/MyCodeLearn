package mylearncode.undergrowth.spring.mw.springinaction.springcore;

import java.util.List;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用) spel----通过计算获取某个表达式的值
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年9月11日
 * @version 1.0.0
 */
public class SpelInject {

	// 字符
	private String grade;
	// 类
	private int age;
	// 对象
	private double price;
	// 集合
	private List<String> names;

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	@Override
	public String toString() {
		return "SpelInject [grade=" + grade + ", age=" + age + ", price=" + price + ", names=" + names + "]";
	}

}
