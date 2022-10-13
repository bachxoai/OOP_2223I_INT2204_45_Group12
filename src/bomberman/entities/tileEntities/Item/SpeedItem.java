package bomberman.entities.tileentities.Item;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class SpeedItem extends Item{
    public SpeedItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_speed.getFxImage();
        collision = 4;
    }

    @Override
    public void update() {

    }
}
