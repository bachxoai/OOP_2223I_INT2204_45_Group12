package bomberman.entities.moving.enemy;

import bomberman.graphics.Sprite;
import bomberman.managers.GamePlay;

import java.awt.*;

/**
 * Đuổi theo bomberman nếu ở gần
 * Be associated with a Soft Block Pass power-up => appear when the stage Exit containing the power-up is bombed
 *      or destroyed
 */
public class Minvo extends Enemy {
    public Minvo(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.minvo_left1.getFxImage();
        super.gamePlay = gamePlay;
        velocity = 2;

        Sprite[] right = new Sprite[3];
        right[0] = Sprite.minvo_right1;
        right[1] = Sprite.minvo_right2;
        right[2] = Sprite.minvo_right3;
        Sprite[] left = new Sprite[3];
        left[0] = Sprite.minvo_left1;
        left[1] = Sprite.minvo_left2;
        left[2] = Sprite.minvo_left3;
        Sprite[] dead = new Sprite[1];
        dead[0] = Sprite.minvo_dead;
        setSprite(left, right, left, right, dead);
    }
}
