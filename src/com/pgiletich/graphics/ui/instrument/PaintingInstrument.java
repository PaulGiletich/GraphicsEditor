package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.ui.MainWindow;


public abstract class PaintingInstrument extends InstrumentStrategy {
    public PaintingInstrument(MainWindow window) {
        super(window);
    }

    public GraphicsScene getScene() {
        return getWindow().getScene();
    }
}
