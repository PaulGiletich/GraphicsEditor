package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.Cube;
import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.Polygon;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.util.Matrix;
import com.pgiletich.graphics.util.PointUtil;

import static com.pgiletich.graphics.scene.object.line.AntialiasedLine.Drawer.draw;

//TODO refactor thrash with perspective transformations codebase
public class GraphicsCube extends GraphicsObject {

    public GraphicsCube(Cube shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        int z = 3;
        Point pointOfView = new Point(0, 0, -z, 1);

        Polygon[] sides = getShape().getSides();

        Matrix robertsMatrix = PointUtil.getMatrix(pointOfView).multiply(getShape().getPlanesMatrix());

        for (int i = 0; i < robertsMatrix.columnCount(); i++) {
            if (robertsMatrix.get(0, i) > 0){
                drawPolygon(scene, sides[i]);
            }
        }
    }

    private void drawPolygon(GraphicsScene scene, Polygon p) {
        Point[] ps = new Point[4];
        for(int i = 0; i < p.points().size(); i++){
            ps[i] = PointUtil.projection(p.points().get(i), getShape().pos.x(), getShape().pos.y(), 3, getShape().size);
        }
        draw(scene, new Line(ps[0], ps[1]));
        draw(scene, new Line(ps[1], ps[2]));
        draw(scene, new Line(ps[2], ps[3]));
        draw(scene, new Line(ps[3], ps[0]));
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
