package com.pgiletich.graphics.model;

import java.util.ArrayList;
import java.util.List;

public class Curve implements GeometricShape {
    public List<Point> points;

    public Curve() {
        this.points = new ArrayList<>();
    }

    public Curve(List<Point> points) {
        this.points = points;
    }
}
