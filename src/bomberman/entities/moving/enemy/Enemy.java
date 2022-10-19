package bomberman.entities.moving.enemy;

import bomberman.entities.moving.MovingEntity;
import bomberman.graphics.Sprite;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;

public abstract class Enemy extends MovingEntity {

    //Thời gian cho 1 hướng di chuyển (đơn vị là frame, 1s = 60 frame)
    protected final int MOVING_DELAY_TIME = 64;
    /*
    Có một điều kiện bắt buộc là MOVING_DELAY_TIME * velocity phải là bội của 32
    để Enemy đi theo đúng tọa độ hàng-cột.
     */

    protected boolean canMove = false;

    //Thời gian di chuyển còn lại theo hướng đó.
    protected int movingDelayTimeLeft;

    public Enemy(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        gamePlay.getMapManager().addEnemies(this);
    }
    @Override
    public void update() {
        if (isAlive) {
            CollisionChecker.checkTileStable(this, gamePlay);

            if (presentCollision == CollisionChecker.FLAME_COLLISION) {
                state = DEAD_STATE;
                isAlive = false;
                return;
            }

            setDirection();
            animation(state);
            move();
        } else {
            handleDeadState();
        }
    }

    protected abstract void setDirection();

    protected boolean inABlock() {
        if (getX() == getXUnit() * Sprite.SCALED_SIZE && getY() == getYUnit() * Sprite.SCALED_SIZE) {
            return true;
        }
        return false;
    }
}
