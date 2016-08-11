package org.java.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// Read TrianglePoint >> DisplayNameBeanPostProcessor >> MyBeanFactoryPostProcessor
public class DisplayNameBeanPostProcessor implements BeanPostProcessor {

// - BeanPostProcessor used to do some processing before and after 'every' bean initialization
// - It has no destroy method and only used for processing on initialization of a bean
// - PostProcessor methods provides better control of the application;
// - Must initialize DisplayNameBeanPostProcessor in spring.xml

// To process on specific beans use InitializingBean, DisposableBean (myInit() or CleanUp() method)

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

