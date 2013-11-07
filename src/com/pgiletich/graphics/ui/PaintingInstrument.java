package com.pgiletich.graphics.ui;

import com.pgiletich.graphics.graphics.GraphicsScene;

public abstract class PaintingInstrument extends InstrumentStrategy {
    final GraphicsScene scene;

    public PaintingInstrument(GraphicsScene scene) {
        this.scene = scene;
    }

    public GraphicsScene getScene() {
        return scene;
    }
}