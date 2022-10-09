package bomberman.entities.movingEntities;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;
import javafx.scene.input.KeyEvent;

import java.awt.*;

public class Bomber extends MovingEntity {
    //Kiểm tra xem nút Up có đang được bấm hay không? Các nút còn lại tương tự.
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;

    //Thời gian hiệu lực của vật phẩm tăng tốc
    private int speedTimer = 0;

    public Bomber(int x, int y, ClassicMap map) {
        super(x, y, map);
        img = Sprite.player_down.getFxImage();
        //Thêm các Spite animation cho Bomber
        Sprite[] up = new Sprite[3];
        up[0] = Sprite.player_up;
        up[1] = Sprite.player_up_1;
        up[2] = Sprite.player_up_2;
        Sprite[] down = new Sprite[3];
        down[0] = Sprite.player_down;
        down[1] = Sprite.player_down_1;
        down[2] = Sprite.player_down_2;
        Sprite[] right = new Sprite[3];
        right[0] = Sprite.player_right;
        right[1] = Sprite.player_right_1;
        right[2] = Sprite.player_right_2;
        Sprite[] left = new Sprite[3];
        left[0] = Sprite.player_left;
        left[1] = Sprite.player_left_1;
        left[2] = Sprite.player_left_2;
        Sprite[] dead = new Sprite[3];
        dead[0] = Sprite.player_dead1;
        dead[1] = Sprite.player_dead2;
        dead[2] = Sprite.player_dead3;

        setSprite(up, down, left, right, dead);
        solidArea = new Rectangle(0,12,20,20); //Cài đặt thông số cho hitbox của Bomber
        super.map = map;
        velocity = 2; //Vận tốc của Bomber = 2 pixel/frame
    }

    @Override
    public void update() {
        //Nếu có phím được bấm thì thay đổi hướng + nhân vật thực hiện animation, lúc này nhân vật chưa thay đổi vị trí
        //vì nhân vật có thể bị kẹt tường
        if (upPressed || downPressed || leftPressed || rightPressed) {
            if (upPressed) {
                direction = "up";
                animatedUp();
            }
            if (downPressed) {
                direction = "down";
                animatedDown();
            }

            if (leftPressed) {
                direction = "left";
                animatedLeft();
            }

            if (rightPressed) {
                direction = "right";
                animatedRight();
            }
        }

        //Kiểm tra xem nhân vật có bị kẹt tường không.
        collisionOn = 0;
        map.getCollisionChecker().checkTile(this);

        //Nếu nhân vật không bị kẹt tường thì thay đổi vị trí theo hướng (direction)
        if (collisionOn != 1) {
            if (collisionOn == 2) { //Ăn được Bombs

            } else if (collisionOn == 3) { //Ăn được Flames

            } else if (collisionOn == 4) { //Ăn được Speed
                speedTimer = 6; //Thời gian hiệu lực chạy nhanh = 6
            }
            switch (direction) {
                case "up": {
                    y -= velocity;
                    break;
                }
                case "down": {
                    y += velocity;
                    break;
                }
                case "left": {
                    x -= velocity;
                    break;
                }
                case "right": {
                    x += velocity;
                    break;
                }
            }
        }

        //Nếu thời gian hiệu lực tăng tốc khác 0 thì gọi hàm để (bắt đầu đếm ngược và tăng tốc độ)
        if (speedTimer != 0) {
            velocity = 5;
            speedCountdown();
        } else {
            velocity = 2;
        }
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
        }
    }

    public void handleReleasedEvent(KeyEvent event) {
        //Khi thả nút ra thì hướng di chuyển sẽ = none
        direction = "none";

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

    //Hàm đếm ngược + tăng tốc độ di chuyển
    public void speedCountdown() {
        if (ClassicMap.frameCount == 59) {
            speedTimer--;
        }
    }
}


