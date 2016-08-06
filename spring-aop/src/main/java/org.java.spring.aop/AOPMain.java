package org.java.spring.aop;

import org.java.spring.aop.service.ShapeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ShapeService shapeservice = context.getBean("shapeservice", ShapeService.class);
        shapeservice.getCircle().setName("New Circle");
        shapeservice.getCircle().setNameAndReturn("New Circle return");
        System.out.println(shapeservice.getCircle().getName());

    }
}

