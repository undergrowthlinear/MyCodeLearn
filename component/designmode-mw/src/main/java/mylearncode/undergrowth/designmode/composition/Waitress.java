package mylearncode.undergrowth.designmode.composition;

import java.util.Iterator;

public class Waitress {
	
	MenuComponent menuComponent;

	public Waitress(MenuComponent menuComponent) {
		super();
		this.menuComponent = menuComponent;
	}
	
	public void print(){
		menuComponent.print();
	}
	
	/**
	 * 打印菜单中的素菜菜单项
	 */
	public void printVegetarianName(){
		Iterator iteratorAll=menuComponent.createIterator();
		while (iteratorAll.hasNext()) {
			MenuComponent currMenuComponent = (MenuComponent) iteratorAll.next();
			try { 
				//表示是菜单项 并且是素菜
				if(currMenuComponent.isVegetarian()){
					System.out.println(currMenuComponent);
				}
			} catch (Exception e) {
				// TODO: handle exception
				//菜单 会抛出异常
			}
		}
	}
	
}
