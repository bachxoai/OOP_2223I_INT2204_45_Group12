package bomberman.entities.moving;

import bomberman.entities.DynamicEntity;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.entities.Entity;
import bomberman.graphics.Sprite;

import java.awt.*;

/**
 * Class dành cho các đối tượng có thể di chuyển. Liên quan đến toạ độ pixel.
 */
public abstract class MovingEntity extends Entity implements DynamicEntity {
    //Các trạng thái của Moving Entity
    public static final int NORMAL_STATE = 0;
    public static final int DEAD_STATE = 1;
    public static final int UP_STATE = 2;
    public static final int DOWN_STATE = 3;
    public static final int LEFT_STATE = 4;
    public static final int RIGHT_STATE = 5;
    public static final int DEAD_TIME = 20;
    //Trạng thái của Moving Entity
    protected int state;

    //Các Sprite lưu animation của Entity.
    protected Sprite[] up;
    protected Sprite[] down;
    protected Sprite[] left;
    protected Sprite[] right;
    protected Sprite[] dead;

    //Va chạm của ô sắp gặp
    protected Entity futureCollision = null;

    //Va chạm của ô đang đứng
    protected Entity presentCollision = null;

    //Vận tốc
    protected int velocity;

    //Số khung hình trên giây
    public final int ANIMATED_FRAME = 6;

    //Thời gian hoạt ảnh animation luc chết
    protected int animationDeadTime = DEAD_TIME;

    //Biến thể hiện rằng Entity còn sống
    protected boolean isAlive = true;

    public MovingEntity(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        gamePlay.getMapManager().addMovingEntities(this);
        state = NORMAL_STATE;
    }

    //Phương thức gán các hình ảnh để tạo animation cho MovingEntity
    public void setSprite(Sprite[] up, Sprite[] down, Sprite[] left, Sprite[] right, Sprite[] dead) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.dead = dead;
    }

    public int getState() {
        return state;
    }

    public void setFutureCollision(Entity collisionStatus) {
        this.futureCollision = collisionStatus;
    }

    public void setPresentCollision(Entity presentCollision) {
        this.presentCollision = presentCollision;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public abstract void update();

    /**
     * Hàm tạo hoạt ảnh cho nhân vật
     * @param state
     */
    protected void animation(int state) {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        switch (state) {
            case UP_STATE:
                img = up[n % up.length].getFxImage();
                break;
            case DOWN_STATE:
                img = down[n % down.length].getFxImage();
                break;
            case LEFT_STATE:
                img = left[n % left.length].getFxImage();
                break;
            case RIGHT_STATE:
                img = right[n % right.length].getFxImage();
                break;
            case DEAD_STATE:
                img = dead[n % dead.length].getFxImage();
                break;
        }
    }

    /**
     * Hàm thay đổi vị trí cho nhân vật
     */
    protected void move() {
        switch (state) {
            case UP_STATE: {
                setY(getY() - velocity);
                break;
            }
            case DOWN_STATE: {
                setY(getY() + velocity);
                break;
            }
            case LEFT_STATE: {
                setX(getX() - velocity);
                break;
            }
            case RIGHT_STATE: {
                setX(getX() + velocity);
                break;
            }
        }
    }

    /**
     * Hàm thực hiện khi nhân vật chết
     */
    protected void handleDeadState() {
        animationDeadTime--;
        if (animationDeadTime == 0) {
            if (this instanceof Bomber && ((Bomber) this).lives != 0) {
                return;
            }
            gamePlay.getMapManager().getEnemies().remove(this);
            gamePlay.getMapManager().getMovingEntities().remove(this);
            return;
        }
        animation(DEAD_STATE);
    }
}
