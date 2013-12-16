package com.pgiletich.graphics.ui.instrument;

import com.pgiletich.graphics.model.Cube;
import com.pgiletich.graphics.model.Point;
import com.pgiletich.graphics.scene.object.GraphicsCube;
import com.pgiletich.graphics.ui.MainWindow;
import com.pgiletich.graphics.util.TransformUtil;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class CubeInstrument extends InstrumentStrategy {
    private GraphicsCube cube;
    private java.awt.Point lastMousePos;

    public CubeInstrument(MainWindow window) {
        super(window);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePos = e.getPoint();
        if((e.getModifiers() & (InputEvent.CTRL_MASK | InputEvent.ALT_MASK | InputEvent.SHIFT_MASK)) != 0){
            return;
        }
        java.awt.Point p = getScene().toSceneCoords(e.getPoint());
        cube = new GraphicsCube(new Cube(new Point(p.x, p.y), 180));
        getScene().addObject(cube);
        getScene().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK){
            Cube cubeModel = cube.getShape();
            for(int i  = 0; i < cubeModel.points.length; i++){
                Point p = cubeModel.points[i];
                Point xRotated  = TransformUtil.rotate(p, TransformUtil.Axis.X, lastMousePos.x - e.getX());
                Point xyRotated = TransformUtil.rotate(xRotated, TransformUtil.Axis.Y, e.getY() - lastMousePos.y);
                cubeModel.points[i] = xyRotated;
            }
        }
        if((e.getModifiers() & InputEvent.ALT_MASK) == InputEvent.ALT_MASK){
            Cube cubeModel = cube.getShape();
            for(int i  = 0; i < cubeModel.points.length; i++){
                double sv = 1 + (lastMousePos.y - e.getY()) / 100.0;
                cubeModel.points[i] = TransformUtil.scale(cubeModel.points[i], sv, sv, sv);
            }
        }
        if((e.getModifiers() & InputEvent.SHIFT_MASK) == InputEvent.SHIFT_MASK){
            cube.getShape().pos.setY(cube.getShape().pos.y() + e.getY() - lastMousePos.y);
            cube.getShape().pos.setX(cube.getShape().pos.x() + e.getX() - lastMousePos.x);
        }
        getScene().repaint();
        lastMousePos = e.getPoint();
    }
}
