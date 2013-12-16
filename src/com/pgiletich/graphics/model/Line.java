package com.pgiletich.graphics.model;

public class Line extends PointList {

    public Line(Point start, Point end) {
        super(new Point[2]);
        this.setStart(start);
        this.setEnd(end);
    }

    public Line(int x1, int y1, int x2, int y2){
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Point start() {
        return points().get(0);
    }

    public void setStart(Point start) {
        points().set(0, start);
    }

    public Point end() {
        return points().get(1);
    }

    public void setEnd(Point end) {
        points().set(1, end);
    }
}
