package bomberman.entities.tileEntities;

import bomberman.ScreenController.Map1;
import bomberman.graphics.Sprite;

public class Portal extends TileEntity {
    public Portal(int xUnit, int yUnit, Map1 map) {
        super(xUnit, yUnit, map);
        img = Sprite.portal.getFxImage();
    }
}
