package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class WallPassItem extends Item {
    public WallPassItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_wallpass.getFxImage();
    }

    public boolean handleEntityCollision(Bomber bomber) {
        bomber.setCanWalkThroughBrick(true);
        super.handleEntityCollision(bomber);
        return true;
    }
}
