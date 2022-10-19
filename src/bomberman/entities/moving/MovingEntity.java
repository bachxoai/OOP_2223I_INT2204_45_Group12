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

    //Trạng thái của Moving Entity
    protected int state;

    //Các Sprite lưu animation của Entity.
    protected Sprite[] up;
    protected Sprite[] down;
    protected Sprite[] left;
    protected Sprite[] right;
    protected Sprite[] dead;

    //Va chạm của ô sắp gặp
    protected String futureCollision = CollisionChecker.NULL_COLLISION;

    //Va chạm của ô đang đứng
    protected String presentCollision = CollisionChecker.NULL_COLLISION;

    //Vận tốc
    protected int velocity;

    //Số khung hình trên giây
    public final int ANIMATED_FRAME = 6;

    //Thời gian hoạt ảnh animation luc chết
    protected int animationDeadTime = 20;

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

    public void setFutureCollision(String collisionStatus) {
        this.futureCollision = collisionStatus;
    }

    public void setPresentCollision(String presentCollision) {
        this.presentCollision = presentCollision;
    }

    public int getVelocity() {
        return velocity;
    }

    @Override
    public abstract void update();

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

    protected void move() {
        if (futureCollision != CollisionChecker.WALL_COLLISION && futureCollision != CollisionChecker.BRICK_COLLISION
                && futureCollision != CollisionChecker.BOMB_COLLISION) {
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
    }

    protected void handleDeadState() {
        if (animationDeadTime == 0) {
            gamePlay.getMapManager().getEnemies().remove(this);
            gamePlay.getMapManager().getMovingEntities().remove(this);
            return;
        }
        animationDeadTime--;
        animation(DEAD_STATE);
    }
}
