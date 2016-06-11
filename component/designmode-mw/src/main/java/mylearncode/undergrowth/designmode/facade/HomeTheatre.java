package mylearncode.undergrowth.designmode.facade;


/**
 * ���ԭ��
 *   ����֪ʶԭ��ֻ��������ѽ�̸
 * Ϊ��ֻ��������ѽ�̸�������з���Ӧ��ֻ������һ�������������
 *   1����������
 *   2��������Ϊ����������κ����
 *   3��������Ϊ�����������д���
 *   4���˷�����������ʵ����������
 *   1��3��4����ζ�Ų�Ҫ�ڵ��������������صĶ����ϵ����䷽��  
 *   
 *   
 * ���ģʽ
 *   �ṩ��һ��ͳһ�Ľӿڣ�����������ϵͳ��һȺ�ӿ�
 *   ������һ���߲�ӿڣ�����ϵͳ������ʹ��  
 * ���ģʽֻ���ṩ��һ����ϵͳ�ķ��ʽӿڣ�����Ӱ����ϵͳ�ĵ���ʹ��
 * ���ģʽǿ��ͳһ�ͼ򻯽ӿڣ���������ģʽǿ��ת���ӿ�
 * 
 * ʾ��
 *   ��ͥӰԺ
 * 
 * @author Administrator
 *
 */
public class HomeTheatre {

	Amplifier amp;
	Projector pro;
	Screen scr;
	Curtain cur;
	public HomeTheatre(Amplifier amp,Projector pro,Screen scr,Curtain cur){
		this.amp=amp;
		this.pro=pro;
		this.cur=cur;
		this.scr=scr;
	}
	
	public void watchMovie(){
		cur.close();
		scr.down();
		pro.open();
		amp.openAmp();
		amp.setPlayer(0);
		amp.setVolume(10);
	}
	public void closeMovie(){
		amp.closeAmp();
		pro.close();
		scr.up();
		cur.open();
	}
	
	
}
