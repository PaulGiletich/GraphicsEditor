package com.pgiletich.graphics.scene.object.curve;

import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.PointList;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.util.Matrix;

import java.util.List;

public class Ermit extends AbstractCurve {

    private static final Matrix basicMatrix = new Matrix(
            new double[][]{
                    {2, -2, 1, 1},
                    {-3, 3, -2, -1},
                    {0, 0, 1, 0},
                    {1, 0, 0, 0}
            });

    public Ermit(PointList shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        List<Point> points = getShape().points();

        double tStep = calculateTStep(points);

        Point r1 = calculateRVector(points.get(0), points.get(1));
        Point r4 = calculateRVector(points.get(2), points.get(3));

        Matrix xMatrix = new Matrix(
                new double[][]{
                        {points.get(0).getX()},
                        {points.get(3).getX()},
                        {r1.getX()},
                        {r4.getX()}
                });

        Matrix yMatrix = new Matrix(
                new double[][]{
                        {points.get(0).getY()},
                        {points.get(3).getY()},
                        {r1.getY()},
                        {r4.getY()}
                });

        for (double t = 0; t <= 1; t += tStep) {
            scene.fillPixel(
                    (int)Math.round(this.calculatePoint(t, xMatrix)),
                    (int)Math.round(this.calculatePoint(t, yMatrix)));
        }
    }

    private Point calculateRVector(Point p1, Point p2) {
        return new Point((int) (p2.getX() - p1.getX()), (int) (p2.getY() - p1.getY()));
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
