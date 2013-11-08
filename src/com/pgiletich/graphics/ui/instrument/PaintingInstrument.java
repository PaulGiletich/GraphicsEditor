package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.scene.GraphicsScene;

import java.awt.*;


public abstract class PaintingInstrument extends InstrumentStrategy {
    public PaintingInstrument(GraphicsScene scene) {
        super(scene);
        scene.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
