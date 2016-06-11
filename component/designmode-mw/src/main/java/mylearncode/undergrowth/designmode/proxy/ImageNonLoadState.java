package mylearncode.undergrowth.designmode.proxy;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class ImageNonLoadState implements ImageState {

	ImageProxy imageProxy;
	public ImageNonLoadState(ImageProxy imageProxy){
		this.imageProxy=imageProxy;
	}
	
	@Override
	public void paintIcon(final Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.drawString("���ڼ���ͼƬ�С�����", x, y);
		//ʹ���߳� ����������Դ ������ɺ� �ػ����
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//�޸�ͼƬ״̬
				imageProxy.setIcon(new ImageIcon(imageProxy.getImageUrl()));
				imageProxy.setImageCurr(imageProxy.getImageLoadState());
				//�ػ�
				c.repaint();
			}
		}).start();
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 400;
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 400;
	}

}
