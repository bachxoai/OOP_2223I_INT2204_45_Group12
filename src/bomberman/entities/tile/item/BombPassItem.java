package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class BombPassItem extends Item {

    public BombPassItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_bombpass.getFxImage();
    }

    public boolean handleOtherBomberCollision(Bomber bomber) {
        bomber.setCanWalkThroughBomb(true);
        super.handleOtherBomberCollision(bomber);
        return true;
    }
}
