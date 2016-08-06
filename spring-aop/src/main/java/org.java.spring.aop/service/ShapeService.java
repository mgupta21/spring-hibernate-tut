package org.java.spring.aop.service;


import org.java.spring.aop.model.Circle;
import org.java.spring.aop.model.Triangle;

// we are not creating circle or triangle objects directly but creating a shape interface that crates the instance of circle/triangle class which then can be used to call name methods of these classes
public class ShapeService {

    private Circle circle;
    private Triangle triangle;

    public Circle getCircle() {
        System.out.println("My Factory Demonstration");
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Triangle getTriangle() {
        return triangle;
    }

    public void setTriangle(Triangle triangle) {
        this.triangle = triangle;
    }

}

