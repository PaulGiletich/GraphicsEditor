package com.pgiletich.graphics.scene.object.line;

import com.pgiletich.graphics.debugger.Debugger;
import com.pgiletich.graphics.model.Line;
import com.pgiletich.graphics.scene.GraphicsScene;
import com.pgiletich.graphics.util.MathUtil;

public class CDALine extends AbstractLine {

    public CDALine(Line line) {
        super(line);
    }

    @Override
    public void paint(GraphicsScene scene) {
        Drawer.draw(scene, getShape());
    }

    public static class Drawer{

        public static void draw(GraphicsScene scene, Line line){
            Debugger debugger = Debugger.getDebugger();

            int length = (int) Math.max( // Getting maximum length
                    Math.abs(line.start().getX() - line.end().getX()),
                    Math.abs(line.start().getY() - line.end().getY()));
            float dx = (float) ((line.end().getX() - line.start().getX()) / (float)length); // values, by which x and y change each step
            float dy = (float) ((line.end().getY() - line.start().getY()) / (float)length);
            int i = 0;
            float x = (float) (line.start().getX() + MathUtil.sign(dx) * 0.5f);
            float y = (float) (line.start().getY() + MathUtil.sign(dy) * 0.5f);

            while(i++ < length){
                if(!debugger.hasNextStep()){
                    scene.setFillAlpha(0.1f);
                }
                scene.fillPixel((int) x, (int) y);  // filling pixel
                x+=dx;
                y+=dy;
            }
        }
    }
}
