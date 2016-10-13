package de.oj.topics.clone;

/**
 * Created by oj on 13.10.16.
 */
public class Engine {
    private String type;
    private int PS;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPS() {
        return PS;
    }

    public void setPS(int PS) {
        this.PS = PS;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Engine engine = (Engine) o;

        if (PS != engine.PS) return false;
        return type != null ? type.equals(engine.type) : engine.type == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + PS;
        return result;
    }

    public Engine(String type, int PS) {
        this.type = type;

        this.PS = PS;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "type='" + type + '\'' +
                ", PS=" + PS +
                '}';
    }
}
