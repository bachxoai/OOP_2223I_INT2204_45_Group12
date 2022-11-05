package bomberman.entities.tile;

import bomberman.entities.moving.Bomber;
import bomberman.entities.moving.MovingEntity;
import bomberman.entities.moving.enemy.Enemy;
import bomberman.entities.Entity;
import bomberman.managers.MapManager;

/**
 * Class dành cho các đối tượng đứng yên. Liên quan đến toạ độ trong map
 */
public abstract class TileEntity extends Entity {

    public TileEntity(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        mapManager.addTileEntity(this);
    }

    public abstract boolean handleOtherBomberCollision(Bomber bomber);

    public boolean handleOtherEnemyCollision(Enemy enemy) {
        return true;
    }

    public boolean allowWalkThrough(MovingEntity movingEntity) {
        return true;
    }
}
