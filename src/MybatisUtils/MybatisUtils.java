package MybatisUtils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtils {

	private static SqlSessionFactory sqlSessionFactory = null;
	static{
		try{

			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static SqlSession getSqlSession(){
		return sqlSessionFactory.openSession();
	}
	public static SqlSessionFactory getSessionFactory(){
		return sqlSessionFactory;
	}

}

