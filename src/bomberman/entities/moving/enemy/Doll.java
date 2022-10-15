package bomberman.entities.moving.enemy;

import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

import java.awt.*;

/**
 * Move through Soft Blocks
 * Don't chase after Bomberman
 * Have wall-pass abilities
 * Be associated with a Bomb Pass power-up => appear when the stage Exit containing the power-up is bombed
 *      or destroyed.
 */
public class Doll extends Enemy {
    public Doll(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.doll_left1.getFxImage();
        solidArea = new Rectangle(0,0,32,32);
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
        animationDeadTime = -1;
    }
}
