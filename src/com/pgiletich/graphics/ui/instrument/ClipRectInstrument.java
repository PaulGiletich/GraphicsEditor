package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.model.Rect;
import com.pgiletich.graphics.scene.object.ClipRect;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ClipRectInstrument extends InstrumentStrategy {
    private Rect rect;

    public ClipRectInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        com.pgiletich.graphics.model.Point start = new com.pgiletich.graphics.model.Point(p.x, p.y);
        com.pgiletich.graphics.model.Point end = new com.pgiletich.graphics.model.Point(p.x, p.y);

        rect = new Rect(start, end);
        getScene()
                .addObject(new ClipRect(rect))
                .addObject(new GraphicsPoint(start))
                .addObject(new GraphicsPoint(end))
                .repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        rect.bottomRight().setX(p.x);
        rect.bottomRight().setY(p.y);
        getScene().repaint();
    }
}
