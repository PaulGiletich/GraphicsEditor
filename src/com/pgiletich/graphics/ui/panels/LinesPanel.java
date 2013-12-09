package com.pgiletich.graphics.ui.panels;

import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.instrument.line.AntialiasedLineInstrument;
import com.pgiletich.graphics.ui.instrument.line.BresenhamLineInstrument;
import com.pgiletich.graphics.ui.instrument.line.CDALineInstrument;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LinesPanel extends JToolBar {
    public LinesPanel(final MainWindow window) {
        setFloatable(false);
        setOrientation(SwingConstants.VERTICAL);
        add(new JLabel("Lines"));

        add(new JButton(new AbstractAction("CDA") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new CDALineInstrument(window));
            }
        }));
        add(new JButton(new AbstractAction("Antialiased") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new AntialiasedLineInstrument(window));
            }
        }));
        add(new JButton(new AbstractAction("Bresenham") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new BresenhamLineInstrument(window));
            }
        }));

        addSeparator();
    }

}
