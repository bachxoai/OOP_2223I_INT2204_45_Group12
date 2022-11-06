package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * BombPassItem: Allow bomber to walk through bombs.
 */
public class BombPassItem extends Item {
    public BombPassItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_bombpass.getFxImage();
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        super.handleOtherBomberCollision(bomber);
        bomber.setCanWalkThroughBomb(true);
        return true;
    }
}
