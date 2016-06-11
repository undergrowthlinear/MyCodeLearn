package mylearncode.undergrowth.designmode.facade;


/**
 * ����
 * @author Administrator
 *
 */
public class Amplifier {
	
	 public void openAmp(){
		 System.out.println("�򿪹���");
	 }
	
	 public void closeAmp(){
		 System.out.println("�رչ���");
	 }
	 
	 public void setVolume(int volume){
		 System.out.println("��������Ϊ"+5);
	 }
	 
	 public void setPlayer(int num){
		 switch (num) {
		case 1:
			System.out.println("����CD����");
			break;
		default:
			System.out.println("����DVD����");
			break;
		}
		 
	 }
}
