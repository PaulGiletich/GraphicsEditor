package com.pgiletich.graphics.scene.object.curve;


import com.pgiletich.graphics.model.Curve;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.object.GraphicsObject;

public abstract class AbstractCurve extends GraphicsObject {
    protected AbstractCurve(Curve shape) {
        super(shape);
    }

    @Override
    public Curve getShape() {
        return (Curve)super.getShape();
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }
}
