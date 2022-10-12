package bomberman.entities;

import javafx.scene.canvas.GraphicsContext;

public interface DynamicEntity {
    public void update();
    public void render(GraphicsContext gc);
}
