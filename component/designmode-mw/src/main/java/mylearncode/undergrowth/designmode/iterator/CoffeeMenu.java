package mylearncode.undergrowth.designmode.iterator;

import java.util.Iterator;

/**
 * �������˵� ʹ������洢�˵���
 * @author Administrator
 *
 */
public class CoffeeMenu implements Menu {

	MenuItem[] items;
	int count=0;
	public CoffeeMenu(){
		items=new MenuItem[5];
		addMenuItem(new MenuItem("��������", 100, "������ţ�̵Ľ���", true));
		addMenuItem(new MenuItem("Խ�Ͽ���", 200, "���ô�����Խ�Ͽ��ȶ�", true));
		addMenuItem(new MenuItem("��ʽ����", 80, "���µķ�ζ�в�ʧ����", true));
		addMenuItem(new MenuItem("����������", 220, "����ɰ�ǡ���������", true));
		addMenuItem(new MenuItem("�ʼҿ���", 280, "�����ڹ�֮͢��", true));
	}
	
	
	private void addMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		items[count++]=menuItem;
	}


	@Override
	public Iterator createIterator() {
		// TODO Auto-generated method stub
		return new CoffeeIterator(items);
	}

}
