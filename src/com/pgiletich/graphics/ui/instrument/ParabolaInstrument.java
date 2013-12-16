package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.model.Parabola;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.object.GraphicsParabola;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.event.MouseEvent;

public class ParabolaInstrument extends InstrumentStrategy {
    private Parabola parabola;
    private java.awt.Point lastMousePos;

    public ParabolaInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        java.awt.Point p = getScene().toSceneCoords(e.getPoint());
        Point pos = new com.pgiletich.graphics.model.Point(p.x, p.y);

        parabola = new Parabola(pos, 0);
        getScene()
                .addObject(new GraphicsParabola(parabola))
                .addObject(new GraphicsPoint(pos))
                .repaint();
        lastMousePos = p;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        java.awt.Point p = getScene().toSceneCoords(e.getPoint());
        parabola.setP((int) (lastMousePos.getX()-p.getX()));
        getScene().repaint();
    }
}
