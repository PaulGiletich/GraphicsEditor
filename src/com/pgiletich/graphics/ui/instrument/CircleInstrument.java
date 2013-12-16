package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.model.Circle;
import com.pgiletich.graphics.scene.object.GraphicsCircle;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CircleInstrument extends InstrumentStrategy {
    private Circle circle;

    public CircleInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        com.pgiletich.graphics.model.Point pos = new com.pgiletich.graphics.model.Point(p.x, p.y);
        com.pgiletich.graphics.model.Point radius = new com.pgiletich.graphics.model.Point(p.x, p.y);

        circle = new Circle(pos, radius);
        getScene()
                .addObject(new GraphicsCircle(circle))
                .addObject(new GraphicsPoint(pos))
                .addObject(new GraphicsPoint(radius))
                .repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        circle.getRadius().setX(p.x).setY(p.y);
        getScene().repaint();
    }
}
