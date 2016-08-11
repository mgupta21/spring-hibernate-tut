package org.java.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

// Allows TriangleAware bean to be aware of ApplicationContext object defined in the DrawingApp.java
public class TriangleAware implements ApplicationContextAware, BeanNameAware {

    private Point pointA;
    private Point pointB;
    private Point pointC;
    private ApplicationContext context = null;

    // defined in interface ApplicationContextAware
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    // defined in interface BeanNameAware; used to get the name of the bean defined for this class in the Spring.Xml before code in this class executes
    @Override
    public void setBeanName(String beanName) {
        System.out.println("Bean Name is " + beanName);
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }
}
// If triangle bean is singelton then its object and the object of dependent beans (point A, B, C) are initialized only once
// by spring.xml container even if the scope of dependant bean is prototype 
// So if we want to create new instances of Point A, B and C whenever required in the code then we implement
// ApplicationContextAware , so we can do context.getBean(Point.class) in this class (rather them initializing Point A, B and C in spring.xml)
// and then call point A , B and C and define new points here

// Other aware interfaces which allow beans to be aware of context object are ApplicationEventPublisherAware, BeanClassLoader Aware
// BeanNameAware