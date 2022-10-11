package bomberman.entities.movingEntities;

import bomberman.managers.GamePlay;
import bomberman.entities.Entity;
import bomberman.graphics.Sprite;

import java.awt.*;

//Đây là một Class chung cho các Entity có thể di chuyển.
public abstract class MovingEntity extends Entity {
    //Các Sprite lưu animation của Entity.
    protected Sprite up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2;

    //Một biến để lưu hướng của Entity.
    protected String direction;

    //Một hình vuông là hitbox của Entity.
    protected Rectangle solidArea;

    //Biến int trả về các giá trị khi có va chạm
    // 0 là bình thường, 1 là bị chặn, 2 là ăn Bombs, 3 là ăn Flames, 4 là ăn Speed
    protected int collisionOn = 0;

    //Vận tốc
    protected int Velocity;

    //Số khung hình trên giây
    public final int ANIMATED_FRAME = 6;

//    public MovingEntity(int xUnit, int yUnit, Image img, Map1 map) {
//        super(xUnit, yUnit, img, map);
//        direction = "none";
//        map.getEntities().add(this);
//    }

    public MovingEntity(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        direction = "none";
        gamePlay.getEntities().add(this);
    }

    //Phương thức gán các hình ảnh để tạo animation cho MovingEntity
    public void setSprite(Sprite up, Sprite up1, Sprite up2, Sprite down, Sprite down1, Sprite down2, Sprite left,
                          Sprite left1, Sprite left2, Sprite right, Sprite right1, Sprite right2) {
        this.up = up; this.up1 = up1; this.up2 = up2;
        this.down = down; this.down1 = down1; this.down2 = down2;
        this.left = left; this.left1 = left1; this.left2 = left2;
        this.right = right; this.right1 = right1; this.right2 = right2;
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
        return Velocity;
    }

    @Override
    public void update() {

    }

    //Các phương thức thay đổi img của Entity để tạo ra Animation cho nó.
    protected void animatedRight() {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        if (n % 2 == 0) {
            img = Sprite.player_right_1.getFxImage();
        } else {
            img = Sprite.player_right_2.getFxImage();
        }
    }

    protected void animatedLeft() {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        if (n % 2 == 0) {
            img = Sprite.player_left_1.getFxImage();
        } else {
            img = Sprite.player_left_2.getFxImage();
        }
    }

    protected void animatedDown() {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        if (n % 2 == 0) {
            img = Sprite.player_down_1.getFxImage();
        } else {
            img = Sprite.player_down_2.getFxImage();
        }
    }

    protected void animatedUp() {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        if (n % 2 == 0) {
            img = Sprite.player_up_1.getFxImage();
        } else {
            img = Sprite.player_up_2.getFxImage();
        }
    }
}
