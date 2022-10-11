package bomberman.entities.tileEntities.Item;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class BombpassItem extends Item{

    public BombpassItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_bombs.getFxImage();
//        collision = 5;
    }


}
