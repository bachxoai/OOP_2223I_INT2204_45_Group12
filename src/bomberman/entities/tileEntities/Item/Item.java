package bomberman.entities.tileEntities.Item;

import bomberman.managers.GamePlay;
import bomberman.entities.tileEntities.TileEntity;

//Class dành cho các vật phẩm nâng cấp
public abstract class Item extends TileEntity {
//    public Item(int xUnit, int yUnit, Image img, Map1 map) {
//        super(xUnit, yUnit, img, map);
//    }
    public Item(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        gamePlay.addStillObject(this);
    }
}
