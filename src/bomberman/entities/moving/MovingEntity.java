package bomberman.entities.moving;

import bomberman.entities.DynamicEntity;
import bomberman.managers.GamePlay;
import bomberman.entities.Entity;
import bomberman.graphics.Sprite;

import java.awt.*;

/**
 * Class dành cho các đối tượng có thể di chuyển. Liên quan đến toạ độ pixel.
 */
public abstract class MovingEntity extends Entity implements DynamicEntity {
    //Các Sprite lưu animation của Entity.
//    protected Sprite up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2;
    protected Sprite[] up;
    protected Sprite[] down;
    protected Sprite[] left;
    protected Sprite[] right;
    protected Sprite[] dead;

    //Một biến để lưu hướng của Entity.
    protected String direction;

    //Một hình vuông là hitbox của Entity.
    protected Rectangle solidArea;

    //Biến trả về các giá trị khi có va chạm
    protected String collisionStatus = "null";

    //Vận tốc
    protected int velocity;

    //Số khung hình trên giây
    public final int ANIMATED_FRAME = 6;

    protected int animationDeadTime;

    public MovingEntity(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        direction = "none";
        gamePlay.getMapManager().addMovingEntities(this);
    }

    //Phương thức gán các hình ảnh để tạo animation cho MovingEntity
    public void setSprite(Sprite[] up, Sprite[] down, Sprite[] left, Sprite[] right, Sprite[] dead) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.dead = dead;
    }

    public String getDirection() {
        return direction;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setCollisionStatus(String collisionStatus) {
        this.collisionStatus = collisionStatus;
    }

    public int getVelocity() {
        return velocity;
    }

    @Override
    public abstract void update();

    //Các phương thức thay đổi img của Entity để tạo ra Animation cho nó.
    protected void animatedRight() {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        img = right[n % right.length].getFxImage();
    }

    protected void animatedLeft() {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        img = left[n % left.length].getFxImage();
    }

    protected void animatedDown() {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        img = down[n % down.length].getFxImage();
    }

    protected void animatedUp() {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        img = up[n % up.length].getFxImage();
    }

    protected void animatedDead() {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        img = dead[n % dead.length].getFxImage();
    }
}
