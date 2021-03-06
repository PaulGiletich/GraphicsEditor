package com.pgiletich.graphics.scene.object.line;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.object.GraphicsObject;

public abstract class AbstractLine extends GraphicsObject {
    public AbstractLine(Line line) {
        super(line);
    }

    @Override
    public Line getShape() {
        return (Line) super.getShape();
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }
}
