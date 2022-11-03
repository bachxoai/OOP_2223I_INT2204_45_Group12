package bomberman.entities.tile;

import bomberman.entities.moving.Bomber;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class Wall extends TileEntity {

    public Wall(int x, int y, MapManager mapManager) {
        super(x, y, mapManager);
        img = Sprite.wall.getFxImage();
    }

    @Override
    public boolean handleEntityCollision(Bomber bomber) {
        return false;
    }
}
