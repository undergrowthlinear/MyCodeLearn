package mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryByScan {

	@Autowired(required = false)
	@StudentQualifier
	private QualifierStudent student;

	public QualifierStudent getStudent() {
		return student;
	}

	public void setStudent(QualifierStudent student) {
		this.student = student;
	}

}
