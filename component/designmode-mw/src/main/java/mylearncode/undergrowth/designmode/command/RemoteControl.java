package mylearncode.undergrowth.designmode.command;

import java.util.Arrays;

/**
 * ����ģʽ
 *   �������װ�ɶ���ʹ��ͬ��������־����������������������
 *   
 *  ˵ʵ�����������ģʽ�Ķ�����Ĳ��ö� 
 * 
 * �򵥵�˵������ģʽ���Խ���������ĵ����ߺͽ���ִ������Ķ������
 * ԭ�����ڣ���������а����˽����ߺ�ִ�ж���
 *    �������߽��յ��������󣬵�����������еķ���������������ҵ������߽���ִ����صĲ���
 * 
 * ���մ����̶� ��������󻮷�Ϊ��
 *   ��Ϳ���������--ֻ�Ǹ���ת�������ߵ������ɽ����������صĲ���
 *   �������������--�������ߵĴ������Ҳ����
 *   ��Ȼ Ϊ�˸��õĽ��� �����Ƽ���Ϳ���������
 *    
 * ʵ����
 *   ����ң���� ���Ƶ�ơ���ˮ���Ŀ���
 *   ң�������ǵ����� ��Ƶ���Ϊ������ߵƷ�װ�����������
 * 
 * @author Administrator
 *
 */
public class RemoteControl {
   
	private final int num=2;
	
	Command[] commandOns=new Command[num];
	Command[] commandOffs=new Command[num];
	Command lastCommand=null;
	
	public RemoteControl(){
		NoCommand noCommand=new NoCommand();
		for(int i=0;i<num;i++)
		{
			commandOns[i]=noCommand;
			commandOffs[i]=noCommand;
		}
	}
	
	public void setCommand(int position,Command commandOn,Command commandOff) {
		commandOns[position]=commandOn;
		commandOffs[position]=commandOff;
	}
	
	public void buttonOnWasPress(int position){
		commandOns[position].execute();
		lastCommand=commandOns[position];
	}
	
	public void buttonOffWasPress(int position){
		commandOffs[position].execute();
		lastCommand=commandOffs[position];
	}
	
	public void cancel()
	{
		lastCommand.undo();
	}

	
	@Override
	public String toString() {
		return "RemoteControl [num=" + num + ", commandOns="
				+ Arrays.toString(commandOns) + ", commandOffs="
				+ Arrays.toString(commandOffs) + ", lastCommand=" + lastCommand
				+ "]";
	}
	
	
	
}
