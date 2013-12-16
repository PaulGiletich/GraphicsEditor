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
                    Math.abs(line.start().x() - line.end().x()),
                    Math.abs(line.start().y() - line.end().y()));
            float dx = (float) ((line.end().x() - line.start().x()) / (float)length); // values, by which x and y change each step
            float dy = (float) ((line.end().y() - line.start().y()) / (float)length);
            int i = 0;
            float x = (float) (line.start().x() + MathUtil.sign(dx) * 0.5f);
            float y = (float) (line.start().y() + MathUtil.sign(dy) * 0.5f);

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
