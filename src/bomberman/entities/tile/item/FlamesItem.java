package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;
import bomberman.screen.levelscreen.InformationPane;

/**
 * FlamesItem: Increase Bomber's flame range.
 */
public class FlamesItem extends Item {
    public FlamesItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_flames.getFxImage();
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        super.handleOtherBomberCollision(bomber);
        bomber.setFlameRange(bomber.getFlameRange() + 1);
        mapManager.getGamePlay().getContainedLevelScreen().setBomberStat(InformationPane.FLAME_RANGE, bomber.getFlameRange());
        return true;
    }
}
