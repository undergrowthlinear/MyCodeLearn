package mylearncode.undergrowth.designmode.proxy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * �������-->���ڴ����������Դ
 *   ʹ�ô��� ��δ������Դʱ����ʾ��ʾ��Ϣ
 * @author Administrator
 *
 */
public class LoadImageUrlFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu menu;
	JMenuBar menuBar;
	Map<String, String> imageResources = new HashMap<String, String>();
	ImageComponent imageComponent;

	public LoadImageUrlFrame() {

		initResources();

		menu = new JMenu("����ͼƬ");
		//��Ӳ˵���
		addMenuItem(menu);
		menuBar = new JMenuBar();
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		//��ʼ��ͼƬ
		Icon icon=null;
		try {
			icon = new ImageProxy(new URL(imageResources.get("������")));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���ͼƬ���
		imageComponent=new ImageComponent(icon);
		getContentPane().add(imageComponent);
		
		// ��ʼ������
		setSize(800, 500);
		setLocation((getToolkit().getScreenSize().width-getWidth())/2, (getToolkit().getScreenSize().height-getHeight())/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * ��Ӳ˵���
	 * @param menu2
	 */
	private void addMenuItem(JMenu menu2) {
		// TODO Auto-generated method stub
		for (Iterator iterator = imageResources.keySet().iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			//��Ӳ˵���
			JMenuItem menuItem=new JMenuItem(name);
			menuItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//������˵����ʱ��
					//�滻ͼƬ����е�ͼƬ ����frame�ػ�
					try {
						//�滻ͼ������е�ͼƬ
						imageComponent.setIcon(new ImageProxy(new URL(imageResources.get(e.getActionCommand()))));
						//�ػ����
						repaint();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			menu2.add(menuItem);
		}
	}

	/**
	 * ��ʼ��ͼƬ��Դ
	 */
	private void initResources() {
		// TODO Auto-generated method stub
		imageResources
				.put("��������",
						"http://cdn.duitang.com/uploads/item/201207/27/20120727130449_hSxQk.jpeg");
		imageResources.put("������",
				"http://ent.shangdu.com/stardata/P_5962597_1__1869907784.jpg");

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoadImageUrlFrame frame = new LoadImageUrlFrame();

	}

}
