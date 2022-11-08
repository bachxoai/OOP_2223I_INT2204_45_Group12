package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.entities.tile.TileEntity;
import bomberman.managers.MapManager;
import bomberman.sounds.SoundEffect;
import bomberman.sounds.SoundManager;

/**
 * Items that can have effect on bomber who walks on this item.
 */
public abstract class Item extends TileEntity {
    public Item(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        SoundManager.soundEffect.play(SoundEffect.collectingItem);
        mapManager.getGamePlay().getMapManager().getTilesAt(getXUnit(), getYUnit()).remove(this);
        return true;
    }
}
