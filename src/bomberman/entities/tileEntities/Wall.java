package bomberman.entities.tileEntities;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class Wall extends TileEntity {

    public Wall(int x, int y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        img = Sprite.wall.getFxImage();
        collision = 1; //Tường không cho người chơi đi qua
        gamePlay.addStillObject(this);
    }

    @Override
    public void update() {

    }
}
