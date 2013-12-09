package com.pgiletich.graphics.scene.object.curve;

import com.pgiletich.graphics.model.Curve;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.util.Matrix;

import java.util.List;

public class Bezier extends AbstractCurve {

    private static final Matrix basicMatrix = new Matrix(
            new double[]{-1, 3, -3, 1},
            new double[]{3, -6, 3, 0},
            new double[]{-3, 3, 0, 0},
            new double[]{1, 0, 0, 0});

    public Bezier(Curve shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        List<Point> points = getShape().points;

        double tStep = calculateTStep(points);
        
        Matrix xMatrix = new Matrix(
                new double[]{points.get(0).x},
                new double[]{points.get(1).x},
                new double[]{points.get(2).x},
                new double[]{points.get(3).x}
        );

        Matrix yMatrix = new Matrix(
                new double[]{points.get(0).y},
                new double[]{points.get(1).y},
                new double[]{points.get(2).y},
                new double[]{points.get(3).y}
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
