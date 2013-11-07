package com.pgiletich.graphics.model;

public class Line implements GeometricShape{
    public Point start;
    public Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(int x1, int y1, int x2, int y2){
        this(new Point(x1, y1), new Point(x2, y2));
    }
}
