package com.pgiletich.graphics.ui.panels.helper;

import com.pgiletich.graphics.model.Point;

import javax.swing.*;
import java.awt.*;

public class PointCellRenderer extends JLabel implements ListCellRenderer<Point> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Point> list, Point value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.x() + "," + value.y());
        return this;
    }
}
