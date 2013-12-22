package com.pgiletich.graphics.ui.instrument.curve;

import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.Polygon;
import com.pgiletich.graphics.scene.object.ClipPolygon;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.event.MouseEvent;

public class PolygonInstrument extends PointListInstrument {
    public PolygonInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(getPointList() == null){
            Polygon poly = new Polygon();
            setPointList(poly);
            getScene().addObject(new ClipPolygon(poly));
        }
        java.awt.Point p = getScene().toSceneCoords(e.getPoint());
        Point newPoint = new Point(p.x, p.y);
        getScene().addObject(new GraphicsPoint(newPoint));
        getPointList().points().add(newPoint);
        getScene().repaint();
    }
}
