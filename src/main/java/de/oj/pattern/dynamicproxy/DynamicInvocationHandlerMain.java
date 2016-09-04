package de.oj.pattern.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * This starts the sample how to use the dynamic proxy solution of the jdk. For this purpose
 * two InvocationHandlers are created one which puts some log message to the called method {@Link LoggingInvocationHandler}
 * and the other one calculates the duration of the called method {@link TimerInvocationHandler}.
 * The SubjectInterface describes the methods which will be proxied by this sample, the real methods
 * are in the SubjectInteraceImpl {@link SubjectInterfaceImpl}
 *
 * To create a chain of InvocationHandlers which will be called, the InvocationHandlers depends on each other, so that
 * TimerInvocationHandler extends the LoggingInvationHandler which is ugly. A better solution has been found and the
 * sample can be found here {@link DynamicProxyMain}
 * <p/>
 * <p/>
 * Created by oj on 04.09.16.
 */
public class DynamicInvocationHandlerMain {


    public static void main(String[] args) {
        System.out.println("Starting dynamic proxy sample");

        SubjectInterface timerProxy = (SubjectInterface) Proxy.newProxyInstance(SubjectInterface.class.getClassLoader(),
                new Class<?>[]{SubjectInterface.class},
                new TimerInvocationHandler(new SubjectInterfaceImpl()));

        System.out.println("------------------------------");
        timerProxy.methodA("a");
        System.out.println("------------------------------");
        timerProxy.methodB("test b");
        System.out.println("------------------------------");
        timerProxy.methodC(1, "test c");

    }
}


