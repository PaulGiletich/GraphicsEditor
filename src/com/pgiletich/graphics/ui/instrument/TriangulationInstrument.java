package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.PointSet;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.scene.object.GraphicsTriangulation;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.event.MouseEvent;

public class TriangulationInstrument extends InstrumentStrategy {
    private PointSet pointSet;

    public TriangulationInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(pointSet == null){
            pointSet = new PointSet();
            getScene().addObject(new GraphicsTriangulation(pointSet));
        }
        java.awt.Point p = getScene().toSceneCoords(e.getPoint());
        Point newPoint = new Point(p.x, p.y);
        getScene().addObject(new GraphicsPoint(newPoint));
        pointSet.points().add(newPoint);
        getScene().repaint();
    }
}
