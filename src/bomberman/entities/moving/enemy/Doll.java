package bomberman.entities.moving.enemy;

import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

import java.awt.*;

/**
 * Move through Soft Blocks
 * Don't chase after Bomberman (dumb)
 * It takes 1 hit to defeat and yields a score of 400 points.
 */
public class Doll extends Enemy {
    public Doll(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.doll_left1.getFxImage();
        super.gamePlay = gamePlay;
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

    }

    @Override
    protected boolean canMove(int x, int y) {
        return false;
    }
}
