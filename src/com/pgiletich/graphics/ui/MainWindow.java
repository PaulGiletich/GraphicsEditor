package com.pgiletich.graphics.ui;

import com.pgiletich.graphics.Debugger;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.ui.instrument.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private InstrumentStrategy selectedInstrument;
    private GraphicsScene scene = new GraphicsScene();
    private JScrollPane scrollPane = new JScrollPane(scene);

    public MainWindow(){
        setSize(800, 600);
        initMenu();
        initActionListeners();

        selectedInstrument = new HandInstrument(scene, scrollPane);
        scrollPane.setWheelScrollingEnabled(false);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);


        this.add(initDebugPanel(), BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JPanel initDebugPanel(){
        JPanel debugPanel = new JPanel();
        JCheckBox debugEnabled = new JCheckBox("debug");
        debugEnabled.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Debugger.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                scene.repaint();
            }
        });
        JButton step = new JButton("step");
        step.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debugger.addStep();
                scene.repaint();
            }
        });
        debugPanel.add(debugEnabled);
        debugPanel.add(step);
        return debugPanel;
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(initLineMenu());
        menuBar.add(initToolsMenu());
        menuBar.add(initHelpMenu());
        this.setJMenuBar(menuBar);
    }

    private JMenu initHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame aboutWindow = new AboutWindow();
                aboutWindow.setVisible(true);
            }
        });
        helpMenu.add(aboutItem);
        return helpMenu;
    }

    private JMenu initToolsMenu() {
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem handItem = new JMenuItem("Hand");
        handItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedInstrument = new HandInstrument(scene, scrollPane);
            }
        });
        toolsMenu.add(handItem);
        return toolsMenu;
    }

    private JMenu initLineMenu(){
        JMenu lineMenu = new JMenu("Line");

        JMenuItem cdaItem = new JMenuItem("CDA");
        cdaItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedInstrument = new CDALineInstrument(scene);
            }
        });
        lineMenu.add(cdaItem);

        JMenuItem bresenhamItem = new JMenuItem("Bresenham");
        bresenhamItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedInstrument = new BresenhamLineInstrument(scene);
            }
        });
        lineMenu.add(bresenhamItem);

        JMenuItem antialiasedItem = new JMenuItem("Antialiased");
        antialiasedItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedInstrument = new AntialiasedLineInstrument(scene);
            }
        });
        lineMenu.add(antialiasedItem);

        return lineMenu;
    }

    private void initActionListeners() {
        InstrumentListener listener = new InstrumentListener();
        scene.addMouseListener(listener);
        scene.addMouseMotionListener(listener);
        scene.addMouseWheelListener(new ResizeListener());
    }

    class InstrumentListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            selectedInstrument.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            selectedInstrument.mouseReleased(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            selectedInstrument.mouseDragged(e);
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
}
