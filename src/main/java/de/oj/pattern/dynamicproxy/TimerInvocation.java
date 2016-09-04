package de.oj.pattern.dynamicproxy;

import java.lang.reflect.Method;

/**
 * Created by oj on 04.09.16.
 */
public class TimerInvocation implements Invocation {
    @Override
    public Object invoke(Object caller, Method method, Object[] args, InvocationChain chain) {
        System.out.println("TimerInvocation started");
        long duration = -System.currentTimeMillis();
        chain.invoke(caller, method, args);
        duration += System.currentTimeMillis();

        System.out.println("Timer: execution took " + duration + " ms");

        return null;
    }
}
