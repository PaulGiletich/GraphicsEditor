package com.pgiletich.graphics.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PointSet implements GeometricShape {
    private Set<Point> points;

    public PointSet(Point... points){
        this.points = new HashSet<>(Arrays.asList(points));
    }

    public PointSet() {
        this(new Point[0]);
    }

    public Set<Point> points() {
        return points;
    }
}
