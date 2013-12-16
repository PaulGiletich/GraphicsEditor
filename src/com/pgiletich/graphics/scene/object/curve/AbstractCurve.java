package com.pgiletich.graphics.scene.object.curve;


import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.PointList;
import com.pgiletich.graphics.scene.object.GraphicsObject;

public abstract class AbstractCurve extends GraphicsObject {
    protected AbstractCurve(PointList shape) {
        super(shape);
    }

    @Override
    public PointList getShape() {
        return (PointList)super.getShape();
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }
}
