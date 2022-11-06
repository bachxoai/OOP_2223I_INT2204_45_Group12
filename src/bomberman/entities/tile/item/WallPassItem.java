package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * WallPassItem: Allows Bomber that acquires this item walk on the brick.
 */
public class WallPassItem extends Item {
    public WallPassItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_wallpass.getFxImage();
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        super.handleOtherBomberCollision(bomber);
        bomber.setCanWalkThroughBrick(true);
        return true;
    }
}
