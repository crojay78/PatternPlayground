package de.oj.pattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by oj on 02.09.16.
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;
    InvocationChain chain;

    DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
        this.chain = new InvocationChainImpl();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return chain.invoke(proxied, method, args);
    }
}
