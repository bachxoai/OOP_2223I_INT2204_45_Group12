package bomberman.entities.tile.item;

import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;
import bomberman.managers.SoundEffect;

public class BombsItem extends Item {
    public BombsItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_bombs.getFxImage();
    }
}
