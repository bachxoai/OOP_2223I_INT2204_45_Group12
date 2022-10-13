package bomberman.entities.tileentities.Item;

import bomberman.entities.DynamicEntity;
import bomberman.managers.GamePlay;
import bomberman.entities.tileentities.TileEntity;

//Class dành cho các vật phẩm nâng cấp
public abstract class Item extends TileEntity implements DynamicEntity {

    public Item(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
    }
}
