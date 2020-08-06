package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
 
public class MyBatisHelper {

	
	private static SqlSessionFactory sqlSessionFactory;
	//��̬��
	static{
		try {
	 
		// mybatis �����ļ�
		String resource = "mybatis.xml";
		// ���������ļ�
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// �����Ự����  ==>  23 ���ģʽ   ����ģʽ
		 sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
 	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static  SqlSession openSession() {
		return sqlSessionFactory.openSession();
	}
}
