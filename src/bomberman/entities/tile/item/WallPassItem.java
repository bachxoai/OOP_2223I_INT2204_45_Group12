package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class WallPassItem extends Item {
    public WallPassItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_wallpass.getFxImage();
    }

    public boolean handleOtherBomberCollision(Bomber bomber) {
        super.handleOtherBomberCollision(bomber);
        bomber.setCanWalkThroughBrick(true);
        return true;
    }
}
