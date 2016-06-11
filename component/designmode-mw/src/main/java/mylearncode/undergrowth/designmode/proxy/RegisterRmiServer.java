package mylearncode.undergrowth.designmode.proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
/**
 * RMIע�����Ͳ���
 * @author Administrator
 *
 */
public class RegisterRmiServer {
	
	public static void registerRmi(int port,String name,GumballMachineRemote remoteServer){
		try {
			//����register�˿ڼ���
			LocateRegistry.createRegistry(port);
			//�󶨷���
			Naming.rebind(name, remoteServer);
			//ע��Զ�̷���ɹ�
			System.out.println("ע��Զ�̷���ɹ�");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static GumballMachineRemote getRmiServer(String name){
		try {
			return (GumballMachineRemote) Naming.lookup(name);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
