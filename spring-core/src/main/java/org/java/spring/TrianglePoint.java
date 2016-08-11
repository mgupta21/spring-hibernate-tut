package org.java.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// - InitializingBean call afterPropertiesSet() method before bean’s properties are initialized
// - DisposableBean call destroy() method before bean is destroyed
public class TrianglePoint implements InitializingBean, DisposableBean, Shape {

    private Point pointA;
    private Point pointB;
    private Point pointC;

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    public void draw() {
        System.out.printf("Point A's X is: " + getPointA().getX() + " Point A's Y is: " + getPointA().getY());
        System.out.printf("Point B's X is: " + getPointB().getX() + " Point B's Y is: " + getPointB().getY());
        System.out.printf("Point C's X is: " + getPointC().getX() + " Point C's Y is: " + getPointC().getY());
    }

    // defined in DisposableBean interface and is called before the bean is destroyed when registerShutdownHook is called in drawingApp
    @Override
    public void destroy() throws Exception {
        System.out.println("Bean is destroyed now");
    }

    // defined in InitializingBean interface and is called after the bean properties are initialized in spring.Xml
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Triangle bean is initialized");
    }

    // this is user defined init() methods can be used instead of afterPropertiesSet() methods of interface;
    // basically does the same thing but no need to implement interfaces and are initiated by spring.Xml code init-method="myInit”;
    // if both interface's and this methods are defined then first call interface's afterPropertiesSet() and then myInit()
    public void myInit() {
        System.out.println("My Init Method is called");
    }

    // same as destroy() of DisposableBean interface;
    // destroy-method="cleanup" in spring.xml's TrianglePoint bean;
    // but if both interface method and this method are defined then first call interface's destroy() and then cleanup()
    public void cleanup() {
        System.out.println("My cleanup method");
    }

// if myInit and cleanup method needs to be called on all beans initialization and destroy and not just on TrianglePoint's bean
// then declare them globally in spring.xml as <beans default-init-method="myInit" default-destroy-method="cleanup">
// other way to call initialization methods is by def. postProcessors which do some processing after a bean is initialized
// eg: DisplayNameBeanPostProcessor defines methods to be called after and before initialization of each bean

}
