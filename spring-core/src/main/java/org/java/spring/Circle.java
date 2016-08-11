package org.java.spring;

// Read triangles >> MyEventlistener >> DrawEvent >> Circle

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component // (generic) no need to define circle bean in spring.xml + add <context:component-scan
//@Service (specific) //gives additional info that component is a service role. All your business logic should be in Service classes.
//@Repository (specific) //gives additional info that component is a Data object role. All your database access logic should be in DAO classes
//@Controller (specific) //gives additional info that component is a controller role

// Source aka event publisher class must implement ApplicationEventPublisherAware interface
public class Circle implements Shape, ApplicationEventPublisherAware {

    public Point radius;
    // use getMessage method on object to access messages defined in contextmessage.properties
    private MessageSource messagesource;

    // publisher object to publish event though publishEvent method
    private ApplicationEventPublisher publisher;

    public Point getRadius() {
        return radius;
    }

    //@Required / Validate if radius is set in the spring.xml via RequiredAnnotationBeanPostProcessor
    @Autowired
    // Default is a type driven injection. Autowire's looks @byType then @byName then @byQualifier @byConstructor.
    @Qualifier("circleRelated") // spring annotation used to further fine-tune autowiring.
    //@Resource // Javax annotation // used for wiring by name
    public void setRadius(Point radius) {
        this.radius = radius;
    }

    public void draw() {
        //System.out.println("Circle drawn of radius points: " + radius.getX() + " & "  +  radius.getY());

        // replace by arguments by those defined in .properties file {}
        System.out.println(this.messagesource.getMessage("circle.draw", new Object[]{radius.getX(), radius.getY()}, "Didn't find key in contextmessage.properties", null));

        System.out.println(this.messagesource.getMessage("greeting", null, "Didn't find key in contextmessage.properties", null));

        // pass publisher to event constructor
        DrawEvent drawevent = new DrawEvent(this);
        publisher.publishEvent(drawevent);

    }

    // ApplicationEventPublisherAware interface
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    //@PostConstruct //initialization through annotations(javax)
    public void initalizeCircle() {
        System.out.println("Init of Circle");
    }


    //@PreDestroy
    public void destroyCircle() {
        System.out.println("destroy of Circle");
    }

    @Autowired // autowired to bean id="messageSource" in Spring.Xml
    public MessageSource getMessagesource() {
        return messagesource;
    }

    public void setMessagesource(MessageSource messagesource) {
        this.messagesource = messagesource;
    }
}
