package com.pgiletich.graphics.debugger;

import com.pgiletich.graphics.model.Point;

import java.util.*;

public class GraphicEquationSolver {
    private static Map<Point, Integer> figures = new HashMap<>();
    private static Set<Point> currentObject;

    public static boolean isEnabled = false;

    public static boolean addPoint(Point p){
        if(!currentObject.contains(p)){
            currentObject.add(p);
            figures.put(p, figures.containsKey(p)? figures.get(p)+1 : 1);
        }
        return figures.get(p) > 1;
    }

    public static void clear(){
        figures.clear();
    }

    public static void newObject() {
        currentObject = new HashSet<>();
    }

    public static Point[] getSolves(){
        List<Point> result = new ArrayList<>();
        for(Map.Entry<Point, Integer> entry: figures.entrySet()){
            if(entry.getValue() > 1){
                result.add(entry.getKey());
            }
        }
        return result.toArray(new Point[result.size()]);
    }
}
