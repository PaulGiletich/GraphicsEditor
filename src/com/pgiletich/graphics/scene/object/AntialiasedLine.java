package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.scene.GraphicsScene;

public class AntialiasedLine extends AbstractLine {
    public AntialiasedLine(Line shape) {
        super(shape);
    }

    @Override
    public void paint(GraphicsScene scene) {
        Debugger debugger = Debugger.getDebugger();

        Line line = getShape();
        int x0 = line.end.x;
        int y0 = line.end.y;
        int x1 = line.start.x;
        int y1 = line.start.y;

        int dx = x1 - x0;
        int dy = y1 - y0;

        boolean steep = Math.abs(dy) > Math.abs(dx);
        if(steep){
            x0 = line.start.y;
            y0 = line.start.x;
            x1 = line.end.y;
            y1 = line.end.x;
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
            if(!debugger.hasNextStep()){ // TODO refactor this shit
                if(steep){
                    scene.fillPixel((int)y, x, 0.1f*reverseFloatPart(y));
                    scene.fillPixel((int)y+1, x, 0.1f*floatPart(y));
                }
                else{
                    scene.fillPixel(x, (int)y, 0.1f*reverseFloatPart(y));
                    scene.fillPixel(x, (int)y+1, 0.1f*floatPart(y));
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


    private static float floatPart(float a){
        return a - (int)a;
    }

    private static float reverseFloatPart(float a){
        return 1 - (a - (int)a);
    }
}
