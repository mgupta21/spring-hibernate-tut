package org.java.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/* For Spring event handling (2nd adv. of ApplicationContext over beanFactory) we need an event (DrawEvent), publisher (defined in class triggering event) which publishes the event and listener that listens the event;

Event takes source class which triggers the event as argument and listner takes event as an argument
ApplicationEventPublisherAware interface provides us the publisher which is an ApplicationContext Object that implements an Application's event); interface has publishEvent() method */

@Component // not defined in spring.Xml used annotations
public class myEventListener implements ApplicationListener { // implement ApplicationListener to register any class as Listener

    // if no user events are defined then it still captures the application events
    // ApplicationListener interface method
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());

    }

}

