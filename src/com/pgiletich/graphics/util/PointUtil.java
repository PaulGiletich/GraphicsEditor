package com.pgiletich.graphics.util;


import com.pgiletich.graphics.logic.Triangle;
import com.pgiletich.graphics.model.Point;
import delaunay.Pnt;

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

    public static Point circumcenter(Point[] simplex) {
        Pnt[] ps = new Pnt[3];
        for(int i = 0; i < simplex.length; i++){
            ps[i] = new Pnt(simplex[i].x(), simplex[i].y());
        }
        Pnt center = Pnt.circumcenter(ps);
        return new Point(center.coord(0), center.coord(1));
    }

    public static boolean isInside(Point point, Triangle triangle) {
        Pnt p = new Pnt(point.x(), point.y());
        Point[] points = triangle.getPoints();
        Pnt[] ps = new Pnt[3];
        for(int i = 0; i < points.length; i++){
            ps[i] = new Pnt(points[i].x(), points[i].y());
        }
        return p.isOutside(ps) == null;
    }

    public static int vsCircumcircle(Point myPoint, Point[] mySimplex) {
        Pnt p = new Pnt(myPoint.x(), myPoint.y());
        Pnt[] ps = new Pnt[3];
        for(int i = 0; i < mySimplex.length; i++){
            ps[i] = new Pnt(mySimplex[i].x(), mySimplex[i].y());
        }

        return p.vsCircumcircle(ps);
    }
}
