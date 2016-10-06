package de.oj.pattern.prototype;

import java.awt.*;

/**
 * Created by oj on 29.09.16.
 */
public class CircleFigure extends FigureElement {

    final int radius;

    public CircleFigure(final Color drawingColor, final int xMidPoint, final int yMidPoint, final int radius)
    {
        super(drawingColor, xMidPoint, yMidPoint);

        this.radius = radius;
    }



    @Override
    public AbstractGraphicsElement makeCopy() {
        return new CircleFigure(getDrawingColor(), getPosition().x, getPosition().y, radius);
    }

    @Override
    public void draw(Graphics2D g2D) {
        final Color oldColor = g2D.getColor();

        g2D.setColor(getDrawingColor());
        g2D.drawOval(getPosition().x - radius, getPosition().y - radius, radius * 2, radius * 2);

    }


}
