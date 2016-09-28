package de.oj.pattern.singleton;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * Created by oliverjazic on 28.09.16.
 */
public class ClassicSingletonTest extends TestCase {

    final static Logger log = Logger.getLogger(ClassicSingletonTest.class);

    @Test
    public void testSingleton(){

        ClassicSingleton singleton = ClassicSingleton.getInstance();
        ClassicSingleton singletonCopy = ClassicSingleton.getInstance();
        log.info("accesscounter " + singleton.getAccessCounter());
        log.info("accesscounter " + singleton.getAccessCounter());
        log.info("accesscounter " + singletonCopy.getAccessCounter());
        assertTrue(singleton.getAccessCounter() == 4);

        log.info(singleton);
        log.info(singletonCopy);
        assertTrue(singleton == singletonCopy);

    }

}
