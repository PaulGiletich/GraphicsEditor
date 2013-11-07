package com.pgiletich.graphics.ui;

import com.pgiletich.graphics.graphics.GraphicsScene;

import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private InstrumentStrategy selectedInstrument;
    private GraphicsScene scene = new GraphicsScene();
    private JScrollPane scrollPane = new JScrollPane(scene);

    public MainWindow(){
        setSize(500, 400);
        initMenu();
        initActionListeners();

        this.add(scrollPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(initLineMenu());
        this.setJMenuBar(menuBar);
        selectedInstrument = new AntialiasedLineInstrument(scene);
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

    class InstrumentListener implements MouseListener, MouseMotionListener {

        private MouseEvent convertToSceneCoords(MouseEvent e){
            return new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiersEx(),
                    e.getX() / scene.getScale(), e.getY() / scene.getScale(),
                    e.getClickCount(), e.isPopupTrigger());
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //do nothing
        }

        @Override
        public void mousePressed(MouseEvent e) {
            selectedInstrument.mousePressed(convertToSceneCoords(e));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            selectedInstrument.mouseReleased(convertToSceneCoords(e));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //do nothing
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //do nothing
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            selectedInstrument.mouseDragged(convertToSceneCoords(e));
        }

        @Override
        public void mouseMoved(MouseEvent e) {

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
