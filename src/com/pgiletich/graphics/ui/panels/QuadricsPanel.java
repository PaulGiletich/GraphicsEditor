package com.pgiletich.graphics.ui.panels;

import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.MainWindowStyles;
import com.pgiletich.graphics.ui.instrument.CircleInstrument;
import com.pgiletich.graphics.ui.instrument.ParabolaInstrument;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class QuadricsPanel extends JToolBar {
    public QuadricsPanel(final MainWindow window) {
        setFloatable(false);
        setOrientation(SwingConstants.VERTICAL);
        JLabel label = new JLabel("Quadrics");
        label.setFont(MainWindowStyles.instrumentsPanelHeadingFont);
        add(label);

        add(new JButton(new AbstractAction("Circle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new CircleInstrument(window));
            }
        }));
        add(new JButton(new AbstractAction("Parabola") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new ParabolaInstrument(window));
            }
        }));
    }

}
