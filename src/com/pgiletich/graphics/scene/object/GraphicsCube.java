package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.Cube;
import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.scene.object.line.BresenhamLine;
import com.pgiletich.graphics.util.PointUtil;

public class GraphicsCube extends GraphicsObject {

    public GraphicsCube(Cube shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        int x = 300;
        int y = 300;
        int z = 3;

        Point[] ps = new Point[8];
        for(int i = 0; i < getShape().points.length; i++){
            ps[i] = PointUtil.projection(getShape().points[i], getShape().pos.x, getShape().pos.y, z, getShape().size);
        }

        BresenhamLine.Drawer.draw(scene, new Line(ps[0], ps[1]));
        BresenhamLine.Drawer.draw(scene, new Line(ps[1], ps[2]));
        BresenhamLine.Drawer.draw(scene, new Line(ps[2], ps[3]));
        BresenhamLine.Drawer.draw(scene, new Line(ps[3], ps[0]));

        BresenhamLine.Drawer.draw(scene, new Line(ps[4], ps[5]));
        BresenhamLine.Drawer.draw(scene, new Line(ps[5], ps[6]));
        BresenhamLine.Drawer.draw(scene, new Line(ps[6], ps[7]));
        BresenhamLine.Drawer.draw(scene, new Line(ps[7], ps[4]));

        BresenhamLine.Drawer.draw(scene, new Line(ps[0], ps[4]));
        BresenhamLine.Drawer.draw(scene, new Line(ps[1], ps[5]));
        BresenhamLine.Drawer.draw(scene, new Line(ps[2], ps[6]));
        BresenhamLine.Drawer.draw(scene, new Line(ps[3], ps[7]));
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }

    @Override
    public Cube getShape() {
        return (Cube) super.getShape();
    }
}
