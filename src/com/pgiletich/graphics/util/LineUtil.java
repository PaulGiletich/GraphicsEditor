package com.pgiletich.graphics.util;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.model.Point;

public class LineUtil {
    public static void flip(Line line){
        Point tmp = line.start();
        line.setStart(line.end());
        line.setEnd(tmp);
    }
}
