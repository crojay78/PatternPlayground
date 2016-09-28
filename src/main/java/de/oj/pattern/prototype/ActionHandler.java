package de.oj.pattern.prototype;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JToolBar;



/**
 * Die Klasse ActionHandler ist ein Teil des Beispielprogramms f�r das Prototyp-Muster:
 * Die Klasse ActionHandler operiert auf den Daten des Modells. Sie stellt den
 * Controller aus der MVC-Architektur dar.
 * Die Klasse ActionHandler definiert dazu verschiedene Aktionen vom Typ AbstractAction, die
 * in der Klasse AppFrame zur Verkn�pfung und Behandlung verschiedener Toolbar-Buttons
 * dient. Mit deren Hilfe k�nnen neue Figuren erzeugt werden. Des Weiteren erlauben die
 * Buttons Copy und Paste ein Kopie des aktuell selektierten Elements
 * Dieser Vorgang entspricht dem Prototyp-Muster.
 *
 * @author Michael Inden
 *
 * Copyright 2011 by Michael Inden
 */
class ActionHandler
{
    private Point                   insertPos       = new Point(10, 10);

    private AbstractGraphicsElement clipboardElement;

    private final ContentModel      contentModel;

    private final AbstractAction    newLineAction   = new AbstractAction("Line")
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            final Color drawingColor = chooseRandomColor();
            final int x1 = 10 + (int) (100 * Math.random());
            final int y1 = 10 + (int) (100 * Math.random());
            final int x2 = 100 + (int) (50 * Math.random());
            final int y2 = 100 + (int) (50 * Math.random());
            contentModel.addElement(new LineFigure(drawingColor, x1,
                    y1, x2, y2));
            refreshActionState();
        }
    };

    private final AbstractAction    newRectAction   = new AbstractAction("Rect")
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            final Color drawingColor = chooseRandomColor();
            final int x1 = 100 + (int) (150 * Math.random());
            final int y1 = 50 + (int) (100 * Math.random());
            final int x2 = 20 + (int) (100 * Math.random());
            final int y2 = 20 + (int) (50 * Math.random());
            contentModel.addElement(new RectFigure(drawingColor, x1,
                    y1, x2, y2));
            refreshActionState();
        }
    };

    private final AbstractAction    newCircleAction = new AbstractAction("Oval")
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            final Color drawingColor = chooseRandomColor();
            final int x1 = 150 + (int) (200 * Math.random());
            final int y1 = 100 + (int) (100 * Math.random());
            final int x2 = 10 + (int) (50 * Math.random());
            final int y2 = 10 + (int) (50 * Math.random());
            contentModel.addElement(new OvalFigure(drawingColor, x1,
                    y1, x2, y2));
            refreshActionState();
        }
    };

    private final AbstractAction    newClearAction  = new AbstractAction("Clear")
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            contentModel.removeAllElements();
            refreshActionState();
        }
    };

    private final AbstractAction    newCopyAction   = new AbstractAction("Copy")
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            handleCopyElement();
            refreshActionState();
        }
    };

    private final AbstractAction    newPasteAction  = new AbstractAction("Paste")
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            handlePasteElement();
            refreshActionState();
        }
    };

    ActionHandler(final ContentModel contentModel)
    {
        this.contentModel = contentModel;
    }

    public void handleCopyElement()
    {
        final AbstractGraphicsElement selected = contentModel.getSelectedElement();

        if (selected != null)
        {
            insertPos = selected.getPosition();
            clipboardElement = selected.makeCopy();
        }
        else
        {
            throw new IllegalStateException("Copy must only be activated if an element is selected!");
        }
    }

    public void handlePasteElement()
    {
        if (clipboardElement != null)
        {
            // Kaskadierende Position
            increaseImageInsertPos();

            final Point insertPos = getImageInsertPos();
            clipboardElement.setPosition(insertPos.x, insertPos.y);

            contentModel.addElement(clipboardElement);

            // hier nochmal kopieren, damit wir mehrfach pasten k�nnen
            // und neue Elemente ins Modell aufnehmen
            clipboardElement = clipboardElement.makeCopy();
        }
        else
        {
            throw new IllegalStateException("Paste must only be activated if an element is selected!");
        }
    }

    private Point getImageInsertPos()
    {
        return insertPos;
    }

    private void increaseImageInsertPos()
    {
        insertPos = new Point(insertPos.x + 10, insertPos.y + 10);
    }

    public void refreshActionState()
    {
        final boolean hasSelected = (contentModel.getSelectedElement() != null);
        newCopyAction.setEnabled(hasSelected);

        final boolean hasClipboardElement = (clipboardElement != null);
        newPasteAction.setEnabled(hasClipboardElement);
    }

    private Color chooseRandomColor()
    {
        final int r = 20 + (int) (180 * Math.random());
        final int g = 20 + (int) (180 * Math.random());
        final int b = 20 + (int) (180 * Math.random());

        return new Color(r, g, b);
    }

    public JToolBar createToolbar()
    {
        final JToolBar toolbar = new JToolBar();

        toolbar.add(newLineAction);
        toolbar.add(newRectAction);
        toolbar.add(newCircleAction);
        toolbar.add(newClearAction);
        toolbar.add(newCopyAction);
        toolbar.add(newPasteAction);

        return toolbar;
    }
}
