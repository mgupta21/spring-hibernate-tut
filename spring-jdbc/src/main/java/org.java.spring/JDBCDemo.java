package org.java.spring;

import org.java.spring.dao.JdbcDaoImpl;
import org.java.spring.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JDBCDemo {

    public static void main(String[] args) {

        // using simple JDBC
        //Circle circle = new JdbcDaoImpl().getCircle(1);
        //System.out.println(circle.getName());

        // using Spring
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        JdbcDaoImpl dao = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);

        Circle circle = dao.getCircle(1);

        System.out.println(dao.getCircleCount());
        System.out.println(dao.getCircleName(1));
        System.out.println(dao.getCircleForId(1).getName());
        System.out.println(dao.getAllCircles().size());

        //dao.insertCircle(new Circle(2, "Second Circle"));
        //dao.createTriangleTable();

        dao.insertCircleNM(new Circle(3, "Third Circle"));


    }

}

	