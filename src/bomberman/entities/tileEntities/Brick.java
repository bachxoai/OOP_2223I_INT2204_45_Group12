package bomberman.entities.tileEntities;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;
//Class cho gạch
public class Brick extends TileEntity{
    public Brick(int xUnit, int yUnit, ClassicMap map) {
        super(xUnit, yUnit, map);
        img = Sprite.brick.getFxImage();
        collision = 1; //Không cho người chơi đi qua
    }
    public void update() {

    }
}
