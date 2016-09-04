package de.oj.pattern.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * Here is a nicer version how to create a chain of invocation handlers. In this solution the invocation handlers
 * does not know anything from each other.
 * Thanks to this post the solution has been created:
 *
 * http://stackoverflow.com/questions/39283264/how-to-create-chain-of-dynamic-proxies/39284420#39284420
 *
 * Created by oj on 02.09.16.
 */
public class DynamicProxyMain {

    public static void main(String[] args) {

        System.out.println("Starting dynamic proxy sample");

        SubjectInterface proxy = (SubjectInterface) Proxy.newProxyInstance(SubjectInterface.class.getClassLoader(),
                new Class[]{SubjectInterface.class},
                new DynamicProxyHandler(new SubjectInterfaceImpl()));


        System.out.println("------------------------------");
        proxy.methodA("a");
        System.out.println("------------------------------");
        proxy.methodB("test b");
        System.out.println("------------------------------");
        proxy.methodC(1, "test c");
        System.out.println("------------------------------");

    }
}
