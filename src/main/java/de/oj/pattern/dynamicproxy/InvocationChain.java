package de.oj.pattern.dynamicproxy;

import java.lang.reflect.Method;

/**
 * Created by oj on 02.09.16.
 */
public interface InvocationChain {
    Object invoke(Object caller, Method method, Object [] args);
}