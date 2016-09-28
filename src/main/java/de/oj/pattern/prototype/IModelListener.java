package de.oj.pattern.prototype;

import java.util.List;

/**
 * Created by oj on 29.09.16.
 */
public interface IModelListener
{
    public void imageElementsChanged(final List<AbstractGraphicsElement> images);
    public void pdfElementsChanged(final List<AbstractGraphicsElement> pdfs);
    public void nameChanged(final String newName);
}