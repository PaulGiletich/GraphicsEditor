package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.logic.Triangle;
import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.model.PointSet;
import com.pgiletich.graphics.scene.GraphicsScene;
import delaunay.Pnt;

import java.util.HashSet;
import java.util.Set;

import static com.pgiletich.graphics.scene.object.line.AntialiasedLine.Drawer.draw;

public class GraphicsTriangulation extends GraphicsObject {
    private static final Triangle boundingTriangle = new Triangle(
            new Point(10000, 10000),
            new Point(10000, -10000),
            new Point(-10000, 10000));

    public GraphicsTriangulation(PointSet shape) {
        super(shape);
    }

    //sorry, do not look here. write-only code
    @Override
    public void paint(GraphicsScene scene) {
//        Triangulation triangulation = new Triangulation(boundingTriangle);
//        for(Point point: getShape().points()){
//            triangulation.place(point);
//        }
//
//        Set<Set<Point>> drawnEdges = new HashSet<>();
//
//        for (Triangle triangle: triangulation){
//            for(Line edge: triangle.getEdges()){
//                Set<Point> oneEdge = new HashSet<>(2);
//                    oneEdge.add(edge.start());
//                    oneEdge.add(edge.end());
//                if (!drawnEdges.contains(oneEdge)) {
//                    BresenhamLine.Drawer.draw(scene, edge);
//                    drawnEdges.add(oneEdge);
//                }
//            }
//        }
        Set<Pnt> boundingBox = new HashSet<Pnt>(){{
            add(new Pnt(10000, 10000));
            add(new Pnt(10000, -10000));
            add(new Pnt(-10000, 10000));
        }};
        delaunay.Triangulation triangulation = new delaunay.Triangulation(new delaunay.Triangle(boundingBox));

        for(Point point: getShape().points()){
            triangulation.delaunayPlace(new Pnt(point.x(), point.y()));
        }

        Set<Set<Point>> drawnEdges = new HashSet<>();

        for(delaunay.Triangle triangle: triangulation){
            if(triangle.containsAny(boundingBox)){
                continue;
            }

            Pnt pnt1 = triangle.get(0);
            Point p1 = new Point(pnt1.coord(0), pnt1.coord(1));

            Pnt pnt2 = triangle.get(1);
            Point p2 = new Point(pnt2.coord(0), pnt2.coord(1));

            Pnt pnt3 = triangle.get(2);
            Point p3 = new Point(pnt3.coord(0), pnt3.coord(1));


            Line line1 = new Line(p1, p2);
            Set<Point> edge1 = new HashSet<>(2);
            edge1.add(p1);
            edge1.add(p2);

            Line line2 = new Line(p2, p3);
            Set<Point> edge2 = new HashSet<>(2);
            edge2.add(p2);
            edge2.add(p3);

            Line line3 = new Line(p3, p1);
            Set<Point> edge3 = new HashSet<>(2);
            edge3.add(p3);
            edge3.add(p1);


            if(!drawnEdges.contains(edge1)){
                draw(scene, line1);
                drawnEdges.add(edge1);
            }
            if(!drawnEdges.contains(edge2)){
                draw(scene, line2);
                drawnEdges.add(edge2);
            }
            if(!drawnEdges.contains(edge3)){
                draw(scene, line3);
                drawnEdges.add(edge3);
            }
        }
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }

    @Override
    public PointSet getShape() {
        return (PointSet) super.getShape();
    }
}
