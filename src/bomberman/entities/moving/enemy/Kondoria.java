package bomberman.entities.moving.enemy;

import bomberman.entities.Entity;
import bomberman.entities.tile.Brick;
import bomberman.entities.tile.Grass;
import bomberman.graphics.Sprite;
import bomberman.managers.GamePlay;

import java.awt.*;

/**
 * It moves really slow, making Doria the slowest, but it can move through Soft Blocks.
 * It appears cyan-colored, just as the Onils are.
 * Doria is very smart, it will commonly attempt to chase Bomberman, and it can evade bombs.
 * It takes 1 hit to defeat and yields a score of 1000 points.
 */
public class Kondoria extends Enemy {
    BomberFinder bomberFinder;
    private static final int LENGTH_TO_CHASE_BOMBER = 20;

    public Kondoria(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.kondoria_left1.getFxImage();
        super.gamePlay = gamePlay;
        velocity = 1;

        Sprite[] right = new Sprite[3];
        right[0] = Sprite.kondoria_right1;
        right[1] = Sprite.kondoria_right2;
        right[2] = Sprite.kondoria_right3;
        Sprite[] left = new Sprite[3];
        left[0] = Sprite.kondoria_left1;
        left[1] = Sprite.kondoria_left2;
        left[2] = Sprite.kondoria_left3;
        Sprite[] dead = new Sprite[1];
        dead[0] = Sprite.kondoria_dead;
        setSprite(left, right, left, right, dead);

        bomberFinder = new BomberFinder(this, LENGTH_TO_CHASE_BOMBER);

        state = NORMAL_STATE;
    }

    @Override
    protected void setDirection() {
        bomberFinder.setFinalDirection(state);
        bomberFinder.updatedVision();
        bomberFinder.bfs();
        state = bomberFinder.getFinalDirection();
        if (!stuck()) {
            changeDirWhenBlockedRandomly();
        } else {
            state = NORMAL_STATE;
        }
    }

    @Override
    protected boolean canMove(int x, int y) {
        Entity e = gamePlay.getMapManager().getTopTileAt(x, y);
        return e instanceof Grass
                || e instanceof Brick;
    }
}
