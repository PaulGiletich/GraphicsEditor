package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.PointList;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.scene.object.GraphicsTriangulation;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.event.MouseEvent;

public class TriangulationInstrument extends InstrumentStrategy {
    private PointList pointList;

    public TriangulationInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(pointList == null){
            pointList = new PointList();
            getScene().addObject(new GraphicsTriangulation(pointList));
        }
        java.awt.Point p = getScene().toSceneCoords(e.getPoint());
        Point newPoint = new Point(p.x, p.y);
        getScene().addObject(new GraphicsPoint(newPoint));
        pointList.points().add(newPoint);
        getScene().repaint();
    }
}
