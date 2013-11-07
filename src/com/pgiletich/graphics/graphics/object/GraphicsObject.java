package com.pgiletich.graphics.graphics.object;

import com.pgiletich.graphics.graphics.GraphicsScene;
import com.pgiletich.graphics.model.GeometricShape;

public abstract class GraphicsObject {
    private final GeometricShape shape;

    protected GraphicsObject(GeometricShape shape) {
        this.shape = shape;
    }

    public abstract void paint(GraphicsScene scene);

    public GeometricShape getShape() {
        return shape;
    }
}
