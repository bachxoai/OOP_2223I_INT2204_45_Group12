package bomberman.entities.tile;

import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

//Grass là một vật thể đứng yên nên kế thừa TileEntity
public class Grass extends TileEntity {

    public Grass(int x, int y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        gamePlay.getMapManager().addTileEntity(this);
        img = Sprite.grass.getFxImage();
        collision = CollisionChecker.NULL_COLLISION;
    }
}
