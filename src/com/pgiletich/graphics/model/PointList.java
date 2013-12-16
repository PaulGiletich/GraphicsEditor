package com.pgiletich.graphics.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointList implements GeometricShape {
    private List<Point> points;

    public PointList(Point... points){
        this.points = new ArrayList<>(Arrays.asList(points));
    }

    public PointList() {
        this(new Point[0]);
    }

    public List<Point> points() {
        return points;
    }
}
