package mylearncode.undergrowth.designmode.observer.util;

import java.util.Observable;
import java.util.Observer;

public class ObserverSys implements Observer {

	public ObserverSys(Observable observable){
		observable.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof SubjectSys){
			SubjectSys sys=(SubjectSys) o;
			System.out.println(ObserverSys.class+"\tƽ����:"+sys.getAverageGrade()+"\t��߷�:"+sys.getGradeMax());
		}
	}

}
