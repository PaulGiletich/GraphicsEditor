package com.pgiletich.graphics.util;

import com.pgiletich.graphics.model.Point;

public class PlaneUtil {

    public static Matrix getPlaneMatrix(Point p1, Point p2, Point p3){
        double[] xArray = {p1.x(), p2.x(), p3.x()};
        double[] yArray = {p1.y(), p2.y(), p3.y()};
        double[] zArray = {p1.z(), p2.z(), p3.z()};

        double a = getCoeffValue(new double[]{1, 1, 1}, yArray, zArray);
        double b = getCoeffValue(xArray, new double[]{1, 1, 1}, zArray);
        double c = getCoeffValue(xArray, yArray, new double[]{1, 1, 1});
        double d = -getCoeffValue(xArray, yArray, zArray);

        return new Matrix(new double[][]{
                {a},
                {b},
                {c},
                {d}
        });
    }

    private static double getCoeffValue(double[] xArray, double[] yArray,double[] zArray){
        double x1 = xArray[0];
        double x2 = xArray[1];
        double x3 = xArray[2];

        double y1 = yArray[0];
        double y2 = yArray[1];
        double y3 = yArray[2];

        double z1 = zArray[0];
        double z2 = zArray[1];
        double z3 = zArray[2];

        return x1 * (y2 * z3 - z2 * y3) - x2 * (y1 * z3 - z1 * y3) + x3 * (y1 * z2 - z1 * y2);
    }
}
