package de.oj.pattern.prototype;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * Die Klasse <code>BaseDrawingComponent</code> modelliert eine Komponente
 * im Schablonenmethode-Muster.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011, 2014 by Michael Inden 
 */
public abstract class BaseDrawingComponent extends JComponent
{   
    public final void paint(final Graphics g)
    {
        super.paint(g);

        final Graphics2D g2d = (Graphics2D) g;

        drawBackground(g2d);
        drawContent(g2d);
        
        postDraw(g2d);         // hook
    }
    
    private void drawBackground(final Graphics2D g2d)
    {
        final Dimension componentSize = getSize();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, componentSize.width, componentSize.height);
    }
        
    protected abstract void drawContent(final Graphics2D g2d);
    
    protected void postDraw(final Graphics2D g2d)
    {
    }
}