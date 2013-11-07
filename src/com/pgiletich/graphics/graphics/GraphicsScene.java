package com.pgiletich.graphics.graphics;

import com.pgiletich.graphics.graphics.object.GraphicsObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicsScene extends JPanel {
    private int scale = 1;
    private List<GraphicsObject> objects = new ArrayList<>();
    private Graphics tmpGraphics;
    private Dimension canvasSize = new Dimension(500, 400);

    public GraphicsScene() {
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        paintGrid(g);
        paintObjects(g);
    }

    private void paintObjects(Graphics g) {
        g.setColor(Color.BLACK);
        tmpGraphics = g;
        for(GraphicsObject object: objects){
            object.paint(this);
        }
        tmpGraphics = null;
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void paintGrid(Graphics g){
        if(scale == 1){
            return;
        }
        g.setColor(Color.lightGray);
        for(int i=0; i < Math.min(canvasSize.width*scale, getWidth()); i+=scale){
            g.drawLine(i, 0, i, getHeight());
        }
        for(int j=0; j < Math.min(canvasSize.height*scale, getHeight()); j+=scale){
            g.drawLine(0, j, getWidth(), j);
        }
    }

    public void fillPixel(int x, int y){
        tmpGraphics.fillRect(x * scale, y * scale, scale, scale);
    }

    public void fillPixel(int x, int y, float alpha){
        if(alpha > 1) {
            alpha = 1;
        }
        tmpGraphics.setColor(new Color(0,0,0, (int)(alpha*255)));
        tmpGraphics.fillRect(x * scale, y * scale, scale, scale);
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
        revalidate();
    }

    public void addObject(GraphicsObject object) {
        objects.add(object);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvasSize.width * scale, canvasSize.height * scale);
    }
}
