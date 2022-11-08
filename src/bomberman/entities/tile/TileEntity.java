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

    /**
     * Check if an entity can walk through this tile entity.
     *
     * @param movingEntity the movingEntity needs to be checked.
     * @return true if the moving entity can walk through this tile.
     */
    public boolean allowWalkThrough(MovingEntity movingEntity) {
        return true;
    }

    /**
     * Check this tile entity can be placed bombs or not.
     *
     * @return true if bomb can be placed on this tile, false otherwise.
     */
    public boolean canPlaceBomb() {
        return false;
    }

    /**
     * This method is called when a flame reach this Tile Entity.
     * The effect is handled in this method and check if the flame can pass it or not.
     *
     * @return true if this tile does not block flame, false otherwise.
     */
    public boolean blockFlame() {
        return false;
    }
}
