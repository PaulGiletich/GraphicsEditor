package com.pgiletich.graphics.scene.object.line;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.scene.GraphicsScene;

import static com.pgiletich.graphics.util.MathUtil.floatPart;
import static com.pgiletich.graphics.util.MathUtil.reverseFloatPart;

public class AntialiasedLine extends AbstractLine {
    public AntialiasedLine(Line shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        Drawer.draw(scene, getShape());
    }

    public static class Drawer {

        public static void draw(GraphicsScene scene, Line line) {
            Debugger debugger = Debugger.getDebugger();

            int x0 = (int) line.end().getX();
            int y0 = (int) line.end().getY();
            int x1 = (int) line.start().getX();
            int y1 = (int) line.start().getY();

            int dx = x1 - x0;
            int dy = y1 - y0;

            boolean steep = Math.abs(dy) > Math.abs(dx);
            if(steep){
                x0 = (int) line.start().getY();
                y0 = (int) line.start().getX();
                x1 = (int) line.end().getY();
                y1 = (int) line.end().getX();
            }
            if (x0 > x1){
                int tmp = x0;
                x0 = x1;
                x1 = tmp;
                tmp = y0;
                y0 = y1;
                y1 = tmp;
            }

            dx = x1 - x0;
            dy = y1 - y0;

            float gradient = (float)dy / dx;
            float y = y0;
            for(int x = x0; x < x1; x++, y += gradient){
                if(!debugger.hasNextStep()){
                    if(steep){
                        scene.fillPixel((int)y, x, 0.1f* reverseFloatPart(y));
                        scene.fillPixel((int)y+1, x, 0.1f* floatPart(y));
                    }
                    else{
                        scene.fillPixel(x, (int)y, 0.1f* reverseFloatPart(y));
                        scene.fillPixel(x, (int)y+1, 0.1f* floatPart(y));
                    }
                }
                else{
                    if(steep){
                        scene.fillPixel((int)y, x, reverseFloatPart(y));
                        scene.fillPixel((int)y+1, x, floatPart(y));
                    }
                    else{
                        scene.fillPixel(x, (int)y, reverseFloatPart(y));
                        scene.fillPixel(x, (int)y+1, floatPart(y));
                    }
                }
            }
        }
    }
}
