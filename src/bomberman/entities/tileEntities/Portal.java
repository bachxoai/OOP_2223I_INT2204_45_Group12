package bomberman.entities.tileEntities;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;

public class Portal extends TileEntity {
    public Portal(int xUnit, int yUnit, ClassicMap map) {
        super(xUnit, yUnit, map);
        img = Sprite.portal.getFxImage();
    }
}
