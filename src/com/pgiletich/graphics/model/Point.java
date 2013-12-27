package com.pgiletich.graphics.model;

import java.util.Arrays;

public class Point implements GeometricShape{

    private double[] coords;

    public Point(double... coords){
        this.coords = coords;
    }

    public Point(double x, double y) {
        coords = new double[2];
        this.setX(x);
        this.setY(y);
    }

    public Point(double x, double y, double z, double i) {
        coords = new double[4];
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setI(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (!Arrays.equals(coords, point.coords)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return coords != null ? Arrays.hashCode(coords) : 0;
    }

    @Override
    public String toString() {
        return "Point" + Arrays.toString(coords);
    }

    public double[] getCoords() {
        return coords;
    }

    public void setCoord(int index, double val){
        coords[index] = val;
    }

    public double x() {
        return coords[0];
    }

    public Point setX(double x) {
        this.coords[0] = x;
        return this;
    }

    public double y() {
        return coords[1];
    }

    public Point setY(double y) {
        this.coords[1] = y;
        return this;
    }

    public double z() {
        return coords[2];
    }

    public void setZ(double z) {
        this.coords[2] = z;
    }

    public double i() {
        return coords[3];
    }

    public void setI(double i) {
        this.coords[3] = i;
    }

    public Point add(Point b){
        int newLength = Math.min(coords.length, b.coords.length);
        double[] newCoords = new double[newLength];
        for(int i = 0; i < newLength; i++){
            newCoords[i] = coords[i] + b.coords[i];
        }
        return new Point(newCoords);
    }

    public Point sub(Point b){
        int newLength = Math.min(coords.length, b.coords.length);
        double[] newCoords = new double[newLength];
        for(int i = 0; i < newLength; i++){
            newCoords[i] = coords[i] - b.coords[i];
        }
        return new Point(newCoords);
    }

    public Point mult(double m){
        double[] newCoords = new double[coords.length];
        for(int i = 0; i < coords.length; i++){
            newCoords[i] = coords[i] * m;
        }
        return new Point(newCoords);
    }

    public double dot(Point b){
        return x() * b.x() + y() * b.y();
    }

    public double cross(Point b){
        return x() * b.y() - y() * b.x();
    }

    public Point divide(double d) {
        double[] newCoords = new double[coords.length];
        for(int i = 0; i < coords.length; i++){
            newCoords[i] = coords[i] / d;
        }
        return new Point(newCoords);
    }
}
