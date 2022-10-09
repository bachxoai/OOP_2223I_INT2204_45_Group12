package bomberman.entities.movingEntities;

import bomberman.ScreenController.ClassicMap;
import bomberman.ScreenController.Map;
import bomberman.entities.Entity;
import bomberman.graphics.Sprite;

import java.awt.*;

//Đây là một Class chung cho các Entity có thể di chuyển.
public abstract class MovingEntity extends Entity {
    //Các Sprite lưu animation của Entity.
    protected Sprite[] up;
    protected Sprite[] down;
    protected Sprite[] left;
    protected Sprite[] right;
    protected Sprite[] dead;

    //Một biến để lưu hướng của Entity.
    protected String direction;

    //Một hình vuông là hitbox của Entity.
    protected Rectangle solidArea;

    //Biến int trả về các giá trị khi có va chạm
    // 0 là bình thường, 1 là bị chặn, 2 là ăn Bombs, 3 là ăn Flames, 4 là ăn Speed
    protected int collisionOn = 0;

    //Vận tốc
    protected int velocity;

    //Số khung hình trên giây
    public final int ANIMATED_FRAME = 6;

    public MovingEntity(int xUnit, int yUnit, ClassicMap map) {
        super(xUnit, yUnit, map);
        direction = "none";
        map.getEntities().add(this);
    }

    //Phương thức gán các hình ảnh để tạo animation cho MovingEntity
    public void setSprite(Sprite[] up, Sprite[] down, Sprite[] left, Sprite[] right, Sprite[] dead) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public String getDirection() {
        return direction;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setCollisionOn(int collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getVelocity() {
        return velocity;
    }

    @Override
    public abstract void update();

    //Các phương thức thay đổi img của Entity để tạo ra Animation cho nó.
    protected void animatedRight() {
        int n = Map.frameCount/(60/ANIMATED_FRAME);
        int animationnumber = 0;
        if (n % 2 == 0) {
            animationnumber++;
            if (animationnumber >= right.length) {
                animationnumber = 0;
            }
        }
        img = right[animationnumber].getFxImage();
    }

    protected void animatedLeft() {
        int n = Map.frameCount/(60/ANIMATED_FRAME);
        int animationnumber = 0;
        if (n % 2 == 0) {
            animationnumber++;
            if (animationnumber >= left.length) {
                animationnumber = 0;
            }
        }
        img = left[animationnumber].getFxImage();
    }

    protected void animatedDown() {
        int n = Map.frameCount/(60/ANIMATED_FRAME);
        int animationnumber = 0;
        if (n % 2 == 0) {
            animationnumber++;
            if (animationnumber >= down.length) {
                animationnumber = 0;
            }
        }
        img = down[animationnumber].getFxImage();
    }

    protected void animatedUp() {
        int n = Map.frameCount/(60/ANIMATED_FRAME);
        int animationnumber = 0;
        if (n % 2 == 0) {
            animationnumber++;
            if (animationnumber >= up.length) {
                animationnumber = 0;
            }
        }
        img = up[animationnumber].getFxImage();
    }

    protected void animatedDead() {
        int n = Map.frameCount/(60/ANIMATED_FRAME);
        int animationnumber = 0;
        if (n % 2 == 0) {
            animationnumber++;
            if (animationnumber >= dead.length) {
                animationnumber = 0;
            }
        }
        img = dead[animationnumber].getFxImage();
    }
}
