package com.pgiletich.graphics.scene.object.curve;

import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.PointList;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.util.Matrix;

import java.util.List;

public class Bezier extends AbstractCurve {

    private static final Matrix basicMatrix = new Matrix(
            new double[][]{
                    {-1, 3, -3, 1},
                    {3, -6, 3, 0},
                    {-3, 3, 0, 0},
                    {1, 0, 0, 0}
            });

    public Bezier(PointList shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        List<Point> points = getShape().points();

        double tStep = calculateTStep(points);
        
        Matrix xMatrix = new Matrix(
                new double[][]{
                        {points.get(0).getX()},
                        {points.get(1).getX()},
                        {points.get(2).getX()},
                        {points.get(3).getX()}
                }
        );

        Matrix yMatrix = new Matrix(
                new double[][]{
                        {points.get(0).getY()},
                        {points.get(1).getY()},
                        {points.get(2).getY()},
                        {points.get(3).getY()}
                }
        );

        for (double t = 0; t <= 1; t += tStep) {
            scene.fillPixel(
                    (int)Math.round(this.calculatePoint(t, xMatrix)),
                    (int)Math.round(this.calculatePoint(t, yMatrix)));
        }
    }

    private double calculatePoint(double t, Matrix pointsMatrix) {
        Matrix firstPart = getTMatrix(t).multiply(basicMatrix);
        Matrix result =  firstPart.multiply(pointsMatrix);
        return result.get(0,0);
    }

    private Matrix getTMatrix(double t) {
        return new Matrix(
                new double[][]{{
                        Math.pow(t, 3),
                        Math.pow(t, 2),
                        t,
                        1
                }}
        );
    }

    private double calculateTStep(List<Point> points){
        return 0.001;
    }
}
