package bomberman.entities.moving.enemy;

import bomberman.graphics.Sprite;
import bomberman.managers.GamePlay;

import java.awt.*;

/**
 * It moves really slow, making Doria the slowest, but it can move through Soft Blocks.
 * It appears cyan-colored, just as the Onils are.
 * Doria is very smart, it will commonly attempt to chase Bomberman, and it can evade bombs.
 *  It takes 1 hit to defeat and yields a score of 1000 points.
 */
public class Kondoria extends Enemy {
    public Kondoria(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.kondoria_left1.getFxImage();
        super.gamePlay = gamePlay;
        velocity = 2;

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
    }

    @Override
    protected void setDirection() {

    }

    @Override
    protected boolean canMove(int x, int y) {
        return false;
    }
}
