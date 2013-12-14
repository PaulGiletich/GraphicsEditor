package com.pgiletich.graphics.model;

public class Point implements GeometricShape{
    public double x;
    public double y;
    public double z;
    public double i;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
        this.i = 0;
    }

    public Point(double x, double y, double z, double i) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (i != point.i) return false;
        if (x != point.x) return false;
        if (y != point.y) return false;
        if (z != point.z) return false;

        return true;
    }

    //yeah, i can explain this
    @Override
    public int hashCode() {
        int result = (int) x;
        result = (int) (31 * result + y);
        result = (int) (31 * result + z);
        result = (int) (31 * result + i);
        return result;
    }
}
