package de.oj.pattern.prototype;

import java.awt.Color;
import java.awt.Graphics2D;


/**
 * Die Klasse <code>RectFigure</code> modelliert eine Linie.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class LineFigure extends FigureElement
{
    private int x2;
    private int y2;
    
    public LineFigure(final Color drawingColor, final int x1, final int y1, final int x2, final int y2)
    {
        super(drawingColor,x1, y1);

        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public AbstractGraphicsElement makeCopy()
    {
        return new LineFigure(getDrawingColor(), getPosition().x, getPosition().y, x2, y2);
    }

    @Override
    public void draw(final Graphics2D g2d)
    {
        final Color oldColor = g2d.getColor();
        
        g2d.setColor(getDrawingColor());
        g2d.drawLine(getPosition().x, getPosition().y, x2, y2);
        g2d.setColor(oldColor);  
    }
    
    public void setPosition(int newX, int newY)
    {
        final int dx = newX - this.getPosition().x;
        final int dy = newY - this.getPosition().y;
        
        super.setPosition(newX, newY);
        
        x2 += dx;
        y2 += dy;        
    }
}