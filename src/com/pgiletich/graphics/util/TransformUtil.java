package com.pgiletich.graphics.util;

import com.pgiletich.graphics.model.Point;

public class TransformUtil {

    public enum Axis {X, Y, Z}

    public static Point rotate(Point point, Axis axis, int angle){
        Matrix result = PointUtil.getMatrix(point).multiply(getRotateMatrix(axis, angle));
        return  new Point(result.get(0,0), result.get(0,1), result.get(0,2), result.get(0,3));
    }
    
    public static Point scale(Point point, double sx, double sy, double sz){
        Matrix result = PointUtil.getMatrix(point).multiply(getScaleMatrix(sx, sy, sz));
        return  new Point(result.get(0,0), result.get(0,1), result.get(0,2), result.get(0,3));
    }
    
    private static Matrix getTranslateMatrix(double dx, double dy, double dz){
        return new Matrix(new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {dx, dy, dz, 1}
        });
    }

    private static Matrix getScaleMatrix(double sx, double sy, double sz) {
        return new Matrix(new double[][]{
                {sx, 0, 0, 0},
                {0, sy, 0, 0},
                {0, 0, sz, 0},
                {0, 0, 0, 1}
        });
    }

    private static Matrix getRotateMatrix(Axis axis, int angle) {
        double rad = angle * Math.PI / 180;
        double cosa = Math.cos(rad);
        double sina = Math.sin(rad);
        switch (axis){
            case Y:
                return new Matrix(
                        new double[][]{
                                {1, 0, 0, 0},
                                {0, cosa, sina, 0},
                                {0, -sina, cosa, 0},
                                {0, 0, 0, 1}
                        });
            case X:
                return new Matrix(
                        new double[][]{
                                {cosa, 0, -sina, 0},
                                {0, 1, 0, 0},
                                {sina, 0, cosa, 0},
                                {0, 0, 0, 1}
                        });
            default: return null;
        }
    }
    
    
}


