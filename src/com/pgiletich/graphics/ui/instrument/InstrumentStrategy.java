package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.scene.GraphicsScene;

import java.awt.event.MouseAdapter;

public abstract class InstrumentStrategy extends MouseAdapter {
    private final GraphicsScene scene;

    public InstrumentStrategy(GraphicsScene scene){
        this.scene = scene;
    }

    public GraphicsScene getScene() {
        return scene;
    }
}
