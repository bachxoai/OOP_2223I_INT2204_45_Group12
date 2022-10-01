package bomberman.entities.tileEntities;

import bomberman.ScreenController.Map1;
import bomberman.graphics.Sprite;

//Grass là một vật thể đứng yên nên kế thừa TileEntity
public class Grass extends TileEntity {

    public Grass(int x, int y, Map1 map) {
        super(x, y, map);
        img = Sprite.grass.getFxImage();
        collision = 0; //Cỏ cho người chơi đi qua
    }

    @Override
    public void update() {

    }
}
