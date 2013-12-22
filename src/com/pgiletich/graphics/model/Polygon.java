package com.pgiletich.graphics.model;

import com.pgiletich.graphics.util.Matrix;
import com.pgiletich.graphics.util.PlaneUtil;

import java.util.ArrayList;
import java.util.List;

public class Polygon extends PointList {
    public Polygon(Point... points) {
        super(points);
    }

    public Matrix getPlaneMatrix(){
        return PlaneUtil.getPlaneMatrix(points().get(0), points().get(1), points().get(2));
    }

    public boolean IsConvex()
    {
        if (points().size() >= 3){
            for (int a = 0, b = 1, c = 2; c < points().size(); a++, b++, c++){
                if (!new Line(points().get(a), points().get(b)).isLeft(points().get(c)))
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
}
