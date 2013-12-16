package com.pgiletich.graphics.model;

public class Parabola implements GeometricShape {
    private Point pos;
    private int power = 2;
    private int p;

    public Parabola(Point pos, int p) {
        this.pos = pos;
        this.p = p;
    }

    public Point getPos() {
        return pos;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }
}
