package com.pgiletich.graphics.ui;

import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.ui.instrument.HandInstrument;
import com.pgiletich.graphics.ui.instrument.InstrumentStrategy;
import com.pgiletich.graphics.ui.panels.CurvesPanel;
import com.pgiletich.graphics.ui.panels.DebugPanel;
import com.pgiletich.graphics.ui.panels.LinesPanel;
import com.pgiletich.graphics.ui.panels.WindowInstrumentsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private InstrumentStrategy instrument;
    private GraphicsScene scene = new GraphicsScene(new Dimension(700, 500));
    private JScrollPane scrollPane = new JScrollPane(scene){{
        setWheelScrollingEnabled(false);
    }};

    public MainWindow(){
        setSize(800, 600);

        this.setJMenuBar(initMenuBar());
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(new DebugPanel(this), BorderLayout.SOUTH);
        this.add(initToolbar(), BorderLayout.WEST);

        instrument = new HandInstrument(this);
        initActionListeners();
    }

    private JToolBar initToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setOrientation(SwingConstants.VERTICAL);

        toolBar.add(new LinesPanel(this));
        toolBar.add(new CurvesPanel(this));
        toolBar.add(new WindowInstrumentsPanel(this));

        return toolBar;
    }

    private JMenuBar initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem(new AbstractAction("About") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame aboutWindow = new AboutWindow();
                aboutWindow.setVisible(true);
            }
        });
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        return menuBar;
    }

    private void initActionListeners() {
        InstrumentListener listener = new InstrumentListener();
        scene.addMouseListener(listener);
        scene.addMouseMotionListener(listener);
        scene.addMouseWheelListener(new ResizeListener());
    }

    public GraphicsScene getScene() {
        return scene;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    class InstrumentListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            instrument.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            instrument.mouseReleased(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            instrument.mouseDragged(e);
        }
    }

    class ResizeListener implements MouseWheelListener {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            scene.setScale(scene.getScale() - e.getWheelRotation());
            if(scene.getScale() < 1){
                scene.setScale(1);
            }
        }
    }

    public InstrumentStrategy getInstrument() {
        return instrument;
    }

    public void setInstrument(InstrumentStrategy instrument) {
        this.instrument = instrument;
    }
}
