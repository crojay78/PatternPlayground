package de.oj.topics.clone;

/**
 * Created by oj on 13.10.16.
 */
public class Car implements Cloneable{

    private Engine engine;
    private String color;
    private int seats;

    public Car(Engine engine, String color, int seats) {
        this.engine = engine;
        this.color = color;
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getColor() {

        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Engine getEngine() {

        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", color='" + color + '\'' +
                ", seats=" + seats +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
