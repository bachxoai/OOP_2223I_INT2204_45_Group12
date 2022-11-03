package bomberman.entities.tile;

import bomberman.entities.moving.Bomber;
import bomberman.managers.GamePlay;
import bomberman.entities.Entity;
import bomberman.managers.MapManager;
import javafx.scene.canvas.GraphicsContext;

/**
 * Class dành cho các đối tượng đứng yên. Liên quan đến toạ độ trong map
 */
public abstract class TileEntity extends Entity {

    public TileEntity(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        mapManager.addTileEntity(this);
    }

    public abstract boolean handleEntityCollision(Bomber bomber);
}
