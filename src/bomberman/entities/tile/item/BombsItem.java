package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;
import bomberman.screen.levelscreen.InformationPane;

/**
 * BombsItem: Increase Bomber's Bombnums.
 */
public class BombsItem extends Item {
    public BombsItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_bombs.getFxImage();
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        super.handleOtherBomberCollision(bomber);
        bomber.setBombNums(bomber.getBombNums() + 1);
        mapManager.getGamePlay().getContainedLevelScreen().setBomberStat(InformationPane.BOMB_NO, bomber.getBombNums());
        return true;
    }
}
