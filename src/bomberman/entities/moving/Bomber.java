package bomberman.entities.moving;

import bomberman.entities.Entity;
import bomberman.entities.tile.bomb.Bomb;
import bomberman.graphics.Sprite;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.screen.levelscreen.InformationPane;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;

/**
 * Class bomber.
 */
public class Bomber extends MovingEntity {
    int lives;
    int bombNums;
    int flameRange;
    boolean canWalkThroughBomb;
    boolean canWalkThroughBrick;
    boolean canWalkThroughFlame;
    boolean canDetonate;

    //Kiểm tra xem nút Up có đang được bấm hay không? Các nút còn lại tương tự.
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;

    public Bomber(int x, int y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        img = Sprite.player_down.getFxImage();
        //Thêm các Spite animation cho Bomber
        Sprite[] up = new Sprite[2];
        up[0] = Sprite.player_up_1;
        up[1] = Sprite.player_up_2;
        Sprite[] down = new Sprite[2];
        down[0] = Sprite.player_down_1;
        down[1] = Sprite.player_down_2;
        Sprite[] right = new Sprite[2];
        right[0] = Sprite.player_right_1;
        right[1] = Sprite.player_right_2;
        Sprite[] left = new Sprite[2];
        left[0] = Sprite.player_left_1;
        left[1] = Sprite.player_left_2;
        Sprite[] dead = new Sprite[3];
        dead[0] = Sprite.player_dead1;
        dead[1] = Sprite.player_dead2;
        dead[2] = Sprite.player_dead3;

        setSprite(up, down, left, right, dead);
        super.gamePlay = gamePlay;
        velocity = 2; //Vận tốc của Bomber = 2 pixel/frame

        canWalkThroughFlame = true;
        canWalkThroughBrick = false;

        bombNums = 3;
        lives = 3;
        flameRange = 1;
    }

    @Override
    public void update() {
        if (isAlive) {
            CollisionChecker.checkTileStable(this, gamePlay);
            CollisionChecker.checkMovingEntity(this, gamePlay);
            handleCollision();
            changeState();

            //Kiểm tra xem nhân vật có bị kẹt không.
            futureCollision = CollisionChecker.NULL_COLLISION;
            CollisionChecker.checkTileEntity(this, gamePlay);
            if (canMove()) {
                move();
            }
        } else {
            handleDeadState();
        }
    }

    protected boolean canMove() {
        return futureCollision != CollisionChecker.WALL_COLLISION
                && (futureCollision != CollisionChecker.BRICK_COLLISION || isCanWalkThroughBrick())
                && futureCollision != CollisionChecker.BOMB_COLLISION;
    }

    public void handleEvent(KeyEvent event) {
        //Handle Event nhận vào, bấm W thì đi lên, S đi xuống, A sang trái, D sang phải
        //Nhân vật chỉ có thể được đi một hướng duy nhất
        switch (event.getCode()) {
            case W:  {
                upPressed = true;
                downPressed = false;
                leftPressed = false;
                rightPressed = false;
                break;
            }
            case S: {
                upPressed = false;
                downPressed = true;
                leftPressed = false;
                rightPressed = false;
                break;
            }
            case A: {
                upPressed = false;
                downPressed = false;
                leftPressed = true;
                rightPressed = false;
                break;
            }
            case D: {
                upPressed = false;
                downPressed = false;
                leftPressed = false;
                rightPressed = true;
                break;
            }
            case B: {
                if (bombNums > 0) {
                    new Bomb(getXUnit(), getYUnit(), gamePlay, flameRange);
                    gamePlay.getContainedLevelScreen().setBomberStat(InformationPane.BOMBNO, --bombNums);
                }
                break;
            }
        }
    }

    public void handleReleasedEvent(KeyEvent event) {
        //Khi thả nút ra thì hướng di chuyển sẽ = none
        state = NORMAL_STATE;

        //Handle sự kiện thả phím, thả phím nào thì img sẽ đứng yên theo hướng đó.
        switch (event.getCode()) {
            case W:  {
                upPressed = false;
                img = Sprite.player_up.getFxImage();
                break;
            }
            case S: {
                downPressed = false;
                img = Sprite.player_down.getFxImage();
                break;
            }
            case A: {
                leftPressed = false;
                img = Sprite.player_left.getFxImage();
                break;
            }
            case D: {
                rightPressed = false;
                img = Sprite.player_right.getFxImage();
                break;
            }
        }
    }

    private void handleCollision() {
        if (presentCollision == CollisionChecker.FLAME_COLLISION && !isCanWalkThroughFlame()) {
            handleDeath();
            return;
        }
        if (presentCollision == CollisionChecker.ENEMY_COLLISION) {
            handleDeath();
            return;
        }
        if (presentCollision == CollisionChecker.PORTAL_COLLISION) {
            //Modify attribute here
        }

        if (presentCollision == CollisionChecker.SPEED_ITEM_COLLISION) {
            gamePlay.getContainedLevelScreen().setBomberStat(InformationPane.SPEED, ++velocity);
            deleteItem();
        }
        if (presentCollision == CollisionChecker.BOMBS_ITEM_COLLISION) {
            gamePlay.getContainedLevelScreen().setBomberStat(InformationPane.BOMBNO, ++bombNums);
            deleteItem();
        }
        if (presentCollision == CollisionChecker.FLAMES_ITEM_COLLISION) {
            gamePlay.getContainedLevelScreen().setBomberStat(InformationPane.FLAME_RANGE, ++flameRange);
            deleteItem();
        }
    }

    private void handleDeath() {
        gamePlay.getContainedLevelScreen().setBomberStat(InformationPane.LIVES_LEFT, --lives);
        state = DEAD_STATE;
        isAlive = false;
    }

    protected void handleDeadState() {
        super.handleDeadState();
        if (animationDeadTime == 0) {
            if (lives <= 0) {
                gamePlay.getContainedLevelScreen().gameOver();
                gamePlay.stopTimer();
            } else {
                isAlive = true;
                resurrectBomber();
            }
        }
    }

    private void resurrectBomber() {
        animationDeadTime = MovingEntity.DEAD_TIME;
        state = NORMAL_STATE;
        setX(32);
        setY(32);
        gamePlay.getMapManager().addMovingEntities(this);
    }

    private void changeState() {
        if (upPressed || downPressed || leftPressed || rightPressed) {
            if (upPressed) {
                state = UP_STATE;
            }
            if (downPressed) {
                state = DOWN_STATE;
            }

            if (leftPressed) {
                state = LEFT_STATE;
            }

            if (rightPressed) {
                state = RIGHT_STATE;
            }
            animation(state);
        }
    }

    private void deleteItem() {
        Entity item = gamePlay.getMapManager().getTopTileAt(getXUnit(), getYUnit());
        gamePlay.getMapManager().getTilesAt(getXUnit(), getYUnit()).remove(item);
    }

    public int getBombNums() {
        return bombNums;
    }

    public void setBombNums(int bombNums) {
        this.bombNums = bombNums;
    }

    public int getFlameRange() {
        return flameRange;
    }

    public void setFlameRange(int flameRange) {
        this.flameRange = flameRange;
    }

    public boolean isCanWalkThroughBomb() {
        return canWalkThroughBomb;
    }

    public void setCanWalkThroughBomb(boolean canWalkThroughBomb) {
        this.canWalkThroughBomb = canWalkThroughBomb;
    }

    public boolean isCanWalkThroughBrick() {
        return canWalkThroughBrick;
    }

    public void setCanWalkThroughBrick(boolean canWalkThroughBrick) {
        this.canWalkThroughBrick = canWalkThroughBrick;
    }

    public boolean isCanWalkThroughFlame() {
        return canWalkThroughFlame;
    }

    public void setCanWalkThroughFlame(boolean canWalkThroughFlame) {
        this.canWalkThroughFlame = canWalkThroughFlame;
    }

    public boolean isCanDetonate() {
        return canDetonate;
    }

    public void setCanDetonate(boolean canDetonate) {
        this.canDetonate = canDetonate;
    }

    public void setVelocity(int velocity) {
        super.setVelocity(velocity);
    }

    public int getVelocity() {
        return super.getVelocity();
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}


