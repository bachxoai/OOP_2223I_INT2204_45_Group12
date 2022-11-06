package bomberman.entities.tile;

import bomberman.entities.moving.MovingEntity;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * Brick Class.
 */
public class Brick extends TileEntity {
    public Brick(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.brick.getFxImage();
    }

    @Override
    public boolean allowWalkThrough(MovingEntity movingEntity) {
        return movingEntity.isCanWalkThroughBrick();
    }
}
