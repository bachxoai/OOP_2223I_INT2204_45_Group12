package bomberman.entities.tile.item;

import bomberman.entities.moving.Bomber;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;
import bomberman.screen.levelscreen.InformationPane;

public class SpeedItem extends Item {
    public SpeedItem(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.powerup_speed.getFxImage();
    }

    public boolean handleEntityCollision(Bomber bomber) {
        bomber.setVelocity(bomber.getVelocity() + 1);
        mapManager.getGamePlay().getContainedLevelScreen().setBomberStat(InformationPane.SPEED, bomber.getVelocity());
        super.handleEntityCollision(bomber);
        return true;
    }
}
