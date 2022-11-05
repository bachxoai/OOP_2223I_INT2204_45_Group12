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
    public boolean handleOtherBomberCollision(Bomber bomber) {
        SoundEffect.playSE(SoundEffect.collectingItem);
        mapManager.getGamePlay().getMapManager().getTilesAt(getXUnit(), getYUnit()).remove(this);
        return true;
    }
}
