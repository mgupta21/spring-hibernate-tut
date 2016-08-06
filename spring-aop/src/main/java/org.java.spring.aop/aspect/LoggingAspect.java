package org.java.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.java.spring.aop.model.Circle;

@Aspect // annotated as aspect (informs application that class is not model class but Aspect)
public class LoggingAspect {

	/*Advice defines what needs to be applied and when;
Aspect is applying the advice at the pointcuts/methods
	Joinpoint is when the advice is applied to method
	Pointcut is the joinpoints or methods where the advice needs to be applied
	*/

    @Pointcut("execution(* get*())") //define joinpoint at all getters()
    public void allGetters() {
    }

    @Pointcut("within(org.spring.aop.model.Circle)") // Applies to all circle methods
    public void allCircleMethods() {
    }

    // allGetters()/allCircleMethods() are pointcuts that defines methods where LoggingAdvice is applicable
    @Before("allGetters() && allCircleMethods()")
    //public String getName() ,public * get*(), * * get*(), public * get*(*), public * get*(..)
    public void LoggingAdvice() {
        System.out.println("Advice run, Get Method called");
    }

    @Before("allGetters()")
    public void secondAdvice() {
        System.out.println("Second Advice Method");
    }

    //Pointcut specifies all points/methods in code where the advice is applied
    @Before("allGetters() && allCircleMethods()")
    public void LoggingJoinAdvice(JoinPoint joinpoint) {
// joinPoint can be passed as argument to advice to capture method where the advice is applied
        System.out.println(joinpoint.toString()); // prints info about method where adivice applied
        System.out.println(joinpoint.getTarget());

// using getTarget we can get hold of the objects of class on who's methods the advice is run
        Circle circle = (Circle) joinpoint.getTarget();
    }

    @Before("args(String)") // called for methods that takes string arguments
    public void stringArgumentMethods() {
        System.out.println("A method that takes string arguments has been called");
    }

    @Before("args(name)") // called for methods that takes string argument and capturing the argument in name variable
    public void stringArgumentMethods(String name) { // passing the argument to advice method
        System.out.println("A method that takes string arguments has been called " + name);
    }

    @After("args(name)") // Runs after the method called
    public void stringArgumentMethodsA(String name) {
        System.out.println("A method that takes string arguments has been called " + name);
    }

    @AfterReturning("args(name)") // runs after method is executed successfully without exceptions.
    public void stringArgumentMethodsAR(String name) {
        System.out.println("A method that takes string arguments has been called " + name);
    }

    @AfterReturning(pointcut = "args(name)", returning = "returned")
    // runs after method is executed successfully only; captures the returned object
    public void stringArgumentMethodsAR(String name, Object returned) {
        System.out.println("A method that takes string arguments has been called " + name + " The output value is" + returned);
    }

    @AfterThrowing("args(name)") // runs after the method has thrown an exception.
    public void stringArgumentMethodsAT(String name) {
        System.out.println("An exception has been thrown");
    }

    @AfterThrowing(pointcut = "args(name)", throwing = "ex")
    // runs after the method has thrown an exception and captures the exception in ex variable
    public void stringArgumentMethodsATP(String name, RuntimeException ex) {
        System.out.println("An exception has been thrown");
    }

    @Around("@annotation(org.spring.aop.aspect.Loggable)")
// Loggable is user defined annotation used to target specific methods to //apply advice using @Loggable (@XXX) keyword before target method eg: check Circle.java setName()

//@Around("allGetters()")
    public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
// every around advice method must take ProceedingJoinPoint as an argument that captures the actual method/joinpoint so we can publish advises around the target methods

        Object returnValue = null;

        try {

            System.out.println("Before Advice"); // before target method advice
            returnValue = proceedingJoinPoint.proceed(); // At this line the actual target method execution happens. Target code can also be skipped by bypassing this line.
            System.out.println("After Returning"); // after target method advice
        } catch (Throwable e) {
            //e.printStackTrace();
            System.out.println("After Throwing"); // if a method throws exception print this advice
        }

        System.out.println("After Finally");
        return returnValue; // return the value of target method if the target method is not void

        //@AfterReturning also captures the returning object but we cannot modify returned value there but we can do so here
    }


}
