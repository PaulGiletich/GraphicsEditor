package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.Cube;
import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.util.PointUtil;

import static com.pgiletich.graphics.scene.object.line.AntialiasedLine.Drawer.draw;

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
            ps[i] = PointUtil.projection(getShape().points[i], getShape().pos.x(), getShape().pos.y(), z, getShape().size);
        }

        draw(scene, new Line(ps[0], ps[1]));
        draw(scene, new Line(ps[1], ps[2]));
        draw(scene, new Line(ps[2], ps[3]));
        draw(scene, new Line(ps[3], ps[0]));

        draw(scene, new Line(ps[4], ps[5]));
        draw(scene, new Line(ps[5], ps[6]));
        draw(scene, new Line(ps[6], ps[7]));
        draw(scene, new Line(ps[7], ps[4]));

        draw(scene, new Line(ps[0], ps[4]));
        draw(scene, new Line(ps[1], ps[5]));
        draw(scene, new Line(ps[2], ps[6]));
        draw(scene, new Line(ps[3], ps[7]));
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
