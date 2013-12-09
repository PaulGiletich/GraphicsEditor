package com.pgiletich.graphics.ui.instrument.curve;

import com.pgiletich.graphics.model.Curve;
import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.instrument.InstrumentStrategy;

public abstract class AbstractCurveInstrument extends InstrumentStrategy{
    private Curve curve;

    public AbstractCurveInstrument(MainWindow window) {
        super(window);
    }

    public Curve getCurve() {
        return curve;
    }

    public void setCurve(Curve curve) {
        this.curve = curve;
    }
}
