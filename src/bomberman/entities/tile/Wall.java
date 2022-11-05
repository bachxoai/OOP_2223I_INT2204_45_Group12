package bomberman.entities.tile;

import bomberman.entities.moving.Bomber;
import bomberman.entities.moving.MovingEntity;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class Wall extends TileEntity {

    public Wall(int x, int y, MapManager mapManager) {
        super(x, y, mapManager);
        img = Sprite.wall.getFxImage();
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        return false;
    }

    @Override
    public boolean allowWalkThrough(MovingEntity movingEntity) {
        return false;
    }
}