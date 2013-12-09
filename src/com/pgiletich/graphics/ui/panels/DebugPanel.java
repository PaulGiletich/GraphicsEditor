package com.pgiletich.graphics.ui.panels;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.ui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DebugPanel extends JPanel {

    public DebugPanel(final MainWindow window) {
        final JButton stepButton = new JButton(new AbstractAction("Step") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debugger.addStep();
                window.getScene().repaint();
            }
        });
        JCheckBox debugEnabled = new JCheckBox("debug");
        debugEnabled.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Debugger.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                window.getScene().repaint();
                stepButton.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        JCheckBox pointsEnabled = new JCheckBox("show points");
        pointsEnabled.setSelected(true);
        pointsEnabled.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Debugger.setPointsEnabled(e.getStateChange() == ItemEvent.SELECTED);
                window.getScene().repaint();
            }
        });
        stepButton.setEnabled(false);
        this.add(debugEnabled);
        this.add(stepButton);
        this.add(pointsEnabled);
    }
}
