package org.java.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class DisplayNameBeanPostProcessor implements BeanPostProcessor {

// BeanPostProcessor used to do some processing before and after every bean initialization; It has no destroy method and only used for processing on initialization of a bean; Calls interface methods on all beans (like triangle, PontA , PointB and PointC so total 4*2 = 8 printLn called)

// PostProcessor methods provides better control of the application; DisplayNameBeanPostProcessor must be initialized in spring.xml to use on application

//To process on specific beans use InitializingBean, DisposableBean (myInit() or CleanUp() method)

    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("In After Initialization. Bean Name is " + beanName);
        return bean;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("In Before Initialization. Bean Name is " + beanName);
        return bean;
    }
}

