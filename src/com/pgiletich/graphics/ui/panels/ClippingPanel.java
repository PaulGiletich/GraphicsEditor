package com.pgiletich.graphics.ui.panels;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.scene.object.Clipper;
import com.pgiletich.graphics.scene.object.GraphicsObject;
import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.ui.MainWindowStyles;
import com.pgiletich.graphics.ui.instrument.ClipRectInstrument;
import com.pgiletich.graphics.ui.instrument.curve.PolygonInstrument;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClippingPanel extends JToolBar {
    public ClippingPanel(final MainWindow window) {
        setFloatable(false);
        setOrientation(SwingConstants.VERTICAL);
        JLabel label = new JLabel("Clipping");
        label.setFont(MainWindowStyles.instrumentsPanelHeadingFont);
        add(label);

        add(new JButton(new AbstractAction("Rect") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new ClipRectInstrument(window));
            }
        }));

        add(new JButton(new AbstractAction("Poly") {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setInstrument(new PolygonInstrument(window));
            }
        }));

        JPanel clipPanel = new JPanel();
        clipPanel.setLayout(new BoxLayout(clipPanel, BoxLayout.Y_AXIS));
        clipPanel.add(new JButton(new AbstractAction("clip") {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(GraphicsObject object: window.getScene()){
                    if(object instanceof Clipper){
                        for(GraphicsObject l: window.getScene()){
                            if(l.getShape() instanceof Line){
                                if(!((Clipper) object).clip((Line) l.getShape())){
                                    window.getScene().removeObject(l);
                                }
                            }
                        }
                    }
                }
                window.getScene().repaint();
            }
        }));
        add(clipPanel);
    }

}
