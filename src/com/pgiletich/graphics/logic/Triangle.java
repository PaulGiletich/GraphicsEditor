package com.pgiletich.graphics.logic;

import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.util.PointUtil;

import java.util.*;

public class Triangle {
    private Point[] points;

    public Triangle(Point... points){
        if(points.length != 3){
            throw new IllegalArgumentException("dgvgdffghfg");
        }
        this.points = points;
    }

    public boolean isNeighbor(Triangle triangle) {
        int count = 0;
        for (Point vertex: points)
            if (Arrays.asList(points).contains(vertex)) count++;
        return count == 2;
    }

    public Set<Point> facetOpposite(Point vertex) {
        if(!Arrays.asList(points).contains(vertex)){
            throw new IllegalArgumentException("uogvocdfvmgi");
        }

        HashSet<Point> result = new HashSet<>(Arrays.asList(points));
        result.remove(vertex);
        return result;
    }

    public Point getCircumcenter() {
        return PointUtil.circumcenter(points);
    }

    public boolean contains(Point point){
        return Arrays.asList(points).contains(point);
    }

    public Point getVertexButNot(Point... badVertices) {
        Collection<Point> bad = Arrays.asList(badVertices);
        for (Point v: points) {
            if (!bad.contains(v)) return v;
        }
        throw new NoSuchElementException("No vertex found");
    }

    public Point[] getPoints() {
        return points;
    }

    public List<Line> getEdges(){
        List<Line> edges = new ArrayList<>();
        edges.add(new Line(points[0], points[1]));
        edges.add(new Line(points[1], points[2]));
        edges.add(new Line(points[2], points[0]));
        return edges;
    }

    @Override
    public String toString() {
        return "Triangle{" + Arrays.toString(points) + '}';
    }

}
