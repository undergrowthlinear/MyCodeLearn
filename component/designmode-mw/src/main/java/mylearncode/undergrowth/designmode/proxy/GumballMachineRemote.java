package mylearncode.undergrowth.designmode.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ����ģʽ��
 *   Ϊ��һ�������ṩһ��ռλ�����������Կ��ƶ��������ķ���
 * 
 * ���տ��Ʒ�ʽ�Ĳ�ͬ��
 *   Զ�̴���-->���Ʒ���Զ�̶���(����ͬһ��JVM�Ķ���)
 *   �������-->���Ʒ��ʴ�����Դ�Ķ���(�������IO����)
 *   ��������-->����Ȩ�޿��ƶ���Դ�ķ���(��ͬ��Ȩ�ޣ�����Դ�ķ��ʲ�β�ͬ)
 *   �������-->Ϊ��������������ṩ��ʱ�Ĵ洢
 *   �������ô���-->�����ⱻ����ʱ�����ж���Ĳ���
 *   ����ǽ����-->����������Դ�ķ���
 *   ͬ������-->�ڶ��̵߳������Ϊ�����ṩ��ȫ�ķ���
 *   �������ش���-->��������һ����ĸ��ӶȲ����з��ʿ���
 *   д��ʱ���ƴ���-->�������ƶ���ĸ���
 *   
 * ģʽ�Ա�:
 *   װ����ģʽ-->��װһ�����󣬶����ṩ����Ĺ���
 *   ������ģʽ-->��װһ�����󣬲��ṩ��ͬ�Ľӿ�
 *   ���ģʽ-->��װ���������ṩ���ʵļ򵥽ӿ�
 *   ����ģʽ -->��װһ�������Կ��ƶ����ķ��� 
 *   
 *    
 *  ʾ����
 *    Զ�̴���ʹ��RMI��Ϊʾ�� ,Զ�̷��ʵĻ���EJB��WEBSERVICES
 *    �������ʹ��������Դ�ļ�����Ϊʾ��������JFRAME��Ϊʾ��
 *    ��������ʹ����Դ�ķ�����Ϊʾ������SPRING��AOP�����˱�������   
 *    
 *    
 * 
 * @author Administrator
 *
 */
public interface GumballMachineRemote extends Remote{
    /**
     * ÿ�������������׳�throws RemoteException�쳣 
     * @return
     * @throws RemoteException
     */
	public String getLocation() throws RemoteException;
	public int getCount() throws RemoteException;
	public String  getDescription() throws RemoteException;
	public State getCurrState() throws RemoteException;
	
}
