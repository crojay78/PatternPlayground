package de.oj.pattern.dynamicproxy;

import org.apache.log4j.Logger;

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
    final static Logger log = Logger.getLogger(InvocationChainImpl.class);
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

        log.info("working on handlers for method " + method.getName());
        if(tasks.hasNext()){
            Invocation handler = tasks.next();

            log.info("calling next invocation handler in chain: " + handler.getClass().getName());
            Object result = handler.invoke(caller, method, args, this);
            this.result = (this.result == null ? result : this.result);
        }
        else{
            log.info("there is no other invocation handler to call, nead to call the real method");
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
        log.info("invocation handler for  for method " + method.getName() + " is finished");
        return this.result;
    }
}
