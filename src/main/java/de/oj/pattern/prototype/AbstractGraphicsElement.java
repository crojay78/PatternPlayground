package de.oj.pattern.prototype;

import java.awt.*;

/**
 * Created by oj on 29.09.16.
 */
public abstract class AbstractGraphicsElement {

    public Point getPosition() {
        return pos;
    }

    public void setPosition(int x, int y ) {
        pos = new Point(x, y);
    }

    private Point pos;

    public AbstractGraphicsElement(int x, int y){
        pos = new Point(x, y);
    }

    public abstract AbstractGraphicsElement makeCopy();
    public abstract void draw(final Graphics2D g2D);

}
