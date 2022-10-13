package bomberman.entities.tile;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class Wall extends TileEntity {

    public Wall(int x, int y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        gamePlay.getMapManager().addTileEntity(this);
        img = Sprite.wall.getFxImage();
        collision = 1; //Tường không cho người chơi đi qua
    }
}
