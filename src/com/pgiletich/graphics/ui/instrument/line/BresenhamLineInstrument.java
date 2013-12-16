package com.pgiletich.graphics.ui.instrument.line;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.scene.object.line.BresenhamLine;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BresenhamLineInstrument extends AbstractLineInstrument {

    public BresenhamLineInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        com.pgiletich.graphics.model.Point start = new com.pgiletich.graphics.model.Point(p.x, p.y);
        com.pgiletich.graphics.model.Point end = new com.pgiletich.graphics.model.Point(p.x, p.y);

        Line line = new Line(start, end);
        setLine(line);
        getScene()
                .addObject(new BresenhamLine(line))
                .addObject(new GraphicsPoint(start))
                .addObject(new GraphicsPoint(end))
                .repaint();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        getLine().end().setX(p.x);
        getLine().end().setY(p.y);
        getScene().repaint();
    }
}
