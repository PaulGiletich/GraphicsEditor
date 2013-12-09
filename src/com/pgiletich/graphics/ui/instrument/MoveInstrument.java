package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.scene.object.GraphicsObject;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveInstrument extends InstrumentStrategy {
    private GraphicsObject movingObject = null;

    public MoveInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        movingObject = getScene().getObjectByPoint(p);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = getScene().toSceneCoords(e.getPoint());
        if(movingObject != null){
            ((com.pgiletich.graphics.model.Point)movingObject.getShape()).x = p.x;//TODO change this when making movable all the items
            ((com.pgiletich.graphics.model.Point)movingObject.getShape()).y = p.y;
        }
        getScene().repaint();
    }
}
