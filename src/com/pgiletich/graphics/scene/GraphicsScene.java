package com.pgiletich.graphics.scene;

import com.pgiletich.graphics.debugger.GraphicEquationSolver;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.object.GraphicsObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GraphicsScene extends JPanel implements Iterable<GraphicsObject> {
    private int scale = 5;
    private List<GraphicsObject> objects = new ArrayList<>();
    private Collection<GraphicsObject> objectsToRemove = new ArrayList<>();
    private Graphics tmpGraphics;
    private Dimension canvasSize;
    private List<RepaintListener> listeners = new ArrayList<>();


    public GraphicsScene(Dimension canvasSize) {
        this.canvasSize = canvasSize;
    }

    @Override
    public void paint(Graphics g) {
        GraphicEquationSolver.clear();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        paintGrid(g);
        paintObjects(g);

        for(RepaintListener listener: listeners){
            listener.sceneRepainted();
        }
    }

    private void paintObjects(Graphics g) {
        removeObjects();
        tmpGraphics = g;
        for(GraphicsObject object: objects){
            GraphicEquationSolver.newObject();

            g.setColor(Color.BLACK);
            object.paint(this);
        }
        tmpGraphics = null;
    }

    private void removeObjects() {
        for(GraphicsObject object: objectsToRemove){
            objects.remove(object);
        }
        objectsToRemove.clear();
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
        if(GraphicEquationSolver.isEnabled){
            boolean isAnswer = GraphicEquationSolver.addPoint(new Point(x, y));
            if(isAnswer){
                tmpGraphics.setColor(Color.CYAN);
                tmpGraphics.fillRect(x * scale, y * scale, scale, scale);
                tmpGraphics.setColor(Color.BLACK);
                return;
            }
        }
        tmpGraphics.fillRect(x * scale, y * scale, scale, scale);
    }

    public void fillPixel(double x, double y) {
        fillPixel((int) x, (int) y);
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

    public java.awt.Point toSceneCoords(java.awt.Point p){
        p.setLocation(p.x / scale, p.y / scale);
        return p;
    }

    public GraphicsObject getObjectByPoint(java.awt.Point p) {
        com.pgiletich.graphics.model.Point point = new com.pgiletich.graphics.model.Point(p.x, p.y);
        for (GraphicsObject object: objects){
            if(object.contains(point)){
                return object;
            }
        }
        return null;
    }

    public GraphicsScene clear() {
        objects.clear();
        return this;
    }

    @Override
    public Iterator<GraphicsObject> iterator() {
        return objects.iterator();
    }

    public void removeObject(GraphicsObject l) {
        objectsToRemove.add(l);
    }

    public void addRepaintListener(RepaintListener toAdd) {
        listeners.add(toAdd);
    }
}
