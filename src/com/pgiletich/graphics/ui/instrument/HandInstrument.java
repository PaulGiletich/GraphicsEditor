package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class HandInstrument extends InstrumentStrategy {
    private final JScrollPane scrollPane;
    private Point lastMousePos;

    public HandInstrument(MainWindow window) {
        super(window);
        this.scrollPane = window.getScrollPane();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePos = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getPoint().x - lastMousePos.x;
        int dy = e.getPoint().y - lastMousePos.y;
        scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue() - dx);
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() - dy);
    }
}
