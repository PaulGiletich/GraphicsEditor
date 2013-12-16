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
        int r = (int) Math.sqrt(Math.pow(pos.x() - circle.getRadius().x(), 2) +
                Math.pow(pos.y() - circle.getRadius().y(), 2));
        int x = -r;
        int y = 0;
        int F = 1 - r;
        int dFs = 3;
        int dFd = 5 - 2 * r;
        while (x + y <= 0) {
            scene.fillPixel(x + pos.x(), y + pos.y());		// 4 октант
            scene.fillPixel(-x + pos.x(), y + pos.y());		// 1 октант
            scene.fillPixel(x + pos.x(), -y + pos.y());		// 5 октант
            scene.fillPixel(-x + pos.x(), -y + pos.y());	// 8 октант

            scene.fillPixel(y + pos.x(), x + pos.y());		// 7 октант
            scene.fillPixel(-y + pos.x(), x + pos.y());		// 6 октант
            scene.fillPixel(y + pos.x(), -x + pos.y());		// 2 октант
            scene.fillPixel(-y + pos.x(), -x + pos.y());	// 3 октант

            if (F > 0) {
                F += dFd;
                x++;
                y++;
                dFs += 2;
                dFd += 4;
            }
            else {
                F += dFs;
                y++;
                dFs += 2;
                dFd += 2;
            }
        }
//        if (x + y == 0) {
//            baseDrawer.plot(x + c.x, y + c.y);		// 4 октант
//            baseDrawer.plot(-x + c.x, y + c.y);		// 1 октант
//            baseDrawer.plot(x + c.x, -y + c.y);		// 5 октант
//            baseDrawer.plot(-x + c.x, -y + c.y);	// 8 октант
//        }
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
