package com.pgiletich.graphics.ui.instrument.curve;

import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.PointList;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.scene.object.curve.BSpline;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.event.MouseEvent;

public class BSplineInstrument extends PointListInstrument {
    public BSplineInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(getPointList() == null){
            PointList curve = new PointList();
            setPointList(curve);
            getScene().addObject(new BSpline(curve));
        }
        java.awt.Point p = getScene().toSceneCoords(e.getPoint());
        Point newPoint = new Point(p.x, p.y);
        getScene().addObject(new GraphicsPoint(newPoint));
        getPointList().points().add(newPoint);
        getScene().repaint();
    }
}
