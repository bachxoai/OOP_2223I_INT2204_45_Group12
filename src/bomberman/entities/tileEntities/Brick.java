package bomberman.entities.tileEntities;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;
//Class cho gạch
public class Brick extends TileEntity{
    public Brick(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.brick.getFxImage();
        collision = 1; //Không cho người chơi đi qua
    }
    public void update() {

    }
}
