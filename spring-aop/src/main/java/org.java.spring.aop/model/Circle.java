package org.java.spring.aop.model;


import org.java.spring.aop.aspect.Loggable;

public class Circle {

    private String name;

    public String getName() {
        return name;
    }

    @Loggable    //User Defined Annotation to apply advice to selected methods
    public void setName(String name) {
        this.name = name;
        System.out.println("Circle's setter called");
        //throw(new RuntimeException());
    }

    public String setNameAndReturn(String name) {
        this.name = name;
        System.out.println("Circle's setter called");
        //throw(new RuntimeException());
        return name;
    }

}
