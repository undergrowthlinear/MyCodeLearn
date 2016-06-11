package mylearncode.undergrowth.designmode.composition;

import java.util.Iterator;

/**
 * 
 * 
 * 组合模式: 可以使组合对象形成树形结构，以表现出整体/部分的结构，并提供一致的方法访问整体和局部
 *   组合结构内任意对象称为组件，组件可以是组合，也可以是叶节点
 * 客户可以将对象的集合以及个别的对象一视同仁
 * 
 * 运用了递归迭代的思想
 * 
 * 外部迭代器必须维护它在遍历中的位置，以便外部客户可以通过调用hasNext和next方法来驱动遍历
 * 
 * 组合和叶节点都属于组件，只是两者的角色定位不同而已
 * 组合--拥有一群子元素 
 * 叶节点--无子元素
 * 
 * @author Administrator
 * 
 */
public abstract class MenuComponent {
	/**
	 * 提供的默认实现 让子元素决定是否重写
	 * 
	 * @param menuComponent
	 */
	public void add(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}

	public void remove(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}

	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public float getPrice() {
		throw new UnsupportedOperationException();
	}

	public String getDescription() {
		throw new UnsupportedOperationException();
	}

	public boolean isVegetarian() {
		throw new UnsupportedOperationException();
	}

	public void print() {
		throw new UnsupportedOperationException();

	}

	public abstract Iterator createIterator();
	
}
