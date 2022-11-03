package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;
import bomberman.managers.SoundEffect;
import bomberman.screen.levelscreen.InformationPane;

public class BombsItem extends Item {
    public BombsItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_bombs.getFxImage();
    }

    @Override
    public void handleBomberCollision(Bomber bomber) {
        super.handleBomberCollision(bomber);
        int newBombNums = bomber.getBombNums() + 1;
        bomber.setBombNums(newBombNums);
        mapManager.getGamePlay().getContainedLevelScreen().setBomberStat(InformationPane.BOMBNO, newBombNums);
    }
}
