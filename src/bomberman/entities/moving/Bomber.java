package bomberman.entities.moving;

import bomberman.entities.moving.enemy.Enemy;
import bomberman.entities.tile.Brick;
import bomberman.entities.tile.bomb.Bomb;
import bomberman.graphics.Sprite;
import bomberman.managers.*;
import bomberman.screen.levelscreen.HeartPane;
import bomberman.screen.levelscreen.InformationPane;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * Class bomber.
 */
public class Bomber extends MovingEntity {
    public static final double MAX_DISTANCE_TO_REFINE = 10;
    public static final int IMMORTAL_TIME = 3;
    int lives;
    int bombNums;
    int flameRange;
    boolean canDetonate;
    boolean isImmortal;
    int immortalTime;
    //Kiểm tra xem nút Up có đang được bấm hay không? Các nút còn lại tương tự.
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private final int screenX = GamePlay.gameplayScreenWidth / 2 - Sprite.SCALED_SIZE / 2;
    private final int screenY = GamePlay.gameplayScreenHeight / 2 - Sprite.SCALED_SIZE / 2;
    private ArrayList<Bomb> placedBombs;

    /**
     * Constructor.
     *
     * @param x             position x in map.
     * @param y             position y in map.
     * @param mapManager    the MapManager to initialize.
     */
    public Bomber(int x, int y, MapManager mapManager) {
        super(x, y, mapManager);
        img = Sprite.player_down.getFxImage();
//        Thêm các Spite animation cho Bomber
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
        velocity = 2;

        canWalkThroughBomb = false;
        canWalkThroughFlame = false;
        canWalkThroughBrick = false;
        isImmortal = false;
        immortalTime = 0;

        bombNums = 1;
        lives = 3;
        flameRange = 1;
        placedBombs = new ArrayList<>();
    }

    @Override
    public void update() {
        if (isAlive) {
            changeState();
            handleImmortal();
            updateFutureTilesCollision();
            updatePresentTileCollision();
            if (futureTilesCollision[0].allowWalkThrough(this)
                    || futureTilesCollision[1].allowWalkThrough(this)) {
                if (!futureTilesCollision[0].allowWalkThrough(this)) {
                    refineMovement(0, 1);
                } else if (!futureTilesCollision[1].allowWalkThrough(this)) {
                    refineMovement(1, 0);
                } else {
                    move();
                }
            } else {
                moveToFitTile();
            }
            if (presentTileCollision.handleOtherBomberCollision(this)
                    && futureTilesCollision[0].handleOtherBomberCollision(this)
                    && (futureTilesCollision[0] == futureTilesCollision[1]
                    || futureTilesCollision[1].handleOtherBomberCollision(this)))
                checkMovingCollisions();

        } else {
            handleDeadState();
        }
    }

    /**
     * Handle key pressed event.
     *
     * @param event the key event.
     */
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
                downPressed = true;
                upPressed = false;
                leftPressed = false;
                rightPressed = false;
                break;
            }
            case A: {
                leftPressed = true;
                upPressed = false;
                downPressed = false;
                rightPressed = false;
                break;
            }
            case D: {
                rightPressed = true;
                upPressed = false;
                downPressed = false;
                leftPressed = false;
                break;
            }
            case B: {
                placeBomb();
                break;
            }
            case N: {
                if (isCanDetonate()) {
                    detonateBombs();
                }
            }
        }
    }

    /**
     * handle released key event.
     *
     * @param event key event.
     */
    public void handleReleasedEvent(KeyEvent event) {
        //Khi thả nút ra thì hướng di chuyển sẽ = none
        state = NORMAL_STATE;

        //Handle sự kiện thả phím, thả phím nào thì img sẽ đứng yên theo hướng đó.
        switch (event.getCode()) {
            case W: {
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

    /**
     * handle bomber's death.
     */
    public void handleDeath() {
        if (4 - lives == 1) {
            mapManager.getGamePlay().getContainedLevelScreen().getHeartpane().getChildren().remove(HeartPane.hf3);
        }
        if (4 - lives == 2) {
            mapManager.getGamePlay().getContainedLevelScreen().getHeartpane().getChildren().remove(HeartPane.hf2);
        }
        if (4 - lives == 3) {
            mapManager.getGamePlay().getContainedLevelScreen().getHeartpane().getChildren().remove(HeartPane.hf1);
        }
        lives--;
        state = DEAD_STATE;
        isAlive = false;
        if (SoundEffect.hasSoundEffect) {
            SoundEffect.playSE(SoundEffect.bomberDeath);
        }
    }

    protected void handleDeadState() {
        super.handleDeadState();
        if (animationDeadTime == 0) {
            if (lives <= 0) {
                getMapManager().getGamePlay().getContainedLevelScreen().getGameOver().setTitle("Defeat");
                mapManager.getGamePlay().getContainedLevelScreen().gameOver();
                mapManager.getGamePlay().stopTimer();
                SoundEffect.playSE(SoundEffect.gameOver);
                SoundBackground.clip.stop();//tam dung nhac
            } else {
                isAlive = true;
                resurrectBomber();
            }
        }
    }

    private void resurrectBomber() {
        animationDeadTime = MovingEntity.DEAD_TIME;
        state = NORMAL_STATE;
        img = Sprite.player_right.getFxImage();
        setX(32);
        setY(32);
        immortalTime = IMMORTAL_TIME * 60;
        isImmortal = true;
    }

    private void changeState() {
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
        animation();
    }

    private void handleImmortal() {
        if (immortalTime != 0) {
            immortalTime--;
            if (immortalTime % 10 == 5) {
                img = Sprite.nothing.getFxImage();
            } else if (immortalTime % 10 == 0) {
                img = Sprite.player_right.getFxImage();
            }
        } else {
            isImmortal = false;
        }
    }

    private void placeBomb() {
        if (bombNums > 0 && presentTileCollision.canPlaceBomb()) {
            placedBombs.add(new Bomb(getXUnit(), getYUnit(), mapManager, flameRange, this));
            mapManager.getGamePlay().getContainedLevelScreen().setBomberStat(InformationPane.BOMBNO, --bombNums);
            if (SoundEffect.hasSoundEffect) {
                SoundEffect.playSE(SoundEffect.plantingBomb);
                SoundEffect.playSE(SoundEffect.bombCountDown);
            }
        }
    }

    private void refineMovement(int unWalkableTile, int walkableTile) {
        if (futureTilesCollision[unWalkableTile].getX() == futureTilesCollision[walkableTile].getX()) {
            if (getY() < futureTilesCollision[walkableTile].getY()
                    && getY() > futureTilesCollision[walkableTile].getY() - MAX_DISTANCE_TO_REFINE) {
                setY(futureTilesCollision[unWalkableTile].getY() + Sprite.SCALED_SIZE);
                move();
            } else if (getY() > futureTilesCollision[walkableTile].getY()
                    && getY() < futureTilesCollision[walkableTile].getY() + MAX_DISTANCE_TO_REFINE) {
                setY(futureTilesCollision[walkableTile].getY());
                move();
            }
        } else {
            if (getX() < futureTilesCollision[walkableTile].getX()
                    && getX() > futureTilesCollision[walkableTile].getX() - MAX_DISTANCE_TO_REFINE) {
                setX(futureTilesCollision[unWalkableTile].getX() + Sprite.SCALED_SIZE);
                move();
            } else if (getX() > futureTilesCollision[walkableTile].getX()
                    && getX() < futureTilesCollision[walkableTile].getX() + MAX_DISTANCE_TO_REFINE) {
                setX(futureTilesCollision[walkableTile].getX());
                move();
            }
        }
    }

    /**
     * Check collision with all MovingEntity in Map
     * until all the MovingEntity are checked
     * or the checkHandleOtherXCollision return false;
     */
    @Override
    public void checkMovingCollisions() {
        for (Enemy enemy : getMapManager().getEnemies()) {
            if (!(getX() + Sprite.SCALED_SIZE - 1 < enemy.getX()
                    || getX() > enemy.getX() + Sprite.SCALED_SIZE - 1
                    || getY() > enemy.getY() + Sprite.SCALED_SIZE - 1
                    || getY() + Sprite.SCALED_SIZE - 1 < enemy.getY())) {
                enemy.handleOtherBomberCollision(this);
            }
        }
    }

    private void moveToFitTile() {
        switch (state) {
            case UP_STATE: {
                setY(getYUnit() * Sprite.SCALED_SIZE);
                break;
            }
            case DOWN_STATE: {
                setY((int) (((getY()) + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE);
                break;
            }
            case LEFT_STATE: {
                setX(getXUnit() * Sprite.SCALED_SIZE);
                break;
            }
            case RIGHT_STATE: {
                setX((int) (((getX()) + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE);
                break;
            }
        }
    }

    private void detonateBombs() {
        for (int i = placedBombs.size() - 1; i >= 0; i--) {
            if (i < placedBombs.size()) {
                placedBombs.get(i).explode();
            }
        }
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

    public boolean isCanDetonate() {
        return canDetonate;
    }

    public void setCanDetonate(boolean canDetonate) {
        this.canDetonate = canDetonate;
    }

    public void setVelocity(double velocity) {
        super.setVelocity(velocity);
    }

    public double getVelocity() {
        return super.getVelocity();
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, screenX, screenY);
    }

    public double getScreenX() {
        return screenX;
    }

    public double getScreenY() {
        return screenY;
    }

    public boolean isImmortal() {
        return isImmortal;
    }

    public ArrayList<Bomb> getPlacedBombs() {
        return placedBombs;
    }
}
