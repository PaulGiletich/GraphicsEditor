package com.pgiletich.graphics.ui.panels;

import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.instrument.curve.BSplineInstrument;
import com.pgiletich.graphics.ui.instrument.curve.BezierCurveInstrument;
import com.pgiletich.graphics.ui.instrument.curve.ErmitCurveInstrument;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CurvesPanel extends JToolBar {
    public CurvesPanel(final MainWindow window) {
        setFloatable(false);
        setOrientation(SwingConstants.VERTICAL);
        add(new JLabel("Curves"));

        add(new JButton(new AbstractAction("Bezier") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new BezierCurveInstrument(window));
            }
        }));
        add(new JButton(new AbstractAction("B-spline") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new BSplineInstrument(window));
            }
        }));
        add(new JButton(new AbstractAction("Ermit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new ErmitCurveInstrument(window));
            }
        }));

        addSeparator();
    }

}
