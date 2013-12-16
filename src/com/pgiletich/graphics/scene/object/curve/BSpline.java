package com.pgiletich.graphics.scene.object.curve;

import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.PointList;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.util.Matrix;

import java.util.List;

public class BSpline extends AbstractCurve {
    private static final Matrix basicMatrix = new Matrix(
            new double[][]{
                    {-1, 3, -3, 1},
                    {3, -6, 3, 0},
                    {-3, 0, 3, 0},
                    {1, 4, 1, 0}
            });

    public BSpline(PointList shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        List<Point> points = getShape().points();

        if(points.size() < 4){
            return;
        }

        double tStep = calculateTStep(points);

        for(int i = 0; i < points.size() - 4; i++){

            Matrix xMatrix = new Matrix(
                    new double[][]{
                            {points.get(i+0).x()},
                            {points.get(i+1).x()},
                            {points.get(i+2).x()},
                            {points.get(i+3).x()}
                    }
            );

            Matrix yMatrix = new Matrix(
                    new double[][]{
                            {points.get(i+0).y()},
                            {points.get(i+1).y()},
                            {points.get(i+2).y()},
                            {points.get(i+3).y()}
                    }
            );

            for (double t = 0; t <= 1; t += tStep) {
                scene.fillPixel(
                        (int)Math.round(this.calculatePoint(t, xMatrix)),
                        (int)Math.round(this.calculatePoint(t, yMatrix)));
            }
        }
    }

    private double calculatePoint(double t, Matrix pointsMatrix) {
        Matrix firstPart = getTMatrix(t).multiply(basicMatrix);
        Matrix result = firstPart.multiply(pointsMatrix);
        return result.get(0,0) / 6.0;
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

    private double calculateTStep(List<Point> points) {
        return 0.001;
    }
}
