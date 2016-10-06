package de.oj.pattern.prototype;

import java.awt.Color;
import java.awt.Graphics2D;


/**
 * Die Klasse <code>RectFigure</code> modelliert ein Oval.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class OvalFigure extends FigureElement
{
    final int xRadius;
    final int yRadius;

    public OvalFigure(final Color drawingColor, final int xMidPoint, final int yMidPoint, final int xRadius, final int yRadius)
    {
        super(drawingColor, xMidPoint, yMidPoint);

        this.xRadius = xRadius;
        this.yRadius = yRadius;
    }

    @Override
    public AbstractGraphicsElement makeCopy()
    {
        return new OvalFigure(getDrawingColor(), getPosition().x, getPosition().y, xRadius, yRadius);
    }

    @Override
    public void draw(final Graphics2D g2d)
    {
        final Color oldColor = g2d.getColor();

        g2d.setColor(getDrawingColor());
        g2d.drawOval(getPosition().x - xRadius, getPosition().y - yRadius, xRadius * 2, yRadius * 2);
        g2d.setColor(oldColor);
    }
}
