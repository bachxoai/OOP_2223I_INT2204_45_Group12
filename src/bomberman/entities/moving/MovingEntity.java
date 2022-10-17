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
    public static final String NORMAL_STATE = "normal";
    public static final String DEAD_STATE = "dead";
    public static final String UP_STATE = "up";
    public static final String DOWN_STATE = "down";
    public static final String LEFT_STATE = "left";
    public static final String RIGHT_STATE = "right";

    //Trạng thái của Moving Entity
    protected String state;

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

    public String getState() {
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

    protected void animation(String state) {
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
}
