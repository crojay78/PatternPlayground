package de.oj.pattern.dynamicproxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by oj on 02.09.16.
 */
public class InvocationChainImpl implements InvocationChain {
    List<Invocation> list = new ArrayList<>();
    Object result;
    Iterator<Invocation> tasks;

    InvocationChainImpl(){
        list.add(new LoggingInvocation());
        list.add(new TimerInvocation());
        tasks = list.iterator();
    }

    @Override
    public Object invoke(Object caller, Method method, Object[] args) {

        System.out.println("ChainImpl: called method " + method.getName() + " started");
        if(tasks.hasNext()){

            Object result = tasks.next().invoke(caller, method, args, this);
            this.result = (this.result == null ? result : this.result);
        }
        System.out.println("ChainImpl: called method " + method.getName() + " finished");
        return this.result;
    }
}
