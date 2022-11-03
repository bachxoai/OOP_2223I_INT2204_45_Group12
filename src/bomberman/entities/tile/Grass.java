package bomberman.entities.tile;

import bomberman.entities.moving.Bomber;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

//Grass là một vật thể đứng yên nên kế thừa TileEntity
public class Grass extends TileEntity {

    public Grass(int x, int y, MapManager mapManager) {
        super(x, y, mapManager);
        img = Sprite.grass.getFxImage();
    }

    @Override
    public boolean handleEntityCollision(Bomber bomber) {
        return true;
    }
}
