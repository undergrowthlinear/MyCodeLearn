package mylearncode.undergrowth.designmode.iterator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * �в� ʹ��hashmap�洢 �˵���
 * @author Administrator
 *
 */
public class ChineseFood implements Menu {

	Map<String, MenuItem> items=new HashMap<>();
	
	public ChineseFood(){
		addMenuItem(new MenuItem("������", 45, "���������Ǵ��˵Ĵ���ˣ��ɼ����⡢�������������׳��ƶ��ɣ������ó�", false));
		addMenuItem(new MenuItem("�Ǵ��Ｙ", 78, "�Ǵ��Ｙɫ�����������ɿڡ���������", false));
		addMenuItem(new MenuItem("��Ѽ", 80, "������Ѽ������Ϊ��������ζ�����������⣬������ɫ���������ϸ�ۣ�ζ�����񣬷ʶ��������ɫ������������", false));
	}
	
	
	private void addMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		items.put(menuItem.getName(), menuItem);
	}


	@Override
	public Iterator createIterator() {
		// TODO Auto-generated method stub
		return items.values().iterator();
	}

}
