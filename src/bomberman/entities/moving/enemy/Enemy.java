package bomberman.entities.moving.enemy;

import bomberman.entities.moving.Bomber;
import bomberman.entities.moving.MovingEntity;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * Enemies that can move and kill bomber,
 * Each enemy has its own way to move (e.g. find Bomber).
 */
public abstract class Enemy extends MovingEntity {
    public Enemy(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        mapManager.addEnemies(this);
    }

    @Override
    public void update() {
        if (isAlive) {
            updatePresentTileCollision();
            presentTileCollision.handleOtherEnemyCollision(this);
            if (inABlock()) {
                setDirection();
            }
            animation();
            move();
        } else {
            handleDeadState();
        }
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        if (!bomber.isImmortal()) {
            bomber.handleDeath();
        }
        return false;
    }

    /**
     * Hàm thực hiện khi nhân vật chết.
     */
    @Override
    protected void handleDeadState() {
        super.handleDeadState();
        if (animationDeadTime == 0) {
            mapManager.getEnemies().remove(this);
            mapManager.getMovingEntities().remove(this);
        }
    }

    protected abstract void setDirection();

    protected void changeDirWhenBlockedRandomly() {
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

    protected boolean canMove(int x, int y) {
        return getMapManager().getTopTileAt(x, y).allowWalkThrough(this);
    }

    protected boolean inABlock() {
        return getX() == getXUnit() * Sprite.SCALED_SIZE && getY() == getYUnit() * Sprite.SCALED_SIZE;
    }

    protected boolean notStuck() {
        return canMove(getXUnit() + 1, getYUnit())
                || canMove(getXUnit() - 1, getYUnit())
                || canMove(getXUnit(), getYUnit() - 1)
                || canMove(getXUnit(), getYUnit() + 1);
    }
}
