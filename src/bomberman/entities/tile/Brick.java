package bomberman.entities.tile;

import bomberman.entities.moving.Bomber;
import bomberman.entities.moving.MovingEntity;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

//Class cho gạch
public class Brick extends TileEntity {
    public Brick(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.brick.getFxImage();
    }

    public boolean handleOtherBomberCollision(Bomber bomber) {
        return false;
    }

//    @Override
    public boolean allowWalkThrough(MovingEntity movingEntity) {
        return movingEntity.isCanWalkThroughBrick();
    }
}
