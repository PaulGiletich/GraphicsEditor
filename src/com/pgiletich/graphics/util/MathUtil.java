package com.pgiletich.graphics.util;

public class MathUtil {
    public static float floatPart(float a) {
        return a - (int) a;
    }

    public static float reverseFloatPart(float a) {
        return 1 - (a - (int) a);
    }

    public static int sign(double a){
        return a == 0 ? 0 : a > 0 ? 1 : -1;
    }
}