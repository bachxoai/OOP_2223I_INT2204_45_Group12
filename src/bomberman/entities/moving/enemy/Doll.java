package bomberman.entities.moving.enemy;

import bomberman.entities.Entity;
import bomberman.entities.tile.Grass;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * Move fast, randomly
 * Don't chase after Bomberman (dumb)
 * It takes 1 hit to defeat and yields a score of 400 points.
 */
public class Doll extends Enemy {
    public Doll(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.doll_left1.getFxImage();
        velocity = 2;

        Sprite[] right = new Sprite[3];
        right[0] = Sprite.doll_right1;
        right[1] = Sprite.doll_right2;
        right[2] = Sprite.doll_right3;
        Sprite[] left = new Sprite[3];
        left[0] = Sprite.doll_left1;
        left[1] = Sprite.doll_left2;
        left[2] = Sprite.doll_left3;
        Sprite[] dead = new Sprite[1];
        dead[0] = Sprite.doll_dead;
        setSprite(left, right, left, right, dead);
    }

    @Override
    protected void setDirection() {
        if (notStuck())
            changeDirRandomly();
    }

    @Override
    protected boolean canMove(int x, int y) {
        Entity e = mapManager.getTopTileAt(x, y);
        return e instanceof Grass;
    }
}
