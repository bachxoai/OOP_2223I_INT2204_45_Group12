package bomberman.entities.moving.enemy;

import bomberman.entities.Entity;
import bomberman.entities.tile.Brick;
import bomberman.entities.tile.Grass;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * It moves really slow, making Doria the slowest, but it can move through Soft Blocks.
 * It appears cyan-colored, just as the Onils are.
 * Doria is very smart, it will commonly attempt to chase Bomberman, and it can evade bombs.
 * It takes 1 hit to defeat and yields a score of 1000 points.
 */
public class Kondoria extends Enemy {
    BomberFinder bomberFinder;
    private static final int LENGTH_TO_CHASE_BOMBER = 20;

    /**
     * Constructor.
     *
     * @param xUnit         position x in map.
     * @param yUnit         position y in map.
     * @param mapManager    the MapManager to initialize.
     */
    public Kondoria(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        img = Sprite.kondoria_left1.getFxImage();
        velocity = 0.5;

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

        canWalkThroughBomb = false;
        canWalkThroughBrick = true;
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
