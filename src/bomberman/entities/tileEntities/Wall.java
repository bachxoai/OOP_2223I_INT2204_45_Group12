package bomberman.entities.tileEntities;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;

public class Wall extends TileEntity {

    public Wall(int x, int y, ClassicMap map) {
        super(x, y, map);
        img = Sprite.wall.getFxImage();
        collision = 1; //Tường không cho người chơi đi qua
    }

    @Override
    public void update() {

    }
}
