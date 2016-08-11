package org.java.spring;

import org.springframework.context.ApplicationEvent;

// read MyEventListener.java first
public class DrawEvent extends ApplicationEvent { // extend ApplicationEvent to register as a Event

    // When a class extends ApplicationEvent, it needs a constructor with publisher as argument
    public DrawEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "Draw event occured";
    }
}
