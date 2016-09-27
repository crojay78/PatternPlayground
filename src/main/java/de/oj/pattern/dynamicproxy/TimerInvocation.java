package de.oj.pattern.dynamicproxy;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by oj on 04.09.16.
 */
public class TimerInvocation implements Invocation {
    final static Logger log = Logger.getLogger(TimerInvocation.class);

    @Override
    public Object invoke(Object caller, Method method, Object[] args, InvocationChain chain) {
        log.info("-->TimerInvocation started");
        long duration = -System.currentTimeMillis();
        chain.invoke(caller, method, args);
        duration += System.currentTimeMillis();

        log.info("-->Timer: execution took " + duration + " ms");

        return null;
    }
}
