package bomberman.entities.moving.enemy;

import bomberman.entities.moving.MovingEntity;
import bomberman.entities.tile.Brick;
import bomberman.entities.tile.bomb.Explosion;
import bomberman.graphics.Sprite;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.managers.MapManager;
import bomberman.managers.SoundEffect;

public abstract class Enemy extends MovingEntity {

    public Enemy(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        mapManager.addEnemies(this);
    }
    @Override
    public void update() {
        if (isAlive) {
            CollisionChecker.checkTileStable(this, mapManager);

            if (presentCollision instanceof Explosion) {
                state = DEAD_STATE;
                isAlive = false;
                if(SoundEffect.hasSoundEffect) {
                    SoundEffect.playSE(SoundEffect.enemyDeath);
                }
                return;
            }
            if (inABlock()) {
                setDirection();
            }
            animation(state);
            move();
        } else {
            handleDeadState();
        }
    }

    protected abstract void setDirection();

    public void changeDirWhenBlockedRandomly() {
        if (canMove(getXUnit() - 1, getYUnit()) && state == LEFT_STATE
                || canMove(getXUnit() + 1, getYUnit()) && state == RIGHT_STATE
                || canMove(getXUnit(), getYUnit() - 1) && state == UP_STATE
                || canMove(getXUnit(), getYUnit() + 1) && state == DOWN_STATE) {
            return;
        }
        changeDirRandomly();
    }

    protected void changeDirRandomly() {
        if (state == NORMAL_STATE) {
            state = LEFT_STATE;
        }
        int max = 5;
        int min = 2;
        state = (int) (Math.random() * (max - min + 1)) + min;
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

    protected boolean stuck() {
        return !canMove(getXUnit() + 1, getYUnit())
                && !canMove(getXUnit() - 1, getYUnit())
                && !canMove(getXUnit(), getYUnit() - 1)
                && !canMove(getXUnit(), getYUnit() + 1);
    }
}
