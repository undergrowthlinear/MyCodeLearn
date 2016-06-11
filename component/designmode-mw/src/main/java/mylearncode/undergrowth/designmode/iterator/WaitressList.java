package mylearncode.undergrowth.designmode.iterator;

import java.util.Iterator;
import java.util.List;

import mylearncode.undergrowth.designmode.adapter.EnumeIteratorAdapter;


/**
 * ����Ա ���ڴ�ӡ�˵� Ϊ�˸�������չ ʹ��List��Ų˵�
 * @author Administrator
 *
 */
public class WaitressList {
	
	List<Menu> menus;
	public WaitressList(List<Menu> menus) {
		super();
		this.menus = menus;
	}

	public void printMenu(){
		System.out.println("==============��ʼ��ӡ�˵�==============");
		Iterator<Menu> menusIterator=menus.iterator();
		while(menusIterator.hasNext()){
			Menu menu=menusIterator.next();
			printMenu(menu.createIterator());
		}
		System.out.println("==============������ӡ�˵�==============");
	}

	private void printMenu(Iterator<MenuItem> iteratorMenu) {
		// TODO Auto-generated method stub
		System.out.println("   ===========��ʼ��ӡ�²˵�===========   ");
		for (Iterator iterator = iteratorMenu; iterator.hasNext();) {
			MenuItem miItem = (MenuItem) iterator.next();
			System.out.println(miItem);
		}
		System.out.println("   ===========������ӡ�²˵�===========   ");
	}
	
	
}
