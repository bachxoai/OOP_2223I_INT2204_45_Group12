package bomberman.entities.tileEntities.Item;

import bomberman.ScreenController.ClassicMap;
import bomberman.entities.tileEntities.TileEntity;

//Class dành cho các vật phẩm nâng cấp
public class Item extends TileEntity {
//    public Item(int xUnit, int yUnit, Image img, Map1 map) {
//        super(xUnit, yUnit, img, map);
//    }
    public Item(int xUnit, int yUnit, ClassicMap map) {
        super(xUnit, yUnit, map);
    }
}
