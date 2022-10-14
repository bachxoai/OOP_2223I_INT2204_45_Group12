package bomberman.entities.tile.item;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class FlamesItem extends Item{
    public FlamesItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_flames.getFxImage();
    }

    @Override
    public void update() {

    }
}
