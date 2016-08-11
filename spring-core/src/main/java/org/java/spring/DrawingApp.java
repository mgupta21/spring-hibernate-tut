package org.java.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp {

    public static void main(String[] args) {
        //Triangle triangle = new Triangle();
        //BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
        //Triangle triangle = (Triangle)factory.getBean("triangle");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        //Triangle triangle = (Triangle)context.getBean("triangle");
        //TrianglePoint triangle = (TrianglePoint)context.getBean("triangle");
        //TrianglePoints triangle = (TrianglePoints)context.getBean("triangle");
        //TrianglePoint triangle = (TrianglePoint)context.getBean("ChildTriangle");
        //Circle circle = (Circle)context.getBean("circle");

        // calling through ResourceBundleMessageSource
        System.out.println(context.getMessage("greeting", null, "DefaultGreeting", null));

        Shape shape = (Shape) context.getBean("circle");

        //triangle.draw();
        //circle.draw();
        shape.draw();

        // AbstractApplication object is used when need to register  the hook to close the applicationContext
        // which is never the case in Enterprise applications
        AbstractApplicationContext context2 = new ClassPathXmlApplicationContext("spring.xml");
        context2.registerShutdownHook();
    }

}

