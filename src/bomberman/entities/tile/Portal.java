package bomberman.entities.tile;

import bomberman.entities.moving.Bomber;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * Portal.
 * When bomber kills all enemies and walk on this portal, the game end.
 */
public class Portal extends TileEntity {
    public Portal(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.portal.getFxImage();
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        if (getMapManager().getEnemies().size() == 0) {
            try {
                getMapManager().getGamePlay().getContainedLevelScreen().gameOver();
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public boolean canPlaceBomb() {
        return true;
    }
}
