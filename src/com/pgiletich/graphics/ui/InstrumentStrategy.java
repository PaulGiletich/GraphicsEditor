package com.pgiletich.graphics.ui;

import com.pgiletich.graphics.graphics.GraphicsScene;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class InstrumentStrategy implements MouseListener, MouseMotionListener {
    private final GraphicsScene scene;

    public InstrumentStrategy(GraphicsScene scene){
        this.scene = scene;
    }

    public GraphicsScene getScene() {
        return scene;
    }
}
