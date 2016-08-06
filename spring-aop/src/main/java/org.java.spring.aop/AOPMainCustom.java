package org.java.spring.aop;

import org.java.spring.aop.service.MyFactoryService;
import org.java.spring.aop.service.ShapeService;

// custom factory framework
public class AOPMainCustom {

    public static void main(String[] args) {
        MyFactoryService factoryService = new MyFactoryService();
        ShapeService shapeService = (ShapeService) factoryService.getBean("shapeService");
        shapeService.getCircle();
    }

}

