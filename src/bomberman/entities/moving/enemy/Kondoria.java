package bomberman.entities.moving.enemy;

import bomberman.graphics.Sprite;
import bomberman.managers.GamePlay;

import java.awt.*;

/**
 * Di chuyển chậm nhất
 * Có thể đi qua Soft Blocks
 * Đuổi theo bomberman, có khả năng tránh bom
 * Be associated with the Remote Control power-up => appear when the stage Exit
 *      containing the power-up is bombed or destroyed
 */
public class Kondoria extends Enemy {
    public Kondoria(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.kondoria_left1.getFxImage();
        solidArea = new Rectangle(0,0,32,32);
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
        animationDeadTime = -1;
    }
}
