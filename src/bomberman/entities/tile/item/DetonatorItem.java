package bomberman.entities.tile.item;

import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class DetonatorItem extends Item {
    public DetonatorItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_detonator.getFxImage();
        collision = CollisionChecker.DETONATOR_ITEM_COLLISION;
    }
}
