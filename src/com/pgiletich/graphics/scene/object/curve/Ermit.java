package com.pgiletich.graphics.scene.object.curve;

import com.pgiletich.graphics.model.Curve;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.util.Matrix;

import java.util.List;

public class Ermit extends AbstractCurve {

    private static final Matrix basicMatrix = new Matrix(
            new double[]{2, -2, 1, 1},
            new double[]{-3, 3, -2, -1},
            new double[]{0, 0, 1, 0},
            new double[]{1, 0, 0, 0});

    public Ermit(Curve shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        List<Point> points = getShape().points;

        double tStep = calculateTStep(points);

        Point r1 = calculateRVector(points.get(0), points.get(1));
        Point r4 = calculateRVector(points.get(2), points.get(3));
        
        Matrix xMatrix = new Matrix(
                new double[]{points.get(0).x},
                new double[]{points.get(3).x},
                new double[]{r1.x},
                new double[]{r4.x}
        );

        Matrix yMatrix = new Matrix(
                new double[]{points.get(0).y},
                new double[]{points.get(3).y},
                new double[]{r1.y},
                new double[]{r4.y}
        );

        for (double t = 0; t <= 1; t += tStep) {
            scene.fillPixel(
                    (int)Math.round(this.calculatePoint(t, xMatrix)),
                    (int)Math.round(this.calculatePoint(t, yMatrix)));
        }
    }

    private Point calculateRVector(Point p1, Point p2) {
        return new Point(p2.x - p1.x, p2.y - p1.y);
    }

    private double calculatePoint(double t, Matrix pointsMatrix) {
        Matrix firstPart = getTMatrix(t).multiply(basicMatrix);
        Matrix result =  firstPart.multiply(pointsMatrix);
        return result.get(0,0);
    }

    private Matrix getTMatrix(double t) {
        return new Matrix(
                new double[]{
                        Math.pow(t, 3),
                        Math.pow(t, 2),
                        t,
                        1
                }
        );
    }

    private double calculateTStep(List<Point> points){
        return 0.001;//TODO
    }
}
