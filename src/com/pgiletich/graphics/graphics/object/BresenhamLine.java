package com.pgiletich.graphics.graphics.object;

import com.pgiletich.graphics.graphics.GraphicsScene;
import com.pgiletich.graphics.model.Line;

public class BresenhamLine extends AbstractLine {

    public BresenhamLine(Line line) {
        super(line);
    }

    @Override
    public void paint(GraphicsScene scene) {
        Line line = getShape();
        int dX = Math.abs(line.start.x - line.end.x);
        int dY = Math.abs(line.start.y - line.end.y);
        int stepX = line.start.x > line.end.x ? -1 : 1;
        int stepY = line.start.y > line.end.y ? -1 : 1;

        int error = dX - dY;
        int x = line.start.x;
        int y = line.start.y;
        while(x != line.end.x + stepX && y != line.end.y + stepY){
            scene.fillPixel(x, y);
            if(error*2 > -dY){
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
