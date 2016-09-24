package mylearncode.undergrowth.spring.mw.generic.dependency;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {
	@Autowired
	public BaseDao<T> baseDao;

	public void service() {
		System.out.println(baseDao);
		baseDao.say();
	}
}
