package com.pgiletich.graphics.ui;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.scene.object.ClipRect;
import com.pgiletich.graphics.scene.object.GraphicsObject;
import com.pgiletich.graphics.ui.instrument.*;
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

        toolBar.add(new JButton(new AbstractAction("Circle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInstrument(new CircleInstrument(MainWindow.this));
            }
        }));
        toolBar.add(new JButton(new AbstractAction("Parabola") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInstrument(new ParabolaInstrument(MainWindow.this));
            }
        }));
        toolBar.addSeparator();

        toolBar.add(new CurvesPanel(this));

        toolBar.add(new JButton(new AbstractAction("Cube") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInstrument(new CubeInstrument(MainWindow.this));
            }
        }));
        toolBar.addSeparator();

        toolBar.add(new JButton(new AbstractAction("Triangulation") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInstrument(new TriangulationInstrument(MainWindow.this));
            }
        }));
        toolBar.addSeparator();

        toolBar.add(new JButton(new AbstractAction("ClipRect") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInstrument(new ClipRectInstrument(MainWindow.this));
            }
        }));

        JPanel clipPanel = new JPanel(new FlowLayout());
        toolBar.add(clipPanel);
        clipPanel.add(new JButton(new AbstractAction("clip") {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(GraphicsObject object: scene){
                    if(object instanceof ClipRect){
                        for(GraphicsObject l: scene){
                            if(l.getShape() instanceof Line){
                                ((ClipRect) object).clip((Line) l.getShape());
                            }
                        }
                    }
                }
                scene.repaint();
            }
        }));
        toolBar.addSeparator();

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
