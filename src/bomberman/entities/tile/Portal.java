package bomberman.entities.tile;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class Portal extends TileEntity {
    public Portal(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        gamePlay.getMapManager().addTileEntity(this);
        img = Sprite.portal.getFxImage();
    }
}
