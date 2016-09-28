package de.oj.pattern.singleton;

/**
 * Thread-Sicherheit ist bei dieser Art der Realisierung dadurch gegeben, dass die
 * Klassenbeschreibung einmal geladen wird und anschließend eine Initialisierung
 * erfolgt, bevor die Klasse für andere Klassen zugreifbar wird.
 * <p/>
 * Created by oj on 28.09.16.
 */
public class ClassicSingleton {

    private static final ClassicSingleton INSTANCE = new ClassicSingleton();
    private int accessCounter = 0;

    public static ClassicSingleton getInstance() {
        return INSTANCE;
    }

    private ClassicSingleton() {

    }

    public int getAccessCounter(){
        accessCounter++;
        return accessCounter;
    }
    public String foobar() {

        return "foobar";

    }


}
