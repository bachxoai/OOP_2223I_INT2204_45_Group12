package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.MapManager;
import bomberman.entities.tile.TileEntity;
import bomberman.managers.SoundEffect;

//Class dành cho các vật phẩm nâng cấp
public abstract class Item extends TileEntity {
    public Item(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
    }

    @Override
    public void handleBomberCollision(Bomber bomber) {
        super.handleBomberCollision(bomber);
        mapManager.removeTile(this);
        if(SoundEffect.hasSoundEffect) {
            SoundEffect.playSE(SoundEffect.collectingItem);
        }
    }
}
