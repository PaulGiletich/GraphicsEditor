package com.pgiletich.graphics.ui.instrument.curve;

import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.PointList;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.scene.object.curve.Bezier;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.event.MouseEvent;

public class BezierCurveInstrument extends PointListInstrument {

    public BezierCurveInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(getPointList() == null){
            setPointList(new PointList());
        }
        java.awt.Point p = getScene().toSceneCoords(e.getPoint());
        Point newPoint = new Point(p.x, p.y);
        getScene().addObject(new GraphicsPoint(newPoint));
        getScene().repaint();
        getPointList().points().add(newPoint);

        if(getPointList().points().size() == 4){
            Bezier bezier = new Bezier(getPointList());
            getScene().addObject(bezier);
            getScene().repaint();
            setPointList(null);
        }
    }
}
