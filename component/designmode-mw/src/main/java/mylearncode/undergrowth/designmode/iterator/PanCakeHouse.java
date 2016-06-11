package mylearncode.undergrowth.designmode.iterator;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ����ݲ˵�  ʹ��ArrayList��Ų˵���
 * @author Administrator
 *
 */
public class PanCakeHouse implements Menu {

	List<MenuItem> items=new ArrayList<MenuItem>();
	
	public PanCakeHouse(){
		addMenuItem(new MenuItem("�ȸ���", 10, "�ص����人�ȸ���", true));
		addMenuItem(new MenuItem("��������", 8, "���ڵ����Ϲ�������", true));
		addMenuItem(new MenuItem("��������", 80, "���ݵĵص����", false));
	}
	
	private void addMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		items.add(menuItem);
	}

	@Override
	public Iterator createIterator() {
		// TODO Auto-generated method stub
		return items.iterator();
	}

}
