package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.GeometricShape;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.GraphicsScene;

public abstract class GraphicsObject {
    private final GeometricShape shape;

    protected GraphicsObject(GeometricShape shape) {
        this.shape = shape;
    }

    public abstract void paint(GraphicsScene scene);

    public abstract boolean contains(Point p);

    public GeometricShape getShape() {
        return shape;
    }
}
