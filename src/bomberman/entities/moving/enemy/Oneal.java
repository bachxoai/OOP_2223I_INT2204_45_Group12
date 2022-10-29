package bomberman.entities.moving.enemy;

import bomberman.entities.Entity;
import bomberman.entities.tile.Grass;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

import java.util.ArrayList;

/**
 * Oneal moves quickly and randomly.
 * It will move toward Bomberman when he is nearby. no
 * It is not likely to get stuck in walls and can be incredibly troublesome.
 * It takes 1 hit to defeat and yields a score of 200 points
 */
public class Oneal extends Enemy {
    BomberFinder bomberFinder;
    private static final int LENGTH_TO_CHASE_BOMBER = 4;
    public Oneal(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.oneal_left1.getFxImage();
        super.gamePlay = gamePlay;
        velocity = 1;

        // Create sprite.
        Sprite[] right = new Sprite[3];
        right[0] = Sprite.oneal_right1;
        right[1] = Sprite.oneal_right2;
        right[2] = Sprite.oneal_right3;
        Sprite[] left = new Sprite[3];
        left[0] = Sprite.oneal_left1;
        left[1] = Sprite.oneal_left2;
        left[2] = Sprite.oneal_left3;
        Sprite[] dead = new Sprite[1];
        dead[0] = Sprite.oneal_dead;
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

    protected boolean canMove(int x, int y) {
        Entity e = gamePlay.getMapManager().getTopTileAt(x, y);
        return e instanceof Grass;
    }
}
