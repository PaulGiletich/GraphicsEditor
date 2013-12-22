package com.pgiletich.graphics.util;


public class Matrix {

    private final double[][] data;

    public Matrix(double[][] data) {
        this.data = data;
    }

    public Matrix multiply(Matrix other) {

        int aRows = rowCount();
        int aColumns = columnCount();
        int bRows = other.rowCount();
        int bColumns = other.columnCount();

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

    public int columnCount() {
        return data[0].length;
    }

    public int rowCount(){
        return data.length;
    }

    public double get(int row, int column){
        return data[row][column];
    }

    public void multiply(int m) {
        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                data[i][j] *= m;
            }
        }
    }
}