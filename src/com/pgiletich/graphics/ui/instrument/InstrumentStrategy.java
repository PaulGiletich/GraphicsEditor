package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.ui.MainWindow;

import java.awt.event.MouseAdapter;

public abstract class InstrumentStrategy extends MouseAdapter {
    private final MainWindow window;

    public InstrumentStrategy(MainWindow window){
        this.window = window;
    }

    public MainWindow getWindow() {
        return window;
    }

    public GraphicsScene getScene(){
        return window.getScene();
    }
}
