package bomberman.entities.moving.enemy;

import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * Oneal moves quickly and randomly.
 * It will move toward Bomberman when he is nearby. no
 * It is not likely to get stuck in walls and can be incredibly troublesome.
 * It takes 1 hit to defeat and yields a score of 200 points
 */
public class Oneal extends Enemy {
    BomberFinder bomberFinder;
    private static final int LENGTH_TO_CHASE_BOMBER = 4;

    /**
     * Constructor.
     *
     * @param xUnit         position x in map.
     * @param yUnit         position y in map.
     * @param mapManager    the MapManager to initialize.
     */
    public Oneal(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.oneal_left1.getFxImage();
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

        canWalkThroughBomb = false;
        canWalkThroughBrick = false;
        canWalkThroughFlame = false;

        bomberFinder = new BomberFinder(this, LENGTH_TO_CHASE_BOMBER, mapManager);

        state = NORMAL_STATE;
    }

    @Override
    protected void setDirection() {
        bomberFinder.setFinalDirection(state);
        bomberFinder.updatedVision();
        bomberFinder.bfs();
        state = bomberFinder.getFinalDirection();
        if (notStuck()) {
            changeDirWhenBlockedRandomly();
        } else {
            state = NORMAL_STATE;
        }
    }
}
