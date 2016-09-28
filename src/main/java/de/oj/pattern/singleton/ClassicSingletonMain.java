package de.oj.pattern.singleton;

import org.apache.log4j.Logger;

/**
 * Created by oj on 28.09.16.
 */
public class ClassicSingletonMain {

    private final static Logger log = Logger.getLogger(ClassicSingletonMain.class);

    public static void main(String[] args){
        log.info("singleton created " + ClassicSingleton.getInstance().foobar());

    }

}
