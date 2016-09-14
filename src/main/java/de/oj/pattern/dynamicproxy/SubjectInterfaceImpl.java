package de.oj.pattern.dynamicproxy;

import org.apache.log4j.Logger;

/**
 * Created by oj on 02.09.16.
 */
public class SubjectInterfaceImpl implements SubjectInterface
{
    final static Logger log = Logger.getLogger(SubjectInterfaceImpl.class);


    public void methodA(String a) {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            log.error("Exception in methodA", e);
        }
        log.info("-->SubjectImpl: method a has been called<--");
    }

    public void methodB(String b) {
        log.info("-->SubjectImpl: method b is called<--");
    }

    public String methodC(int i, String s) {
        log.info("-->SubjectImpl: method c is called<--");
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            log.error("Exception in methodC", e);
        }

        return s;
    }
}
