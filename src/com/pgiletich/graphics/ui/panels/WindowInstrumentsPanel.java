package com.pgiletich.graphics.ui.panels;

import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.instrument.HandInstrument;
import com.pgiletich.graphics.ui.instrument.MoveInstrument;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WindowInstrumentsPanel extends JToolBar {
    public WindowInstrumentsPanel(final MainWindow window){
        setFloatable(false);
        setOrientation(SwingConstants.VERTICAL);
        add(new JLabel("Instruments"));

        add(new JButton(new AbstractAction("Hand") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new HandInstrument(window));
            }
        }));
        add(new JButton(new AbstractAction("Move") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new MoveInstrument(window));
            }
        }));
        add(new JButton(new AbstractAction("Clear") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getScene().clear().repaint();
        }
        }));

        addSeparator();
    }
}
