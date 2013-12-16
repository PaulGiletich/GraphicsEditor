package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.GraphicsScene;

import java.awt.*;

public class GraphicsPoint extends GraphicsObject {

    public GraphicsPoint(Point point) {
        super(point);
    }

    @Override
    public void paint(GraphicsScene scene) {
        if(!Debugger.isPointsEnabled()){
            return;
        }
        Point p = getShape();
        scene.setColor(Color.BLUE);
        scene.fillPixel((int) p.getX(), (int) p.getY());
    }

    @Override
    public boolean contains(Point p) {
        Point thisPoint = getShape();

        return thisPoint.equals(p);
    }

    @Override
    public Point getShape() {
        return (Point)super.getShape();
    }


}
