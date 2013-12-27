package com.pgiletich.graphics.util;


import com.pgiletich.graphics.model.Point;

import java.util.Collection;

public class PointUtil {

    public static Point projection(Point p, double width, double height, int distance, int fov) {
        double factor = fov / (distance + p.z());
        double x = p.x() * factor + width;
        double y = p.y() * factor + height;

        return new Point(x, y);
    }

    public static Matrix getMatrix(Point p){
        return new Matrix(
                new double[][]{
                        new double[]{
                                p.x(),
                                p.y(),
                                p.z(),
                                p.i()
                        }
                });
    }

    public static Point mean(Collection<Point> points){
        double[] newCoords = new double[4];
        for (Point p: points){
            double[] coords = p.getCoords();
            for (int i = 0; i < coords.length; i++) {
                newCoords[i] += coords[i];
            }
        }
        for (int i = 0; i < newCoords.length; i++){
            newCoords[i] /= points.size();
        }
        return new Point(newCoords);
    }
}
