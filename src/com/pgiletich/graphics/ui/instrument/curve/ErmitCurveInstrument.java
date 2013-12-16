package com.pgiletich.graphics.ui.instrument.curve;

import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.PointList;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.scene.object.curve.Ermit;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.event.MouseEvent;

public class ErmitCurveInstrument extends PointListInstrument {

    public ErmitCurveInstrument(MainWindow window) {
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
            Ermit ermit = new Ermit(getPointList());
            getScene().addObject(ermit);
            getScene().repaint();
            setPointList(null);
        }
    }
}
