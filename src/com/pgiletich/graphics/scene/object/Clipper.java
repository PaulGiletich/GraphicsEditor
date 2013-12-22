package com.pgiletich.graphics.scene.object;

import com.pgiletich.graphics.model.Line;

public interface Clipper {
    boolean clip(Line line);
}
