package com.pgiletich.graphics.scene;

import com.pgiletich.graphics.scene.object.GraphicsObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicsScene extends JPanel {
    private int scale = 1;
    private List<GraphicsObject> objects = new ArrayList<>();
    private Graphics tmpGraphics;
    private Dimension canvasSize;

    public GraphicsScene(Dimension canvasSize) {
        this.canvasSize = canvasSize;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        paintGrid(g);
        paintObjects(g);
    }

    private void paintObjects(Graphics g) {
        tmpGraphics = g;
        for(GraphicsObject object: objects){
            g.setColor(Color.BLACK);
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
        int gray = 255 - (scale * 5);
        g.setColor(new Color(gray, gray, gray));
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
        setFillAlpha(alpha);
        tmpGraphics.fillRect(x * scale, y * scale, scale, scale);
    }

    public void setFillAlpha(float alpha){
        if(alpha > 1) {
            alpha = 1;
        }
        tmpGraphics.setColor(new Color(0, 0, 0, (int) (alpha * 255)));
    }

    public void setColor(Color color){
        tmpGraphics.setColor(color);
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
        revalidate();
    }

    public GraphicsScene addObject(GraphicsObject object) {
        objects.add(object);
        return this;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvasSize.width * scale, canvasSize.height * scale);
    }

    public Point toSceneCoords(Point p){
        p.setLocation(p.x / scale, p.y / scale);
        return p;
    }

    public GraphicsObject getObjectByPoint(Point p) {
        com.pgiletich.graphics.model.Point point = new com.pgiletich.graphics.model.Point(p.x, p.y);
        for (GraphicsObject object: objects){
            if(object.contains(point)){
                return object;
            }
        }
        return null;
    }
}
