package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class BombPassItem extends Item {

    public BombPassItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_bombpass.getFxImage();
    }

    @Override
    public void handleBomberCollision(Bomber bomber) {
        super.handleBomberCollision(bomber);
        bomber.setCanWalkThroughBomb(true);
    }
}
