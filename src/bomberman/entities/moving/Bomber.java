package bomberman.entities.moving;

import bomberman.entities.tile.bomb.Bomb;
import bomberman.graphics.Sprite;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import javafx.scene.input.KeyEvent;

/**
 * Class bomber.
 */
public class Bomber extends MovingEntity {
    int bombNums;
    int bombRange;
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
        animationDeadTime = -1;
    }

    @Override
    public void update() {
        //Nếu có phím được bấm thì thay đổi hướng + nhân vật thực hiện animation, lúc này nhân vật chưa thay đổi vị trí
        //vì nhân vật có thể bị kẹt tường

        if (animationDeadTime == 0) {
            gamePlay.getMapManager().getEnemies().remove(this);
            gamePlay.getMapManager().getMovingEntities().remove(this);
        }

        if (collisionStatus == "dead") {
            animationDeadTime--;
            animatedDead();
            return;
        }

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
        collisionStatus = "null";
        CollisionChecker.checkTileEntity(this, gamePlay);
        CollisionChecker.checkMovingEntity(this, gamePlay);

        //Nếu nhân vật không bị kẹt tường thì thay đổi vị trí theo hướng (direction)
        if (collisionStatus != "block" && collisionStatus != "bomb") {
            if (collisionStatus == "flame") {
                collisionStatus = "dead";
                animationDeadTime = 20;
            }
            if (collisionStatus == "getAttacked") {
                collisionStatus = "dead";
                animationDeadTime = 20;
            }
            if (collisionStatus == "dead") {
                animationDeadTime--;
                animatedDead();
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
                new Bomb(getXUnit(), getYUnit(), gamePlay);
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
}


