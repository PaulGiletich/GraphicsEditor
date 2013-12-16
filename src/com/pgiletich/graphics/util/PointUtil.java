package com.pgiletich.graphics.util;


import com.pgiletich.graphics.logic.Triangle;
import com.pgiletich.graphics.model.Point;
import delaunay.Pnt;

public class PointUtil {

    public static Point projection(Point p, double width, double height, int distance, int fov) {
        double factor = fov / (distance + p.getZ());
        double x = p.getX() * factor + width;
        double y = p.getY() * factor + height;

        return new Point(x, y);
    }

    public static Matrix getMatrix(Point p){
        return new Matrix(
                new double[][]{
                        new double[]{
                                p.getX(),
                                p.getY(),
                                p.getZ(),
                                p.getI()
                        }
                });
    }

    public static Point circumcenter(Point[] simplex) {
        Pnt[] ps = new Pnt[3];
        for(int i = 0; i < simplex.length; i++){
            ps[i] = new Pnt(simplex[i].getX(), simplex[i].getY());
        }
        Pnt center = Pnt.circumcenter(ps);
        return new Point(center.coord(0), center.coord(1));
    }

    public static boolean isInside(Point point, Triangle triangle) {
        Pnt p = new Pnt(point.getX(), point.getY());
        Point[] points = triangle.getPoints();
        Pnt[] ps = new Pnt[3];
        for(int i = 0; i < points.length; i++){
            ps[i] = new Pnt(points[i].getX(), points[i].getY());
        }
        return p.isOutside(ps) == null;
    }

    public static int vsCircumcircle(Point myPoint, Point[] mySimplex) {
        Pnt p = new Pnt(myPoint.getX(), myPoint.getY());
        Pnt[] ps = new Pnt[3];
        for(int i = 0; i < mySimplex.length; i++){
            ps[i] = new Pnt(mySimplex[i].getX(), mySimplex[i].getY());
        }

        return p.vsCircumcircle(ps);
    }
}
