package bomberman.entities.tile.item;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class SpeedItem extends Item{
    public SpeedItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_speed.getFxImage();
    }

    @Override
    public void update() {

    }
}
