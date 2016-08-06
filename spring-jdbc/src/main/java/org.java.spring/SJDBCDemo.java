package org.java.spring;

import org.java.spring.dao.HibernateDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SJDBCDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		//SimpleJdbcDaoImpl dao = context.getBean("simpleJdbcDaoImpl", SimpleJdbcDaoImpl.class);

		//bean id = hibernateDaoImpl not declared in spring.xml as it is annotated by @Repository
		HibernateDaoImpl dao = context.getBean("hibernateDaoImpl", HibernateDaoImpl.class);
		System.out.println(dao.getCircleCount());

	}

}
