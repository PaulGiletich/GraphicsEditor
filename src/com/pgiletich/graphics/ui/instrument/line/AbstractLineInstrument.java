package com.pgiletich.graphics.ui.instrument.line;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.instrument.InstrumentStrategy;

public class AbstractLineInstrument extends InstrumentStrategy {
    private Line line;

    public AbstractLineInstrument(MainWindow window) {
        super(window);
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
