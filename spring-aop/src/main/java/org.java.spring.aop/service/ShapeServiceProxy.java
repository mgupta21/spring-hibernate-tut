package org.spring.aop.service;


import org.java.spring.aop.aspect.LoggingAspectX;
import org.java.spring.aop.model.Circle;
import org.java.spring.aop.service.ShapeService;

public class ShapeServiceProxy extends ShapeService {

    public Circle getCircle() {
        new LoggingAspectX().loggingAdvice();
        return super.getCircle();
    }
}

