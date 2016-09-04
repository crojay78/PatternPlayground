package de.oj.pattern.dynamicproxy;

import java.lang.reflect.Proxy;

/**
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
