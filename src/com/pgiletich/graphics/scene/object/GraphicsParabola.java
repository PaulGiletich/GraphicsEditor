package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.GeometricShape;
import com.pgiletich.graphics.model.Parabola;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.GraphicsScene;

public class GraphicsParabola extends GraphicsObject {
    public GraphicsParabola(GeometricShape shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        Point pos = getShape().getPos();
        int x = 0;
        int y = 0;
        int p = getShape().getP();

        int Sd;
        int Sv;
        int Sh;

        while (x + pos.x() < 1000) {
            scene.fillPixel(pos.x() + x, pos.y() + y);
            scene.fillPixel(pos.x() + x, pos.y() + -y);

            Sd = ((y + 1) * (y + 1)) - 2 * p * (x + 1);
            Sv = ((y + 1) * (y + 1)) - 2 * p * x;
            Sh = (y * y) - 2 * p * (x + 1);

            if (Math.abs(Sh) <= Math.abs(Sv)) {
                if (Math.abs(Sd) < Math.abs(Sh)) {
                    y++;
                }
                x++;
            }
            else {
                if (Math.abs(Sv) > Math.abs(Sd)) {
                    x++;
                }
                y++;
            }
        }
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }

    @Override
    public Parabola getShape() {
        return (Parabola)super.getShape();
    }
}
