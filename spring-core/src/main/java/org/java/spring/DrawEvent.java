package org.java.spring;

import org.springframework.context.ApplicationEvent;

// read myEventListener.java first
public class DrawEvent extends ApplicationEvent { // extend ApplicationEvent to register as a Event

    // need to call constructor on extending ApplicationEvent
    public DrawEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "Draw event occured";
    }
}
