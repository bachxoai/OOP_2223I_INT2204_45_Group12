package bomberman.entities.tile.item;

import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class BombPassItem extends Item {

    public BombPassItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_bombpass.getFxImage();
        collision = CollisionChecker.BOMB_PASS_ITEM_COLLISION;
    }
}