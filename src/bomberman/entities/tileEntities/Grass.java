package bomberman.entities.tileEntities;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

//Grass là một vật thể đứng yên nên kế thừa TileEntity
public class Grass extends TileEntity {

    public Grass(int x, int y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        img = Sprite.grass.getFxImage();
        collision = 0; //Cỏ cho người chơi đi qua
        gamePlay.getGrassObjects().add(this);
    }

    @Override
    public void update() {

    }
}
