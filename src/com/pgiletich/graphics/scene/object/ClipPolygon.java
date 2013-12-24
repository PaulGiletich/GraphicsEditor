package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.Polygon;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.scene.object.line.AntialiasedLine;
import com.pgiletich.graphics.util.MathUtil;

public class ClipPolygon extends GraphicsObject implements Clipper {

    public ClipPolygon(Polygon shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        for (Line edge: getShape().edges()){
            AntialiasedLine.Drawer.draw(scene, edge);
        }
    }

    @Override
    public boolean clip(Line line){
        Polygon poly = getShape();
        if(!poly.isConvex()){
            throw new RuntimeException("Need convex polygon for clipping");
        }
        Point subjDir = line.direction();
        double tStart = 0;
        double tEnd = 1.0;
        for (Line edge: poly.edgesClockwise())
        {
            double t = line.IntersectionParameter(edge);
            switch (MathUtil.sign(edge.normal().dot(subjDir)))
            {
                case -1:
                    if (t > tStart){
                        tStart = t;
                    }
                    break;
                case +1:
                    if (t < tEnd){
                        tEnd = t;
                    }
                    break;
                case 0:
                    if (!edge.isLeft(line.start())){
                        return false;
                    }
                    break;
            }
        }
        if (tStart > tEnd){
            return false;
        }
        Line newLine = line.morph(tStart, tEnd);
        line.setStart(newLine.start());
        line.setEnd(newLine.end());
        return true;
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }

    @Override
    public Polygon getShape() {
        return (Polygon) super.getShape();
    }
}
