package de.oj.pattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by oliverjazic on 02.09.16.
 */
public class TimerInvocationHandler extends LoggingInvocationHandler implements InvocationHandler{
    private Object impl;

    public TimerInvocationHandler(Object impl) {
        super(impl);
        this.impl = impl;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object retVal = null;
        System.out.println("TimeHandler: getting duration time for method " + method.getName());
        long duration = -System.currentTimeMillis();
        retVal = super.invoke(proxy,method,args);
        duration += System.currentTimeMillis();
        System.out.println("TimeHandler: duration time handler has ended, it took " + duration + " milliseconds");
        return retVal;
    }
}
