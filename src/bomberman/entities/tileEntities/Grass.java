package bomberman.entities.tileEntities;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;

//Grass là một vật thể đứng yên nên kế thừa TileEntity
public class Grass extends TileEntity {

    public Grass(int x, int y, ClassicMap map) {
        super(x, y, map);
        img = Sprite.grass.getFxImage();
        collision = 0; //Cỏ cho người chơi đi qua
    }

    @Override
    public void update() {

    }
}
