package de.oj.pattern.dynamicproxy;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by oj on 03.09.16.
 */
public class LoggingInvocation implements Invocation {
    final static Logger log = Logger.getLogger(LoggingInvocation.class);


    @Override
    public Object invoke(Object caller, Method method, Object[] args, InvocationChain chain) {
        log.info("LoggingInvocation: called method is " + method.getName());
        chain.invoke(caller, method, args);
        log.info("LoggingInvocation: called ended");

        return null;
    }
}
