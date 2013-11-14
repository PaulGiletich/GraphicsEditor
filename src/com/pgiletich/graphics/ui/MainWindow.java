package com.pgiletich.graphics.ui;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.ui.instrument.InstrumentRepository;
import com.pgiletich.graphics.ui.instrument.InstrumentStrategy;

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
    private InstrumentRepository instrumentRepository = new InstrumentRepository(this);
    private Map<String, Action> actions = initActionsMap();

    public MainWindow(){
        setSize(800, 600);

        this.setJMenuBar(initMenuBar());
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(initDebugPanel(), BorderLayout.SOUTH);
        this.add(initToolbar(), BorderLayout.WEST);

        selectedInstrument = instrumentRepository.get("Hand");
        initActionListeners();
    }

    private JToolBar initToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setOrientation(SwingConstants.VERTICAL);
        for(String instrumentName: instrumentRepository.keySet()){
            toolBar.add(new JButton(actions.get(instrumentName)));
        }
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
        menuBar.add(initHelpMenu());
        return menuBar;
    }

    private JMenu initHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem(actions.get("About"));
        helpMenu.add(aboutItem);
        return helpMenu;
    }

    private void initActionListeners() {
        InstrumentListener listener = new InstrumentListener();
        scene.addMouseListener(listener);
        scene.addMouseMotionListener(listener);
        scene.addMouseWheelListener(new ResizeListener());
    }

    private Map<String, Action> initActionsMap() {
        Map<String, Action> result = new HashMap<>();
        for(String instrumentName: instrumentRepository.keySet()) {
            result.put(instrumentName, new InstrumentChangeAction(instrumentName));
        }
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

    public GraphicsScene getScene() {
        return scene;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    class InstrumentChangeAction extends AbstractAction{
        private final String instrumentName;

        public InstrumentChangeAction(String instrumentName){
            super(instrumentName);
            this.instrumentName = instrumentName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedInstrument = instrumentRepository.get(instrumentName);
        }
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
