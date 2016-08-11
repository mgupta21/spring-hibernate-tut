package org.java.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

// Read TrianglePoint >> DisplayNameBeanPostProcessor >> MyBeanFactoryPostProcessor
// BeanFactoryPostProcessor -> Calls overriden method before BeanFactory or ApplicationContext is initialized
// BeanPostProcessor -> Calls methods when a bean is initialized
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        System.out.println("My bean factory post processor called");

    }
}

