package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.Line;

public abstract class AbstractLine extends GraphicsObject {
    public AbstractLine(Line line) {
        super(line);
    }

    @Override
    public Line getShape() {
        return (Line) super.getShape();
    }
}
