package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;
import bomberman.screen.levelscreen.InformationPane;

public class BombsItem extends Item {
    public BombsItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_bombs.getFxImage();
    }

    public boolean handleOtherBomberCollision(Bomber bomber) {
        bomber.setBombNums(bomber.getBombNums() + 1);
        mapManager.getGamePlay().getContainedLevelScreen().setBomberStat(InformationPane.BOMBNO, bomber.getBombNums());
        super.handleOtherBomberCollision(bomber);
        return true;
    }
}
