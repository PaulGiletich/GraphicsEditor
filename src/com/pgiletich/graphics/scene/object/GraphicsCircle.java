package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.Circle;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.GraphicsScene;

public class GraphicsCircle extends GraphicsObject {
    public GraphicsCircle(Circle shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        Circle circle = getShape();
        Point pos = circle.getPos();
        Point radius = circle.getRadius();
        
        int x0 = (int)pos.x();
        int y0 = (int)pos.y();
        int r = (int) pos.sub(radius).length();
        int x = 0;
        int y = r;
        int delta = 1 - 2 * r;
        int error = 0;
        while(x <= y) {
            scene.fillPixel(x0 + x, y0 + y);
            scene.fillPixel(x0 + x, y0 - y);
            scene.fillPixel(x0 - x, y0 + y);
            scene.fillPixel(x0 - x, y0 - y);

            scene.fillPixel(x0 + y, y0 + x);
            scene.fillPixel(x0 + y, y0 - x);
            scene.fillPixel(x0 - y, y0 + x);
            scene.fillPixel(x0 - y, y0 - x);

            error = 2 * (delta + y) - 1;
            if(delta < 0 && error <= 0) {
                ++x;
                delta += 2 * x + 1;
                continue;
            }
            error = 2 * (delta - x) - 1;
            if(delta > 0 && error > 0) {
                --y;
                delta += 1 - 2 * y;
                continue;
            }
            ++x;
            delta += 2 * (x - y);
            --y;
        }
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }

    @Override
    public Circle getShape() {
        return (Circle) super.getShape();
    }
}
