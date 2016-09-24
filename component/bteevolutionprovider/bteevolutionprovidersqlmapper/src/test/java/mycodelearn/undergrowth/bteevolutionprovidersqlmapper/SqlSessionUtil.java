package mycodelearn.undergrowth.bteevolutionprovidersqlmapper;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {

	private static SqlSessionFactory sqlSessionFactory;

	/**
	 * 单利获取SqlSessionFactory
	 * 
	 * @param fileName
	 *            mybatis配置的文件名
	 * @return SqlSessionFactory
	 * @throws IOException
	 */
	public static SqlSessionFactory getSqlSessionFactory(String fileName) throws IOException {
		if (sqlSessionFactory == null) {
			synchronized (SqlSessionUtil.class) {
				if (sqlSessionFactory == null) {
					InputStream inputStream = Resources.getResourceAsStream(fileName);
					sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				}
			}
		}

		return sqlSessionFactory;
	}

	public static SqlSession getSqlSession() {
		// TODO Auto-generated method stub
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionUtil.getSqlSessionFactory("mybatis-conf.xml").openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlSession;
	}
}
