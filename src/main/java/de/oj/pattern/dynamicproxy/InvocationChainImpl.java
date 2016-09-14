package de.oj.pattern.dynamicproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class defines the chain of invocation handlers which will be called. These invocation handlers
 * are independent of each other, so that this solutions is a low coupling solution. If there is a new
 * handler to call, just create it and add it in the constructor of this class
 *
 *
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
        else{
            System.out.println("there is no other invocation handler to call, nead to call the real method");
            try {
                this.result = method.invoke(caller, args);
                //reset the iterator otherwise the next method which should be proxied will not be handled
                //by the chained invocationhandlers
                tasks = list.iterator();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ChainImpl: called method " + method.getName() + " finished");
        return this.result;
    }
}
