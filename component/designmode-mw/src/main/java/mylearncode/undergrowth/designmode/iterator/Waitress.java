package mylearncode.undergrowth.designmode.iterator;

import java.util.Iterator;


/**
 * ����Ա ���ڴ�ӡ�˵�
 * @author Administrator
 *
 */
public class Waitress {
	
	Menu panMenu;
	Menu coffeeMenu;
	Menu chineseMenu;
	public Waitress(Menu panMenu, Menu coffeeMenu, Menu chineseMenu) {
		super();
		this.panMenu = panMenu;
		this.coffeeMenu = coffeeMenu;
		this.chineseMenu = chineseMenu;
	}
	
	public void printMenu(){
		System.out.println("==============��ʼ��ӡ�˵�==============");
		System.out.println("   ===========��ʼ��ӡ���Ȳ˵�===========   ");
		Iterator<MenuItem> iteratorMenu=coffeeMenu.createIterator();
		printMenu(iteratorMenu);
		System.out.println("   ===========������ӡ���Ȳ˵�===========   ");
		System.out.println("   ===========��ʼ��ӡ�й��˲˵�===========   ");
		iteratorMenu=chineseMenu.createIterator();
		printMenu(iteratorMenu);
		System.out.println("   ===========������ӡ�й��˲˵�===========   ");
		System.out.println("   ===========��ʼ��ӡ����ݲ˵�===========   ");
		iteratorMenu=panMenu.createIterator();
		printMenu(iteratorMenu);
		System.out.println("   ===========������ӡ����ݲ˵�===========   ");
		System.out.println("==============������ӡ�˵�==============");
	}

	private void printMenu(Iterator<MenuItem> iteratorMenu) {
		// TODO Auto-generated method stub
		for (Iterator iterator = iteratorMenu; iterator.hasNext();) {
			MenuItem miItem = (MenuItem) iterator.next();
			System.out.println(miItem);
		}
	}
	
	
}
