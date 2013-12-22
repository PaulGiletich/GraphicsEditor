package com.pgiletich.graphics.model;

public class Rect implements GeometricShape {
    private Point topLeft;
    private Point bottomRight;

    public Rect(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point topLeft() {
        return topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public Point bottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }


    public double top(){
        return topLeft.y();
    }

    public double bottom(){
        return bottomRight.y();
    }

    public double left(){
        return topLeft.x();
    }

    public double right(){
        return bottomRight.x();
    }
}
