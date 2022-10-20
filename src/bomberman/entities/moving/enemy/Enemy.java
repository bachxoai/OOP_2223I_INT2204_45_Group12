package bomberman.entities.moving.enemy;

import bomberman.entities.moving.MovingEntity;
import bomberman.graphics.Sprite;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;

public abstract class Enemy extends MovingEntity {

    //Thời gian cho 1 hướng di chuyển (đơn vị là frame, 1s = 60 frame)
    protected final int MOVING_DELAY_TIME = 64;

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
            if (inABlock()) {
                setDirection();
            }
            animation(state);
            futureCollision = CollisionChecker.NULL_COLLISION;
            CollisionChecker.checkTileEntity(this, gamePlay);

            move();
        } else {
            handleDeadState();
        }
    }

    protected abstract void setDirection();

    public void moveRandomly() {
        while (true) {
            if (state == LEFT_STATE) {
                if (canMove(getXUnit() - 1, getYUnit())) {
                    return;
                } else {
                    state = (state + 1) % 4 + 2;
                }
            }
            if (state == DOWN_STATE) {
                if (canMove(getXUnit(), getYUnit() + 1)) {
                    return;
                } else {
                    state = (state + 1) % 4 + 2;
                }
            }
            if (state == RIGHT_STATE) {
                if (canMove(getXUnit() + 1, getYUnit())) {
                    return;
                } else {
                    state = (state + 1) % 4 + 2;
                }
            }
            if (state == UP_STATE) {
                if (canMove(getXUnit(), getYUnit() - 1)) {
                    return;
                } else {
                    state = (state + 1) % 4 + 2;
                }
            }
        }
    }

    protected abstract boolean canMove(int x, int y);

    protected boolean inABlock() {
        return getX() == getXUnit() * Sprite.SCALED_SIZE && getY() == getYUnit() * Sprite.SCALED_SIZE;
    }
}
