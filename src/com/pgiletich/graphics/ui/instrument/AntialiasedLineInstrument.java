package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.scene.object.AntialiasedLine;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AntialiasedLineInstrument extends PaintingInstrument {
    private AntialiasedLine line;
    public AntialiasedLineInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        line = new AntialiasedLine(new Line(p.x, p.y, p.x, p.y));
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
