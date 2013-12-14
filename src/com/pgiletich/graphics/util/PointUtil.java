package com.pgiletich.graphics.util;


import com.pgiletich.graphics.model.Point;

public class PointUtil {

    public static Point projection(Point p, double width, double height, int distance, int fov) {
        double factor = fov / (distance + p.z);
        double x = p.x * factor + width;
        double y = p.y * factor + height;

        return new Point(x, y);
    }

    public static Matrix getMatrix(Point p){
        return new Matrix(
                new double[][]{
                        new double[]{
                                p.x,
                                p.y,
                                p.z,
                                p.i
                        }
                });
    }
}
