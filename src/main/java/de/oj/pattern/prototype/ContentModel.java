package de.oj.pattern.prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Modellklasse f�r einige Beispielprogramme zur Demonstartion verschiedener Muster:
 * Die Klasse ContentModel speichert verschiedene grafische Figuren.
 * Sie unterst�tzt das Beobachter-Muster und erlaubt es, dass sich �nderungsinteressenten
 * anmelden, die fortan �ber �nderungen informiert werden.

 * @author Michael Inden
 *
 * Copyright 2011 by Michael Inden
 */
public final class ContentModel
{
    private final List<AbstractGraphicsElement> elements  = new ArrayList<>();

    AbstractGraphicsElement                     selectedElement;

    private final List<IModelListener>          listeners = new CopyOnWriteArrayList<>();

    protected final List<AbstractGraphicsElement> getElements()
    {
        return elements;
    }

    public void addElement(final AbstractGraphicsElement newElement)
    {
        elements.add(newElement);
        selectedElement = newElement;

        notifyModelListeners();
    }

    public void removeAllElements()
    {
        elements.clear();
        selectedElement = null;
        notifyModelListeners();
    }

    /**
     * @param selectedElement the selectedElement to set
     */
    protected final void setSelectedElement(final AbstractGraphicsElement selectedElement)
    {
        this.selectedElement = selectedElement;
    }

    public AbstractGraphicsElement getSelectedElement()
    {
        return selectedElement;
    }

    public void addModelListener(final IModelListener newListener)
    {
        listeners.add(newListener);
    }

    public void removeModelListener(final IModelListener listenerToRemove)
    {
        listeners.remove(listenerToRemove);
    }

    public void notifyModelListeners()
    {
        for (final IModelListener listener : listeners)
            listener.imageElementsChanged(new ArrayList<AbstractGraphicsElement>(elements));
    }
}
