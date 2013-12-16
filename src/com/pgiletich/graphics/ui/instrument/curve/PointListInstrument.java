package com.pgiletich.graphics.ui.instrument.curve;

import com.pgiletich.graphics.model.PointList;
import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.instrument.InstrumentStrategy;

public abstract class PointListInstrument extends InstrumentStrategy{
    private PointList pointList;

    public PointListInstrument(MainWindow window) {
        super(window);
    }

    public PointList getPointList() {
        return pointList;
    }

    public void setPointList(PointList pointList) {
        this.pointList = pointList;
    }
}
