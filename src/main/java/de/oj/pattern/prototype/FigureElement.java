package de.oj.pattern.prototype;

import java.awt.*;

/**
 * Created by oj on 29.09.16.
 */
public abstract class FigureElement extends AbstractGraphicsElement{

    public Color getDrawingColor() {
        return drawingColor;
    }

    private Color drawingColor = Color.BLACK;

    protected FigureElement(final Color drawingColor, final int x, final int y){
        super(x,y);
        setDrawingColor(drawingColor);
    }


    public void setDrawingColor(Color drawingColor) {
        this.drawingColor = drawingColor;
    }
}
