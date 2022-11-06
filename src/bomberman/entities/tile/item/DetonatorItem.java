package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * Don't know yet :)))
 */
public class DetonatorItem extends Item {
    public DetonatorItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_detonator.getFxImage();
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        //Modify here
        super.handleOtherBomberCollision(bomber);
        return true;
    }
}
