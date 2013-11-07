package com.pgiletich.graphics.ui;

import com.pgiletich.graphics.graphics.GraphicsScene;
import com.pgiletich.graphics.graphics.object.CDALine;
import com.pgiletich.graphics.model.Line;

import java.awt.event.MouseEvent;

public class CDALineInstrument extends PaintingInstrument {
    private CDALine line;
    public CDALineInstrument(GraphicsScene scene) {
        super(scene);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        line = new CDALine(new Line(e.getX(), e.getY(), e.getX(), e.getY()));
        getScene().addObject(line);
        getScene().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        line.getShape().end.x = e.getX();
        line.getShape().end.y = e.getY();
        getScene().repaint();
    }
}
