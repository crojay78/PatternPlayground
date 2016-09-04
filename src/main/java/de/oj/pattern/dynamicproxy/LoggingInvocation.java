package de.oj.pattern.dynamicproxy;

import java.lang.reflect.Method;

/**
 * Created by oj on 03.09.16.
 */
public class LoggingInvocation implements Invocation {



    @Override
    public Object invoke(Object caller, Method method, Object[] args, InvocationChain chain) {
        System.out.println("LoggingInvocation: called method is " + method.getName());
        chain.invoke(caller, method, args);
        System.out.println("LoggingInvocation: called ended");

        return null;
    }
}
