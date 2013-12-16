package com.pgiletich.graphics.model;

public class Circle implements GeometricShape {
    private final Point pos;
    private final Point radius;

    public Circle(Point pos, Point radius){
        this.pos = pos;
        this.radius = radius;
    }

    public Point getPos() {
        return pos;
    }

    public Point getRadius() {
        return radius;
    }
}
