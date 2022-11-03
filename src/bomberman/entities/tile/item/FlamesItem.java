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

    @Override
    public void handleBomberCollision(Bomber bomber) {
        super.handleBomberCollision(bomber);
        int newFlameRange = bomber.getFlameRange() + 1;
        bomber.setFlameRange(newFlameRange);
        mapManager.getGamePlay().getContainedLevelScreen().setBomberStat(InformationPane.FLAME_RANGE, newFlameRange);
    }
}
