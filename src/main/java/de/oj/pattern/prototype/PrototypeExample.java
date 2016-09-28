package de.oj.pattern.prototype;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * Beispielprogramm f�r das Prototyp-Muster:
 * Die Klasse ContentModel speichert verschiedene grafische Figuren.
 * Die Klasse ActionHandler operiert auf den Daten des Modells. Sie stellt den
 * Controller aus der MVC-Architektur dar.
 * Die Klasse AppFrame visualisiert das Ganze und entspricht dem View, in dem
 * �ber verschiedene Toolbar-Buttons neue Figuren erzeugt werden k�nnen.
 * Die eigentliche Darstellung erfolgt in der Klasse ImageDrawingComponent.
 * Mithilfe der Buttons Copy und Paste kann das aktuell selektierte Element
 * kopiert und erneut eingef�gt werden. Dieser Vorgang entspricht dem Prototyp-Muster.
 * <br>
 * Au�erdem bietet die Darstellung eine verkleinerte Vorschau- bzw. �bersichtsansicht.
 *
 * @author Michael Inden
 *
 * Copyright 2011 by Michael Inden
 */
public final class PrototypeExample
{
    public static void main(final String[] args)
    {
        final ContentModel  contentModel  = new ContentModel();
        final ActionHandler actionHandler = new ActionHandler(contentModel);

        final AppFrame appFrame = new AppFrame("PrototypeExample", contentModel, actionHandler);
        appFrame.setVisible(true);
    }

    public static class AppFrame extends JFrame
    {
        private final ContentModel  contentModel;

        public AppFrame(final String title, final ContentModel contentModel, final ActionHandler actionHandler)
        {
            super(title);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            this.contentModel = contentModel;

            contentModel.addElement(new LineFigure(Color.RED, 20, 20, 100, 100));
            contentModel.addElement(new RectFigure(Color.BLUE, 40, 40, 250, 150));
            contentModel.addElement(new OvalFigure(Color.GREEN, 300, 125, 50, 25));

            final JPanel panel = new JPanel(new BorderLayout());
            panel.add(actionHandler.createToolbar(), BorderLayout.NORTH);
            panel.add(new ImageDrawingComponent(contentModel), BorderLayout.CENTER);
            panel.add(createPreviewPanel(), BorderLayout.EAST);

            add(panel);

            // Actions korrekt initialisieren
            actionHandler.refreshActionState();

            setSize(500, 300);
        }

        private JPanel createPreviewPanel()
        {
            final JPanel previewPanel = new JPanel(new BorderLayout());
            previewPanel.setBorder(BorderFactory.createEtchedBorder());
            final JLabel previewText = new JLabel("VORSCHAU");
            previewPanel.add(previewText, BorderLayout.NORTH);
            previewPanel.add(new PreviewComponent(contentModel), BorderLayout.CENTER);
            return previewPanel;
        }
    }
}