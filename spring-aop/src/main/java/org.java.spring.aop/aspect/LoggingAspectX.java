package org.java.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

// demonstrates an alternative way to implement Advise without using Annotations
public class LoggingAspectX {

    public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {

        Object returnValue = null;

        try {

            System.out.println("Before AdviceX");
            returnValue = proceedingJoinPoint.proceed();     // At this line the actual target execution happens. Target code can also be skipped by bypassing ths line.
            System.out.println("After ReturningX");
        } catch (Throwable e) {
            //e.printStackTrace();
            System.out.println("After ThrowingX");
        }

        System.out.println("After Finally");
        return returnValue; // return the value of non void target methods
    }

    //method added for demonstration of MyFactoryService
    public void loggingAdvice() {
        System.out.println("Advice Method Called");
    }

}
