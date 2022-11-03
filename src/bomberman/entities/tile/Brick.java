package bomberman.entities.tile;

import bomberman.entities.DynamicEntity;
import bomberman.entities.moving.Bomber;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

//Class cho gạch
public class Brick extends TileEntity {
    public Brick(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.brick.getFxImage();
    }

    public boolean handleEntityCollision(Bomber bomber) {
        return false;
    }
}
