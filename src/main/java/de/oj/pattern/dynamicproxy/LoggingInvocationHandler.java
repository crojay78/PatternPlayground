package de.oj.pattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by oj on 02.09.16.
 */
public class LoggingInvocationHandler implements InvocationHandler {

    Object impl;

    public LoggingInvocationHandler(Object impl){
        this.impl = impl;

    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object retVal;
        System.out.println("LoggingHandler:" + this.getClass().getName() + " has been called");
        retVal = method.invoke(impl, args);
        System.out.println("LoggingHandler:" + this.getClass().getName() + " has ended");
        return retVal;

    }
}
