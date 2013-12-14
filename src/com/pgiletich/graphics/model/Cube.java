package com.pgiletich.graphics.model;

public class Cube implements GeometricShape {
    public Point[] points = {
            new Point(-1,1,-1,1),
            new Point(1,1,-1,1),
            new Point(1,-1,-1,1),
            new Point(-1,-1,-1,1),
            new Point(-1,1,1,1),
            new Point(1,1,1,1),
            new Point(1,-1,1,1),
            new Point(-1,-1,1,1)
    };

    public Cube(Point pos, int size) {
        this.pos = pos;
        this.size = size;
    }

    public Point pos;
    public int size;

}
