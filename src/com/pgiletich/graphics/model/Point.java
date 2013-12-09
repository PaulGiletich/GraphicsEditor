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

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        result = 31 * result + i;
        return result;
    }
}
