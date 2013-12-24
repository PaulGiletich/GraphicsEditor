package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.Rect;
import com.pgiletich.graphics.scene.GraphicsScene;

import static com.pgiletich.graphics.scene.object.line.CDALine.Drawer.draw;

public class ClipRect extends GraphicsObject implements Clipper {

    public ClipRect(Rect shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        Rect rect = getShape();
        Point tl = rect.topLeft();
        Point br = rect.bottomRight();

        draw(scene, new Line(
                new Point(tl.x(), rect.top()),
                new Point(br.x(), rect.top())
        ));
        draw(scene, new Line(
                new Point(tl.x(), rect.bottom()),
                new Point(br.x(), rect.bottom())
        ));
        draw(scene, new Line(
                new Point(rect.left(), tl.y()),
                new Point(rect.left(), br.y())
        ));
        draw(scene, new Line(
                new Point(rect.right(), tl.y()),
                new Point(rect.right(), br.y())
        ));
    }

    private final static int INSIDE = 0;
    private final static int LEFT = 1 << 0;
    private final static int RIGHT = 1 << 1;
    private final static int TOP = 1 << 2;
    private final static int BOTTOM = 1 << 3;

    private int vcode(Rect r, Point p){
        return	(p.x() < r.left() ? LEFT : 0) +
                (p.x() > r.right() ? RIGHT : 0) +
                (p.y() > r.bottom() ? BOTTOM : 0) +
                (p.y() < r.top() ? TOP : 0);
    }

    @Override
    public boolean clip(Line line){
        Rect rect = getShape();

        Point start = line.start();
        Point end = line.end();

        int startCode = vcode(rect, start);
        int endCode = vcode(rect, end);

        while (true) {

            //both inside, return true
            if(startCode == INSIDE && endCode == INSIDE)
                return true;

            //both endpoints are on same side
            if ((startCode & endCode) != INSIDE)
                return false;

            if (startCode == INSIDE && endCode != INSIDE) {
                line.flip();
                clip(line);
                return true;
            }

            if ((startCode & LEFT) != 0) {
                start.setY(start.y() + (start.y() - end.y()) * (rect.left() - start.x()) / (start.x() - end.x()));
                start.setX(rect.left());
            }
            else if ((startCode & RIGHT) != 0) {
                start.setY(start.y() + (start.y() - end.y()) * (rect.right() - start.x()) / (start.x() - end.x()));
                start.setX(rect.right());
            }
            else if ((startCode & BOTTOM) != 0) {
                start.setX(start.x() + (start.x() - end.x()) * (rect.bottom() - start.y()) / (start.y() - end.y()));
                start.setY(rect.bottom());
            }
            else if ((startCode & TOP) != 0) {
                start.setX(start.x() + (start.x() - end.x()) * (rect.top() - start.y()) / (start.y() - end.y()));
                start.setY(rect.top());
            }

            startCode = vcode(rect, start);
            endCode = vcode(rect, end);
        }
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }

    @Override
    public Rect getShape() {
        return (Rect) super.getShape();
    }
}
