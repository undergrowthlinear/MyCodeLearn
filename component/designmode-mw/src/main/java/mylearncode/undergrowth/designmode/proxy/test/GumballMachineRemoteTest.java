package mylearncode.undergrowth.designmode.proxy.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import javax.imageio.spi.RegisterableService;

import org.junit.Test;

import mylearncode.undergrowth.designmode.proxy.GumballMachine;
import mylearncode.undergrowth.designmode.proxy.GumballMachineRemote;
import mylearncode.undergrowth.designmode.proxy.RegisterRmiServer;

public class GumballMachineRemoteTest {

	@Test
	public void test() {
		try {
			//����Զ�̷���
			GumballMachineRemote remoteServer=new GumballMachine("�����ܸ�", "��ѧ����ߵ»�", 100);
			//��
			int port=4567;
			RegisterRmiServer.registerRmi(port,"rmi://192.168.1.101:"+port+"/remoteServer", remoteServer);
			//�ȴ��ͻ��˵Ĳ���RMI����
			while(true){
				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClient(){
		int port=4567;
		GumballMachineRemote remoteServer=RegisterRmiServer.getRmiServer("rmi://192.168.1.101:"+port+"/remoteServer");
		try {
			System.out.println(remoteServer.getCount());
			System.out.println(remoteServer.getLocation());
			System.out.println(remoteServer.getDescription());
			System.out.println(remoteServer.getCurrState());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
