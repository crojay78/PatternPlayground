package de.oj.pattern.prototype;

import java.awt.Color;
import java.awt.Graphics2D;


/**
 * Die Klasse <code>RectFigure</code> modelliert ein Rechteck.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class RectFigure extends FigureElement
{
    private final int width;
    private final int height;

    public RectFigure(final Color drawingColor, final int x1, final int y1, final int width, final int height)
    {
        super(drawingColor, x1, y1);

        this.width = width;
        this.height = height;
    }

    @Override
    public AbstractGraphicsElement makeCopy()
    {
        return new RectFigure(getDrawingColor(), getPosition().x, getPosition().y, width, height);
    }

    @Override
    public void draw(final Graphics2D g2d)
    {
        final Color oldColor = g2d.getColor();

        g2d.setColor(getDrawingColor());
        g2d.drawRect(getPosition().x, getPosition().y, width, height);
        g2d.setColor(oldColor);
    }
}
