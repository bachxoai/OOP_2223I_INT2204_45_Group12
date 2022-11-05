package bomberman.entities.tile;

import bomberman.entities.moving.Bomber;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class Portal extends TileEntity {
    public Portal(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.portal.getFxImage();
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        return true;
    }
}
