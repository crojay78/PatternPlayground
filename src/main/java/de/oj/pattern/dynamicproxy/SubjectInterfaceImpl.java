package de.oj.pattern.dynamicproxy;

/**
 * Created by oj on 02.09.16.
 */
public class SubjectInterfaceImpl implements SubjectInterface
{

    public void methodA(String a) {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SubjectImpl: method a has been called");
    }

    public void methodB(String b) {
        System.out.println("SubjectImpl: method b is called");
    }

    public String methodC(int i, String s) {
        System.out.println("SubjectImpl: method c is called");
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return s;
    }
}
