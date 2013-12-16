package com.pgiletich.graphics.logic;

import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.util.PointUtil;
import delaunay.Graph;

import java.util.*;

public class Triangulation implements Iterable<Triangle>{
    private Graph<Triangle> triangleGraph = new Graph<>();

    public Triangulation(Triangle triangle){
        triangleGraph.add(triangle);
    }

    public Triangle neighborOpposite(Point vertex, Triangle triangle) {
        if (!triangle.contains(vertex))
            throw new IllegalArgumentException("Bad vertex; not in triangle");
        for (Triangle neighbor: triangleGraph.neighbors(triangle)) {
            if (!neighbor.contains(vertex)) {
                return neighbor;
            }
        }
        return null;
    }

    public Triangle locate (Point point) {
        for (Triangle t: triangleGraph.nodeSet()) {
            if (PointUtil.isInside(point, t)){
                return t;
            }
        }
        System.out.println("Warning: No triangle holds " + point);
        return null;
    }

    private Set<Triangle> getCavity (Point site, Triangle triangle) {
        Set<Triangle> encroached = new HashSet<>();
        Queue<Triangle> toBeChecked = new LinkedList<>();
        Set<Triangle> marked = new HashSet<>();
        toBeChecked.add(triangle);
        marked.add(triangle);
        while (!toBeChecked.isEmpty()) {
            triangle = toBeChecked.remove();
            if (PointUtil.vsCircumcircle(site, triangle.getPoints()) == 1)
                continue; // Site outside triangle => triangle not in cavity
            encroached.add(triangle);
            // Check the neighbors
            for (Triangle neighbor: triangleGraph.neighbors(triangle)){
                if (marked.contains(neighbor)) continue;
                marked.add(neighbor);
                toBeChecked.add(neighbor);
            }
        }
        return encroached;
    }
    
    public void place(Point site) {
        // Uses straightforward scheme rather than best asymptotic time

        // Locate containing triangle
        Triangle triangle = locate(site);
        // Give up if no containing triangle or if site is already in DT
        if (triangle == null)
            throw new IllegalArgumentException("No containing triangle");
        if (triangle.contains(site)) return;

        // Determine the cavity and update the triangulation
        Set<Triangle> cavity = getCavity(site, triangle);
        update(site, cavity);
    }

    private Triangle update(Point site, Set<Triangle> cavity) {
        Set<Set<Point>> boundary = new HashSet<>();
        Set<Triangle> theTriangles = new HashSet<Triangle>();

        // Find boundary facets and adjacent triangles
        for (Triangle triangle: cavity) {
            theTriangles.addAll(triangleGraph.neighbors(triangle));
            for (Point vertex: triangle.getPoints()) {
                Set<Point> facet = triangle.facetOpposite(vertex);
                if (boundary.contains(facet)) boundary.remove(facet);
                else boundary.add(facet);
            }
        }
        theTriangles.removeAll(cavity);        // Adj triangles only

        // Remove the cavity triangles from the triangulation
        for (Triangle triangle: cavity) triangleGraph.remove(triangle);

        // Build each new triangle and add it to the triangulation
        Set<Triangle> newTriangles = new HashSet<Triangle>();
        for (Set<Point> vertices: boundary) {
            vertices.add(site);
            Triangle tri = new Triangle(vertices.toArray(new Point[vertices.size()]));
            triangleGraph.add(tri);
            newTriangles.add(tri);
        }

        // Update the graph links for each new triangle
        theTriangles.addAll(newTriangles);    // Adj triangle + new triangles
        for (Triangle triangle: newTriangles)
            for (Triangle other: theTriangles)
                if (triangle.isNeighbor(other))
                    triangleGraph.add(triangle, other);

        // Return one of the new triangles
        return newTriangles.iterator().next();
    }

    public static void main (String[] args) {
        Triangle tri =
                new Triangle(new Point(-10,10), new Point(10,10), new Point(0,-10));
        System.out.println("Triangle created: " + tri);
        Triangulation dt = new Triangulation(tri);
        System.out.println("DelaunayTriangulation created: " + dt);
        dt.place(new Point(0, 0));
        dt.place(new Point(1, 0));
        dt.place(new Point(0, 1));
        System.out.println("After adding 3 points, we have a " + dt);
        System.out.println("Triangles: " + dt.triangleGraph.nodeSet());
    }


    @Override
    public Iterator<Triangle> iterator() {
        return triangleGraph.nodeSet().iterator();
    }
}
