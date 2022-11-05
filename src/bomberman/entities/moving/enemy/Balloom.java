package bomberman.entities.moving.enemy;

import bomberman.entities.Entity;
import bomberman.entities.tile.Grass;
import bomberman.entities.tile.TileEntity;
import bomberman.managers.MapManager;
import bomberman.graphics.Sprite;

/**
 * di chuyển ngẫu nhiên với vận tốc cố định.
 */
public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.balloom_left1.getFxImage();
        velocity = 1;

        Sprite[] right = new Sprite[3];
        right[0] = Sprite.balloom_right1;
        right[1] = Sprite.balloom_right2;
        right[2] = Sprite.balloom_right3;
        Sprite[] left = new Sprite[3];
        left[0] = Sprite.balloom_left1;
        left[1] = Sprite.balloom_left2;
        left[2] = Sprite.balloom_left3;
        Sprite[] dead = new Sprite[1];
        dead[0] = Sprite.balloom_dead;
        setSprite(left, right, left, right, dead);

        state = NORMAL_STATE;
    }

    /**
     * Hàm chọn hướng di chuyển phù hợp
     */
    protected void setDirection() {
        if (notStuck()) {
            changeDirWhenBlockedRandomly();
        } else {
            state = NORMAL_STATE;
        }
    }

    protected boolean canMove(int x, int y) {
        TileEntity e = mapManager.getTopTileAt(x, y);
        return e.allowWalkThrough(this);
//        return e instanceof Grass;
    }
}
