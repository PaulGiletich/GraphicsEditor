package com.pgiletich.graphics.ui.instrument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class HandInstrument extends InstrumentStrategy {
    private JScrollPane scrollPane;
    private Point lastMousePos;

    public HandInstrument(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
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
