package com.pgiletich.graphics.ui;

import com.pgiletich.graphics.graphics.GraphicsScene;
import com.pgiletich.graphics.graphics.object.BresenhamLine;
import com.pgiletich.graphics.model.Line;

import java.awt.event.MouseEvent;

public class BresenhamLineInstrument extends PaintingInstrument {
    private BresenhamLine line;

    public BresenhamLineInstrument(GraphicsScene scene) {
        super(scene);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        line = new BresenhamLine(new Line(e.getX(), e.getY(), e.getX(), e.getY()));
        getScene().addObject(line);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        line.getShape().end.x = e.getX();
        line.getShape().end.y = e.getY();
        getScene().repaint();
    }
}
