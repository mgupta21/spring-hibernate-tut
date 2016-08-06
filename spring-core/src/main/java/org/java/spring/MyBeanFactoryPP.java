package org.java.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPP implements BeanFactoryPostProcessor {
// BeanFactoryPostProcessor calls the method before BeanFactory/ ApplicationContext is initialized while BeanPostProcessor calls methods when a bean is initialized

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        System.out.println("My bean factory post processor called");

    }
}

