package com.pgiletich.graphics.model;

import com.pgiletich.graphics.util.Matrix;
import com.pgiletich.graphics.util.PlaneUtil;
import com.pgiletich.graphics.util.PointUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Polygon extends PointList {
    public Polygon(Point... points) {
        super(points);
    }

    public Matrix getPlaneMatrix(){
        return PlaneUtil.getPlaneMatrix(points().get(0), points().get(1), points().get(2));
    }

    public boolean isConvex(){
        List<Point> cwPoints = pointsClockwise();
        if (cwPoints.size() >= 3){
            for (int a = 0, b = 1, c = 2; c < cwPoints.size(); a++, b++, c++){
                if (!new Line(cwPoints.get(a), cwPoints.get(b)).isLeft(cwPoints.get(c)))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Line> edges() {
        List<Line> result = new ArrayList<>();
        for(int i = 0; i < points().size() - 1; i++){
            result.add(new Line(points().get(i), points().get(i+1)));
        }
        result.add(new Line(points().get(points().size()-1), points().get(0)));
        return result;
    }

    public List<Point> pointsClockwise(){
        List<Point> result = new ArrayList<>(points());

        final Point center = PointUtil.mean(result);
        Collections.sort(result, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double degree1 = Math.toDegrees(Math.atan2(o1.sub(center).x(), o1.sub(center).y())) % 360;
                double degree2 = Math.toDegrees(Math.atan2(o2.sub(center).x(), o2.sub(center).y())) % 360;
                return (int) (degree2 - degree1);
            }
        });
        return result;
    }

    public Iterable<Line> edgesClockwise(){
        List<Point> points = pointsClockwise();

        List<Line> result = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i++){
            result.add(new Line(points.get(i), points.get(i + 1)));
        }
        result.add(new Line(points.get(points.size() - 1), points.get(0)));
        return result;
    }
}
