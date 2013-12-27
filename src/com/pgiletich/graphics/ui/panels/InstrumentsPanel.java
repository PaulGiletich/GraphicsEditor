package com.pgiletich.graphics.ui.panels;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.MainWindowStyles;
import com.pgiletich.graphics.ui.instrument.HandInstrument;
import com.pgiletich.graphics.ui.instrument.MoveInstrument;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class InstrumentsPanel extends JToolBar {
    public InstrumentsPanel(final MainWindow window){
        setFloatable(false);
        setOrientation(SwingConstants.VERTICAL);
        JLabel label = new JLabel("Instruments");
        label.setFont(MainWindowStyles.instrumentsPanelHeadingFont);
        add(label);

        add(new JButton(new AbstractAction("Hand") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new HandInstrument(window));
            }
        }));
        add(new JButton(new AbstractAction("Move points") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new MoveInstrument(window));
            }
        }));

        JPanel instrumentToolsPanel = new JPanel();
        instrumentToolsPanel.setLayout(new BoxLayout(instrumentToolsPanel, BoxLayout.Y_AXIS));
        instrumentToolsPanel.add(new JButton(new AbstractAction("clear scene") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getScene().clear().repaint();
        }
        }));

        JCheckBox pointsEnabledCheckBox = new JCheckBox("show points");
        pointsEnabledCheckBox.setSelected(false);
        pointsEnabledCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Debugger.setPointsEnabled(e.getStateChange() == ItemEvent.SELECTED);
                window.getScene().repaint();
            }
        });
        instrumentToolsPanel.add(pointsEnabledCheckBox);
        add(instrumentToolsPanel);
    }
}
