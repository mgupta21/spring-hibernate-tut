package org.java.spring;

// first read triangles and then myeventlistner and then DrawEvent

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component // (generic) no need to define circle bean in spring.xml + add <context:component-scan
//@Service //gives additional info that component is a service role
//@Repository //gives additional info that component is a Data object role
//@Controller //gives additional info that component is a controller role
public class Circle implements Shape, ApplicationEventPublisherAware {
// Source class of event must implement ApplicationEventPublisherAware interface

    public Point radius;
    // use getMessage method on object to access messages defined in contextmessage.properties
    private MessageSource messagesource;
    // publisher object to publish event though publishEvent method
    private ApplicationEventPublisher publisher;

    public Point getRadius() {
        return radius;
    }

    //@Required /validate if radius is set in the spring.xml via RequiredAnnotationBeanPostProcessor
    @Autowired //Autowires looks @byType then @byName then @byQualifier @byConstructor
    @Qualifier("circleRelated")
    //@Resource // Javax annotation
    public void setRadius(Point radius) {
        this.radius = radius;
    }

    public void draw() {
        //System.out.println("Circle drawn of radius points: " + radius.getX() + " & "  +  radius.getY());

        // replace by arguments by those defined in .properties file {}
        System.out.println(this.messagesource.getMessage("circle.draw", new Object[]{radius.getX(), radius.getY()}, "Didn't find key in contextmessage.properties", null));

        System.out.println(this.messagesource.getMessage("greeting", null, "Didn't find key in contextmessage.properties", null));

// pass this as class name to constructor which takes source of event as a parameter
        DrawEvent drawevent = new DrawEvent(this);
        publisher.publishEvent(drawevent);

    }

    //@PostConstruct //initialization through annotations(javax)
    public void initalizeCircle() {
        System.out.println("Init of Circle");
    }

    //@PreDestroy
    public void destroyCircle() {
        System.out.println("destroy of Circle");
    }

    // ApplicationEventPublisherAware interface method; register publisher as ApplicationEventPublisher
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Autowired // autowired to bean id="messageSource" in Spring.Xml
    public MessageSource getMessagesource() {
        return messagesource;
    }

    public void setMessagesource(MessageSource messagesource) {
        this.messagesource = messagesource;
    }

}
