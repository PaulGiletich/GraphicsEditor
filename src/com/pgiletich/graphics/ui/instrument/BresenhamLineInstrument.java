package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.scene.object.BresenhamLine;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BresenhamLineInstrument extends PaintingInstrument {
    private BresenhamLine line;

    public BresenhamLineInstrument(GraphicsScene scene) {
        super(scene);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        line = new BresenhamLine(new Line(p.x, p.y, p.x, p.y));
        getScene().addObject(line);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        line.getShape().end.x = p.x;
        line.getShape().end.y = p.y;
        getScene().repaint();
    }
}
