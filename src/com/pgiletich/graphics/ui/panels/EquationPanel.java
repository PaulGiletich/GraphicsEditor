package com.pgiletich.graphics.ui.panels;

import com.pgiletich.graphics.debugger.GraphicEquationSolver;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.RepaintListener;
import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.MainWindowStyles;
import com.pgiletich.graphics.ui.panels.helper.PointCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EquationPanel extends JToolBar implements RepaintListener {
    private JList<Point> solvesList = new JList<Point>(){{
        setCellRenderer(new PointCellRenderer());
    }};

    public EquationPanel(final MainWindow window){
        setFloatable(false);
        setOrientation(SwingConstants.VERTICAL);
        JLabel label = new JLabel("Equations");
        label.setFont(MainWindowStyles.instrumentsPanelHeadingFont);
        add(label);

        JCheckBox checkBox = new JCheckBox("show solves");
        checkBox.setSelected(false);
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                GraphicEquationSolver.isEnabled = e.getStateChange() == ItemEvent.SELECTED;
                window.getScene().repaint();
            }
        });
        add(checkBox);

        JScrollPane solvesListScrollPane = new JScrollPane(solvesList);
        solvesListScrollPane.setPreferredSize(new Dimension(80, 40));

        add(solvesListScrollPane);

        window.getScene().addRepaintListener(this);
    }

    @Override
    public void sceneRepainted() {
        solvesList.setListData(GraphicEquationSolver.getSolves());
    }
}
