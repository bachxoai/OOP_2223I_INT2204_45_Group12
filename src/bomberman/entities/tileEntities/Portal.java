package bomberman.entities.tileEntities;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class Portal extends TileEntity {
    public Portal(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.portal.getFxImage();
    }
}
