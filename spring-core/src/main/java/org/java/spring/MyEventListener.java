package org.java.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/* Adv. 2 of ApplicationContext over beanFactory : provides support for event listener
- For Spring event handling we need an event (DrawEvent), a publisher (defined in class triggering/publishing the event)
and listener that listens the event;

- Event class takes publisher class as argument and listener takes event as an argument
- ApplicationEventPublisherAware interface provides us the publisher which is an ApplicationContext Object that implements an Application's event);
interface has publishEvent() method */

@Component // not defined in spring.xml, used annotation
public class MyEventListener implements ApplicationListener { // implement ApplicationListener to register any class as Listener

    // if no user events are defined then it still captures the application events
    // ApplicationListener interface method
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());

    }

}

