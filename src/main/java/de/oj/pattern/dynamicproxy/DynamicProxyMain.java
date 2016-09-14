package de.oj.pattern.dynamicproxy;

import org.apache.log4j.Logger;

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

    final static Logger log = Logger.getLogger(DynamicProxyMain.class);

    public static void main(String[] args) {

        log.info("Starting dynamic proxy sample, with a chain of invocation handlers");

        SubjectInterface proxy = (SubjectInterface) Proxy.newProxyInstance(SubjectInterface.class.getClassLoader(),
                new Class[]{SubjectInterface.class},
                new DynamicProxyHandler(new SubjectInterfaceImpl()));


        log.info("------------------------------");
        proxy.methodA("a");
        log.info("------------------------------");
        proxy.methodB("test b");
        log.info("------------------------------");
        proxy.methodC(1, "test c");
        log.info("------------------------------");

    }
}
