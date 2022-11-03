package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class DetonatorItem extends Item {
    public DetonatorItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_detonator.getFxImage();
    }

    public boolean handleEntityCollision(Bomber bomber) {
        //Modify here
        super.handleEntityCollision(bomber);
        return true;
    }
}
