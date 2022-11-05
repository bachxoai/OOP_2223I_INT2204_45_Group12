package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class DetonatorItem extends Item {
    public DetonatorItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_detonator.getFxImage();
    }

    public boolean handleOtherBomberCollision(Bomber bomber) {
        //Modify here
        super.handleOtherBomberCollision(bomber);
        return true;
    }
}
