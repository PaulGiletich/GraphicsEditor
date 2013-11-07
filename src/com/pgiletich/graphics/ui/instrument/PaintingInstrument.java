package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.scene.GraphicsScene;

public abstract class PaintingInstrument extends InstrumentStrategy {
    final GraphicsScene scene;

    public PaintingInstrument(GraphicsScene scene) {
        this.scene = scene;
    }

    public GraphicsScene getScene() {
        return scene;
    }
}