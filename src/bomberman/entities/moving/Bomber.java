package bomberman.entities.moving;

import bomberman.entities.Entity;
import bomberman.entities.moving.enemy.Enemy;
import bomberman.entities.tile.Brick;
import bomberman.entities.tile.Portal;
import bomberman.entities.tile.Wall;
import bomberman.entities.tile.bomb.Bomb;
import bomberman.entities.tile.bomb.Explosion;
import bomberman.entities.tile.item.*;
import bomberman.graphics.Sprite;
import bomberman.managers.*;
import bomberman.screen.levelscreen.HeartPane;
import bomberman.screen.levelscreen.InformationPane;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;

/**
 * Class bomber.
 */
public class Bomber extends MovingEntity {

    private final int IMMORTAL_TIME = 3;
    int lives;
    int bombNums;
    int flameRange;
    boolean canWalkThroughBomb;
    boolean canWalkThroughBrick;
    boolean canWalkThroughFlame;
    boolean canDetonate;

    boolean isImmortal;
    int immortalTime;

    //Kiểm tra xem nút Up có đang được bấm hay không? Các nút còn lại tương tự.
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;

    private double screenX;
    private double screenY;
    int ChetLanThu;

    public Bomber(int x, int y, MapManager mapManager) {
        super(x, y, mapManager);
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
        screenX = GamePlay.gameplayScreenWidth/2 - Sprite.SCALED_SIZE/2;
        screenY = GamePlay.gameplayScreenHeight/2 - Sprite.SCALED_SIZE/2;

        setSprite(up, down, left, right, dead);
        velocity = 2; //Vận tốc của Bomber = 2 pixel/frame

        canWalkThroughBomb = false;
        canWalkThroughFlame = false;
        canWalkThroughBrick = false;
        isImmortal = false;
        immortalTime = 0;

        bombNums = 1;
        lives = 3;
        flameRange = 1;
        ChetLanThu = 0;
    }

    @Override
    public void update() {
        if (isAlive) {
            CollisionChecker.checkTileStable(this, mapManager);
            CollisionChecker.checkMovingEntity(this, mapManager);
            handleCollision();
            changeState();
            handleImmortal();

            //Kiểm tra xem nhân vật có bị kẹt không.
            futureCollision = null;
            CollisionChecker.checkTileEntity(this, mapManager);
            if (canMove()) {
                move();
            }
        } else {
            handleDeadState();
        }
    }

    protected boolean canMove() {
        return (futureCollision == null) || (!(futureCollision instanceof Wall)
                && (!(futureCollision instanceof Brick) || isCanWalkThroughBrick())
                && !(futureCollision instanceof Bomb));
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
                CollisionChecker.checkTileStable(this, mapManager);
                if (bombNums > 0 && !(presentCollision instanceof Brick)) {
                    new Bomb(getXUnit(), getYUnit(), mapManager, flameRange);
                    mapManager.getGamePlay().getContainedLevelScreen().setBomberStat(InformationPane.BOMBNO, --bombNums);
                    if(SoundEffect.hasSoundEffect) {
                        SoundEffect.playSE(SoundEffect.plantingBomb);
                        SoundEffect.playSE(SoundEffect.bombCountDown);
                    }
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
        if ((presentCollision instanceof Explosion) && !isCanWalkThroughFlame()) {
            handleDeath();
            return;
        }
        if ((presentCollision instanceof Enemy)) {
            handleDeath();
            return;
        }
        if ((presentCollision instanceof Portal)) {
            //Modify attribute here
        }

        if (presentCollision instanceof Item) {
            ((Item) presentCollision).handleBomberCollision(this);
        }
    }

    private void handleDeath() {
        ChetLanThu++;
        if(ChetLanThu == 1) {
            mapManager.getGamePlay().getContainedLevelScreen().getHeartpane().getChildren().remove(HeartPane.hf3);
        }
        if(ChetLanThu == 2) {
            mapManager.getGamePlay().getContainedLevelScreen().getHeartpane().getChildren().remove(HeartPane.hf2);
        }
        if(ChetLanThu == 3) {
            mapManager.getGamePlay().getContainedLevelScreen().getHeartpane().getChildren().remove(HeartPane.hf1);
        }
        lives--;
        state = DEAD_STATE;
        isAlive = false;
        if(SoundEffect.hasSoundEffect) {
            SoundEffect.playSE(SoundEffect.bomberDeath);
        }
    }

    protected void handleDeadState() {
        super.handleDeadState();
        if (animationDeadTime == 0) {
            if (lives <= 0) {
                mapManager.getGamePlay().getContainedLevelScreen().gameOver();
                mapManager.getGamePlay().stopTimer();
                if(SoundEffect.hasSoundEffect) {
                    SoundEffect.playSE(SoundEffect.gameOver);
                }
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
        Entity item = mapManager.getTopTileAt(getXUnit(), getYUnit());
        mapManager.getTilesAt(getXUnit(), getYUnit()).remove(item);
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
}
