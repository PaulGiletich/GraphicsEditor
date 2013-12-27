package com.pgiletich.graphics.ui.panels;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.MainWindowStyles;
import com.pgiletich.graphics.ui.instrument.line.AntialiasedLineInstrument;
import com.pgiletich.graphics.ui.instrument.line.BresenhamLineInstrument;
import com.pgiletich.graphics.ui.instrument.line.CDALineInstrument;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LinesPanel extends JToolBar {
    public LinesPanel(final MainWindow window) {
        setFloatable(false);
        setOrientation(SwingConstants.VERTICAL);
        JLabel label = new JLabel("Lines");
        label.setFont(MainWindowStyles.instrumentsPanelHeadingFont);
        add(label);

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

        JPanel debugPanel = new JPanel();
        debugPanel.setLayout(new BoxLayout(debugPanel, BoxLayout.Y_AXIS));
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
        stepButton.setEnabled(false);
        debugPanel.add(debugEnabled);
        debugPanel.add(stepButton);
        this.add(debugPanel);
    }

}
