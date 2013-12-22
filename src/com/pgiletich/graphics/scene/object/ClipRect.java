package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.Rect;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.util.LineUtil;

import static com.pgiletich.graphics.scene.object.line.CDALine.Drawer.draw;

public class ClipRect extends GraphicsObject {

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

    public void clip(Line line){
        Rect rect = getShape();

        int startCode, endCode;
        Point tmp;

        Point start = line.start();
        Point end = line.end();

        startCode = vcode(rect, start);
        endCode = vcode(rect, end);

        if(startCode == 0 && endCode == 0)
            return;

        if ((startCode & endCode) != 0)
            return;

        while(!(startCode == INSIDE && endCode == INSIDE)){

            if (startCode == INSIDE && endCode != INSIDE) {
                LineUtil.flip(line);
                clip(line);
                return;
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
