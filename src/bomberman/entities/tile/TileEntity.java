package bomberman.entities.tile;

import bomberman.entities.Entity;
import bomberman.entities.moving.Bomber;
import bomberman.entities.moving.MovingEntity;
import bomberman.entities.moving.enemy.Enemy;
import bomberman.managers.MapManager;

/**
 * Class dành cho các đối tượng đứng yên. Liên quan đến toạ độ trong map
 */
public abstract class TileEntity extends Entity {

    public TileEntity(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        mapManager.addTileEntity(this);
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        return true;
    }

    @Override
    public boolean handleOtherEnemyCollision(Enemy enemy) {
        return true;
    }

    /**
     * Check if an entity can walk through this tile entity.
     *
     * @param movingEntity the movingEntity needs to be checked.
     * @return true if the moving entity can walk through this tile.
     */
    public boolean allowWalkThrough(MovingEntity movingEntity) {
        return true;
    }
}
