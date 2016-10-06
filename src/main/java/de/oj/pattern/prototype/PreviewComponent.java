package de.oj.pattern.prototype;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;



/**
 * Hilfsklasse f�r das Beispielprogramm f�r das Prototyp-Muster:
 * Die Klasse <code>PreviewComponent</code> bietet die Darstellung einer verkleinerten 
 * Vorschau- bzw. �bersichtsansicht der Figuren, die in der Klasse  
 * <code>ContentModel</code> gespeichert sind.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
@SuppressWarnings("serial")
public final class PreviewComponent extends Component implements IModelListener
{
    private final ContentModel contentModel;

    public PreviewComponent(final ContentModel contentModel)
    {
        this.contentModel = contentModel;
        this.contentModel.addModelListener(this);
    }

    public void paint(final Graphics g)
    {
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getSize().width, getSize().height);
        g2d.setColor(Color.YELLOW);
        drawFigures(g2d);
    }

    private void drawFigures(final Graphics2D g2d)
    {
        g2d.scale(0.2f, 0.2f);
        for (final AbstractGraphicsElement graphicsFigure : contentModel.getElements())
            graphicsFigure.draw(g2d);
    }

    @Override
    public void nameChanged(final String newName)
    {
        // nothing to do here
    }

    @Override
    public void imageElementsChanged(final List<AbstractGraphicsElement> images)
    {
        repaint();
    }

    @Override
    public void pdfElementsChanged(final List<AbstractGraphicsElement> pdfs)
    {
        repaint();
    }
}