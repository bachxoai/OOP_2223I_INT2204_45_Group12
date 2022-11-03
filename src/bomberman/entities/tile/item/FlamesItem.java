package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;
import bomberman.screen.levelscreen.InformationPane;

public class FlamesItem extends Item {
    public FlamesItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_flames.getFxImage();
    }

    public boolean handleEntityCollision(Bomber bomber) {
        bomber.setFlameRange(bomber.getFlameRange() + 1);
        mapManager.getGamePlay().getContainedLevelScreen().setBomberStat(InformationPane.FLAME_RANGE, bomber.getFlameRange());
        super.handleEntityCollision(bomber);
        return true;
    }
}
