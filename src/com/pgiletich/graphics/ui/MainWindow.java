package com.pgiletich.graphics.ui;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.ui.instrument.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame {
    private InstrumentStrategy selectedInstrument;
    private GraphicsScene scene = new GraphicsScene(new Dimension(700, 500));
    private JScrollPane scrollPane = new JScrollPane(scene){{
        setWheelScrollingEnabled(false);
    }};
    private Map<String, Action> actions = initActionsMap();

    public MainWindow(){
        setSize(800, 600);
        this.setJMenuBar(initMenuBar());
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(initDebugPanel(), BorderLayout.SOUTH);
        this.add(initToolbar(), BorderLayout.WEST);

        selectedInstrument = new HandInstrument(scene, scrollPane);
        initActionListeners();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JToolBar initToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setOrientation(SwingConstants.VERTICAL);
        toolBar.add(new JButton(actions.get("Hand")));
        toolBar.addSeparator();
        toolBar.add(new JButton(actions.get("CDA")));
        toolBar.add(new JButton(actions.get("Bresenham")));
        toolBar.add(new JButton(actions.get("Antialiased")));
        return toolBar;
    }

    private JPanel initDebugPanel(){
        JPanel debugPanel = new JPanel();
        final JButton stepButton = new JButton(actions.get("Step"));
        JCheckBox debugEnabled = new JCheckBox("debug");
        debugEnabled.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Debugger.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                scene.repaint();
                stepButton.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        stepButton.setEnabled(false);
        debugPanel.add(debugEnabled);
        debugPanel.add(stepButton);
        return debugPanel;
    }

    private JMenuBar initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(initLineMenu());
        menuBar.add(initToolsMenu());
        menuBar.add(initHelpMenu());
        return menuBar;
    }

    private JMenu initHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem(actions.get("About"));
        helpMenu.add(aboutItem);
        return helpMenu;
    }

    private JMenu initToolsMenu() {
        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.add(new JMenuItem(actions.get("Hand")));
        return toolsMenu;
    }

    private JMenu initLineMenu(){
        JMenu lineMenu = new JMenu("Line");
        lineMenu.add(new JMenuItem(actions.get("CDA")));
        lineMenu.add(new JMenuItem(actions.get("Bresenham")));
        lineMenu.add(new JMenuItem(actions.get("Antialiased")));
        return lineMenu;
    }

    private void initActionListeners() {
        InstrumentListener listener = new InstrumentListener();
        scene.addMouseListener(listener);
        scene.addMouseMotionListener(listener);
        scene.addMouseWheelListener(new ResizeListener());
    }

    private Map<String, Action> initActionsMap() {
        Map<String, Action> result = new HashMap<>();
        result.put("Hand", new AbstractAction("Hand") {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedInstrument = new HandInstrument(scene, scrollPane);
            }
        });
        result.put("CDA", new AbstractAction("CDA") {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedInstrument = new CDALineInstrument(scene);
            }
        });
        result.put("Bresenham", new AbstractAction("Bresenham") {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedInstrument = new BresenhamLineInstrument(scene);
            }
        });
        result.put("Antialiased", new AbstractAction("Antialiased") {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedInstrument = new AntialiasedLineInstrument(scene);
            }
        });
        result.put("Step", new AbstractAction("Step") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debugger.addStep();
                scene.repaint();
            }
        });
        result.put("About", new AbstractAction("About") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame aboutWindow = new AboutWindow();
                aboutWindow.setVisible(true);
            }
        });
        return result;
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
