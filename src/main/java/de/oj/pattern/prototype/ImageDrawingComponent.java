package de.oj.pattern.prototype;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

import javax.swing.JFrame;


/**
 * Beispielprogramm f�r das Schablonenmethode-Muster:
 * <br>
 * Die Basisklasse <code>BaseDrawingComponent</code> definiert den grunds�tzlichen Algorithmus und
 * erlaubt es Subklassen an verschiedenen Stellen steuernd einzugreifen.
 * Die abgeleitet Klasse <code>ImageDrawingComponent</code> realisiert dazu die Methode
 * <code>drawContent()</code>, die eine Zeichenfl�che mit Figuren und Linalen sowie
 * Rasterpunkten zeichnet.
 * Die Figuren werden in der Klasse <code>ContentModel</code> gespeichert und verwaltet. 
 * Die Klasse <code>AppFrame</code> visualisiert das Ganze und initialisiert das
 * <code>ContentModel</code> mit drei Figuren.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
@SuppressWarnings("serial")
public final class ImageDrawingComponent extends BaseDrawingComponent implements IModelListener
{
    private static final int   GRID_SIZE_X         = 10;
    private static final int   GRID_SIZE_Y         = 10;
    public static final int    RULER_LENGTH_SMALL  = 3;
    public static final int    RULER_LENGTH_MEDIUM = 8;
    public static final int    RULER_LENGTH_LARGE  = 15;

    private final ContentModel contentModel;

    public ImageDrawingComponent(final ContentModel contentModel)
    {
        this.contentModel = contentModel;
        this.contentModel.addModelListener(this);
    }

    @Override
    public void drawContent(final Graphics2D g2d)
    {
        drawSheet(g2d);
        drawGrid(g2d);

        drawFigures(g2d);

        drawRuler(g2d);
    }

    private void drawSheet(final Graphics2D g2d)
    {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getSize().width, getSize().height);
    }

    private void drawGrid(final Graphics2D g2d)
    {
        g2d.setColor(Color.DARK_GRAY);

        for (int x = 0; x < getSize().width; x += GRID_SIZE_X)
        {
            for (int y = 0; y < getSize().height; y += GRID_SIZE_Y)
            {
                g2d.drawLine(x, y, x, y);
            }
        }
    }

    private void drawFigures(final Graphics2D g2d)
    {
        final Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2.0f));

        for (final AbstractGraphicsElement graphicsFigure : contentModel.getElements())
            graphicsFigure.draw(g2d);

        g2d.setStroke(new BasicStroke(5.0f));

        final AbstractGraphicsElement selectedElement = contentModel.getSelectedElement();
        if (selectedElement != null)
            selectedElement.draw(g2d);

        g2d.setStroke(oldStroke);
    }

    private void drawRuler(final Graphics2D g2d)
    {
        final Dimension size = getSize();

        // draw ruler bars
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, size.width, RULER_LENGTH_LARGE + 1);
        g2d.fillRect(0, 0, RULER_LENGTH_LARGE + 1, size.height);

        // draw ticks
        g2d.setColor(Color.DARK_GRAY);
        for (int x = 0; x < size.width; x += GRID_SIZE_X)
        {
            final int rulerLength = calcRulerLength(x, GRID_SIZE_X);
            g2d.drawLine(x, 0, x, rulerLength);
        }

        for (int y = 0; y < size.height; y += GRID_SIZE_Y)
        {
            final int rulerLength = calcRulerLength(y, GRID_SIZE_Y);
            g2d.drawLine(0, y, rulerLength, y);
        }
    }

    private int calcRulerLength(final int input, final int stepSize)
    {
        // every 10 th line large
        // every 5 th line medium
        // rest small
        final int step = input / stepSize;

        int rulerLength = RULER_LENGTH_SMALL;
        if (step % 5 == 0)
            rulerLength = RULER_LENGTH_MEDIUM;
        if (step % 10 == 0)
            rulerLength = RULER_LENGTH_LARGE;

        return rulerLength;
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

    public static class AppFrame extends JFrame
    {
        AppFrame(final String title, final ContentModel contentModel)
        {
            super(title);

            add(new ImageDrawingComponent(contentModel));
            
            contentModel.addElement(new LineFigure(Color.RED, 20, 20, 100, 100));
            contentModel.addElement(new RectFigure(Color.BLUE, 40, 40, 250, 150));
            contentModel.addElement(new OvalFigure(Color.GREEN, 300, 125, 50, 25));
            
            setSize(500, 300);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    public static void main(String[] args)
    {
        final ContentModel contentModel = new ContentModel();
        final AppFrame appFrame = new AppFrame("ImageDrawingComponent", contentModel);
        appFrame.setVisible(true);
    }
}
