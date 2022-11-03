package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class FlamePassItem extends Item {
    public FlamePassItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_flamepass.getFxImage();
    }

    public boolean handleEntityCollision(Bomber bomber) {
        bomber.setCanWalkThroughFlame(true);
        super.handleEntityCollision(bomber);
        return true;
    }
}
