package bomberman.entities.tile.item;

import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class WallPassItem extends Item {
    public WallPassItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_wallpass.getFxImage();
        collision = CollisionChecker.WALL_PASS_ITEM_COLLISION;
    }
}
