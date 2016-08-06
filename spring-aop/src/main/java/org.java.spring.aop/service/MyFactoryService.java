package org.java.spring.aop.service;

import org.java.spring.aop.model.Circle;
import org.java.spring.aop.model.Triangle;

// demonstrating How the Spring Framework works
// called in AOPMainCustom factoryService.getBean("shapeService");
public class MyFactoryService {
    public Object getBean(String beanType) {
        if (beanType.equals("shapeService")) return new ShapeService();
        if (beanType.equals("circle")) return new Circle();
        if (beanType.equals("triangle")) return new Triangle();
        return null;
    }
}

