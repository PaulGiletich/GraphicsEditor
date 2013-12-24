package com.pgiletich.graphics.model;

import com.pgiletich.graphics.util.Matrix;

import java.util.ArrayList;
import java.util.List;

public class Cube implements GeometricShape {
    public Point[] points = {
            new Point(-1,1,-1,1),
            new Point(1,1,-1,1),
            new Point(1,-1,-1,1),
            new Point(-1,-1,-1,1),
            new Point(-1,1,1,1),
            new Point(1,1,1,1),
            new Point(1,-1,1,1),
            new Point(-1,-1,1,1)
    };

    public Cube(Point pos, int size) {
        this.pos = pos;
        this.size = size;
    }

    public Point pos;
    public int size;

    public Polygon[] getSides(){
        Polygon side1 = new Polygon(points[0], points[1], points[2], points[3]); //front
        Polygon side2 = new Polygon(points[4], points[5], points[6], points[7]); //back
        Polygon side3 = new Polygon(points[3], points[2], points[6], points[7]);
        Polygon side4 = new Polygon(points[0], points[1], points[5], points[4]);
        Polygon side5 = new Polygon(points[1], points[5], points[6], points[2]);
        Polygon side6 = new Polygon(points[0], points[4], points[7], points[3]);
        return new Polygon[]{side1, side2, side3, side4, side5, side6};
    }

    public Matrix getPlanesMatrix(){
        Point innerPoint = (points[3].add(points[5])).divide(2);
        List<Matrix> planes = new ArrayList<>();
        for (Polygon p: getSides()){
            Matrix planeMatrix = p.getPlaneMatrix();
            if(innerPoint.x() * planeMatrix.get(0, 0)
                    + innerPoint.y() * planeMatrix.get(1, 0)
                    + innerPoint.z() * planeMatrix.get(2, 0)
                    + planeMatrix.get(3, 0) > 0){
                planeMatrix.multiply(-1);
            }
            planes.add(planeMatrix);
        }

        double[][] result = new double[4][6];
        for(int i = 0; i < planes.size(); i++){
            result[0][i] = planes.get(i).get(0, 0);
            result[1][i] = planes.get(i).get(1, 0);
            result[2][i] = planes.get(i).get(2, 0);
            result[3][i] = planes.get(i).get(3, 0);
        }
        return new Matrix(result);
    }
}
