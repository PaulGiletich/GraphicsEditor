package com.pgiletich.graphics.model;

import com.pgiletich.graphics.util.Matrix;
import com.pgiletich.graphics.util.PlaneUtil;

public class Polygon extends PointList {
    public Polygon(Point... points) {
        super(points);
    }

    public Matrix getPlaneMatrix(){
        return PlaneUtil.getPlaneMatrix(points().get(0), points().get(1), points().get(2));
    }
}
