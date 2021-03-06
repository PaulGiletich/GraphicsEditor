package com.pgiletich.graphics.scene.object.line;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.scene.GraphicsScene;

public class BresenhamLine extends AbstractLine {

    public BresenhamLine(Line line) {
        super(line);
    }

    @Override
    public void paint(GraphicsScene scene) {
        Drawer.draw(scene, getShape());
    }

    public static class Drawer {

        public static void draw(GraphicsScene scene, Line line) {
            Debugger debugger = Debugger.getDebugger();

            int x0 = (int) line.start().x();
            int x1 = (int) line.end().x();
            int y0 = (int) line.start().y();
            int y1 = (int) line.end().y();

            int dX = Math.abs(x0 - x1);
            int dY = Math.abs(y0 - y1);

            if(dX == 0 && dY == 0){ //if line has no length, return;
                return;
            }

            int stepX = x0 > x1 ? -1 : 1;  // values, by which x and y change each step
            int stepY = y0 > y1 ? -1 : 1;

            int error = dX - dY; // error value, which appends each step
            int x = x0;
            int y = y0;
            while(x != x1 + stepX && y != y1 + stepY){  // main painting cycle
                if(!debugger.hasNextStep()){
                    scene.setFillAlpha(0.1f);
                }
                scene.fillPixel(x, y);  //filling pixel
                if(error*2 > -dY){  //recalculating error value
                    error -= dY;
                    x += stepX;
                }
                if(error*2 < dX){
                    error += dX;
                    y += stepY;
                }
            }
        }
    }
}
