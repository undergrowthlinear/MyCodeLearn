package mylearncode.undergrowth.designmode.observer;

public class StudentMObserver implements MObserver {

	private Subject subject;
	
	public StudentMObserver(Subject subject){
		this.subject=subject;
		subject.registerObserver(this);
	}
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object[] data) {
		// TODO Auto-generated method stub
		for (Object object : data) {
			System.out.println(StudentMObserver.class+"\t��������Ϊ:"+object);
		}
	}

	@Override
	public void update(Subject subject) {
		// TODO Auto-generated method stub
		if(subject instanceof GradeSubject){
			GradeSubject gradeSubject=(GradeSubject) subject;
			System.out.println(StudentMObserver.class+"\t��߷�Ϊ��"+gradeSubject.getGradeMax()+" ƽ����Ϊ:"+gradeSubject.getAverageGrade());
		}
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
