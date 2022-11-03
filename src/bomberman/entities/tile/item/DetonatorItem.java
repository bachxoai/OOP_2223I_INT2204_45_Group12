package bomberman.entities.tile.item;

import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

public class DetonatorItem extends Item {
    public DetonatorItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_detonator.getFxImage();
    }
}
