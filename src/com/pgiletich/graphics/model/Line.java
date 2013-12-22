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

    public Point normal(){
        return new Point(end().y() - start().y(), start().x() - end().x());
    }

    public Point direction(){
        return new Point(end().x() - start().x(), end().y() - start().y());
    }

    public void flip() {
        Point tmp = start();
        setStart(end());
        setEnd(tmp);
    }

    public boolean isLeft(Point c) {
        Point a = start();
        Point b = end();
        return ((b.x() - a.x())*(c.y() - a.y()) - (b.y() - a.y())*(c.x() - a.x())) > 0;
    }

    public double IntersectionParameter(Line o){
        Point segmentToEdge = o.start().sub(this.start());
        Point segmentDir = this.direction();
        Point edgeDir = o.direction();

        return edgeDir.cross(segmentToEdge) / edgeDir.cross(segmentDir);
    }

    public Line morph(double tStart, double tEnd) {
        Point d = direction();
        return new Line(this.start().add(d.mult(tStart)), this.start().add(d.mult(tEnd)));
    }
}
