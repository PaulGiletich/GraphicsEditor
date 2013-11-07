package com.pgiletich.graphics.model;

public class Point implements GeometricShape{
    public int x;
    public int y;
    public int z;
    public int i;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = 0;
        this.i = 0;
    }
}
