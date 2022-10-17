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
    private void setDirection() {
        movingDelayTimeLeft--;
        if (movingDelayTimeLeft == 0) {
            Random random = new Random();

            //Chọn bừa 1 số từ {0, 1, 2, 3} tương ứng với hướng đi {up, down, left, right}
            int i = random.nextInt(4);

            //Nếu hướng đang chọn bị chặn thì chọn một hướng khác cho tới khi chọn được hướng không bị chặn.
            while (CollisionChecker.getCollisionAround(this, gamePlay).charAt(i) == '1') {
                i++;
                if (i > 3) {
                    i = 0;
                }
            }

            if (i == 0) {
                state = UP_STATE;
            } else if (i == 1) {
                state = DOWN_STATE;
            } else if (i == 2) {
                state = LEFT_STATE;
            } else {
                state = RIGHT_STATE;
            }
            movingDelayTimeLeft = MOVING_DELAY_TIME;
        }

    }

    public void update() {
        super.update();

        if (state == DEAD_STATE) {
            return;
        }

        setDirection();

        animation(state);

        futureCollision = CollisionChecker.NULL_COLLISION;
        CollisionChecker.checkTileEntity(this, gamePlay);
        //Nếu nhân vật không bị kẹt tường thì thay đổi vị trí theo hướng (direction)
        if (futureCollision != CollisionChecker.WALL_COLLISION && futureCollision != CollisionChecker.BRICK_COLLISION
                && futureCollision != CollisionChecker.BOMB_COLLISION) {
            switch (state) {
                case UP_STATE: {
                    y -= velocity;
                    break;
                }
                case DOWN_STATE: {
                    y += velocity;
                    break;
                }
                case LEFT_STATE: {
                    x -= velocity;
                    break;
                }
                case RIGHT_STATE: {
                    x += velocity;
                    break;
                }
            }
        } else {
            //Nếu hướng đi của nhân vật bị kẹt thì ra đảo ngược hướng đi
            reverseState();
        }
    }

    /**
     * Hàm đảo ngược hướng đi
     */
    public void reverseState() {
        switch (state) {
            case UP_STATE: {
                state = DOWN_STATE;
                break;
            }
            case DOWN_STATE: {
                state = UP_STATE;
                break;
            }
            case LEFT_STATE: {
                state = RIGHT_STATE;
                break;
            }
            case RIGHT_STATE: {
                state = LEFT_STATE;
                break;
            }
        }
    }
}
