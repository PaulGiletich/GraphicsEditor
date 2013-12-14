package com.pgiletich.graphics.util;


public class Matrix {

    private final double[][] data;

    public Matrix(double[][] data) {
        this.data = data;
    }

    public Matrix multiply(Matrix other) {

        int aRows = data.length;
        int aColumns = this.data[0].length;
        int bRows = other.data.length;
        int bColumns = other.data[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] result = new double[aRows][bColumns];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    result[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }

        return new Matrix(result);
    }

    public double get(int row, int column){
        return data[row][column];
    }
}