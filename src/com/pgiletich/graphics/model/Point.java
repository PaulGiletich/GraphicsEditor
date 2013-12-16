package com.pgiletich.graphics.model;

import java.util.Arrays;

public class Point implements GeometricShape{
    private double[] coords;

    public Point(double x, double y) {
        coords = new double[2];
        this.setX(x);
        this.setY(y);
    }

    public Point(double x, double y, double z, double i) {
        coords = new double[4];
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setI(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (!Arrays.equals(coords, point.coords)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coords);
    }

    @Override
    public String toString() {
        return "Point{" + Arrays.toString(coords) + '}';
    }

    public double getX() {
        return coords[0];
    }

    public void setX(double x) {
        this.coords[0] = x;
    }

    public double getY() {
        return coords[1];
    }

    public void setY(double y) {
        this.coords[1] = y;
    }

    public double getZ() {
        return coords[2];
    }

    public void setZ(double z) {
        this.coords[2] = z;
    }

    public double getI() {
        return coords[3];
    }

    public void setI(double i) {
        this.coords[3] = i;
    }
}
