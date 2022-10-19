package bomberman.entities.moving.enemy;

import bomberman.entities.moving.MovingEntity;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

/**
 * di chuyển ngẫu nhiên với vận tốc cố định.
 */
public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.balloom_left1.getFxImage();
        super.gamePlay = gamePlay;
        velocity = 1;

        Sprite[] right = new Sprite[3];
        right[0] = Sprite.balloom_right1;
        right[1] = Sprite.balloom_right2;
        right[2] = Sprite.balloom_right3;
        Sprite[] left = new Sprite[3];
        left[0] = Sprite.balloom_left1;
        left[1] = Sprite.balloom_left2;
        left[2] = Sprite.balloom_left3;
        Sprite[] dead = new Sprite[1];
        dead[0] = Sprite.balloom_dead;
        setSprite(left, right, left, right, dead);
        movingDelayTimeLeft = MOVING_DELAY_TIME;
    }

    /**
     * Hàm chọn hướng di chuyển phù hợp
     */
    protected void setDirection() {
        if (inABlock()) {
            Random random = new Random();

            //Chọn bừa 1 số từ {2, 3, 4, 5} tương ứng với STATE {UP, DOWN, LEFT, RIGHT}
            int i = random.nextInt(4) + 2;

            //Nếu hướng đang chọn bị chặn thì chọn một hướng khác cho tới khi chọn được hướng không bị chặn.
            while (CollisionChecker.getCollisionAround(this, gamePlay).charAt(i - UP_STATE) == '1') {
                i++;
                if (i > RIGHT_STATE) {
                    i = UP_STATE;
                }
            }
            state = i;
        }
    }
}
