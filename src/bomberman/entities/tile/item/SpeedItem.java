package bomberman.entities.tile.item;

import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class SpeedItem extends Item{
    public SpeedItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_speed.getFxImage();
        collision = CollisionChecker.SPEED_ITEM_COLLISION;
    }

    @Override
    public void update() {

    }
}
