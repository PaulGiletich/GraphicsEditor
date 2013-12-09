package com.pgiletich.graphics.ui.instrument.curve;

import com.pgiletich.graphics.model.Curve;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.object.GraphicsPoint;
import com.pgiletich.graphics.scene.object.curve.Ermit;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.event.MouseEvent;

public class ErmitCurveInstrument extends AbstractCurveInstrument {

    public ErmitCurveInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(getCurve() == null){
            setCurve(new Curve());
        }
        java.awt.Point p = getScene().toSceneCoords(e.getPoint());
        Point newPoint = new Point(p.x, p.y);
        getScene().addObject(new GraphicsPoint(newPoint));
        getScene().repaint();
        getCurve().points.add(newPoint);

        if(getCurve().points.size() == 4){
            Ermit ermit = new Ermit(getCurve());
            getScene().addObject(ermit);
            getScene().repaint();
            setCurve(null);
        }
    }
}
