package bomberman.entities.tile;

import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * Grass.
 */
public class Grass extends TileEntity {
    public Grass(int x, int y, MapManager mapManager) {
        super(x, y, mapManager);
        img = Sprite.grass.getFxImage();
    }
}
