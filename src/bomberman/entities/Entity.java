package bomberman.entities;

import bomberman.managers.GamePlay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //Map chứa entity đó.
    protected GamePlay gamePlay;

    //Đây là biến int trả về giá trị khi có va chạm.
    // 0 là bình thường, 1 là bị chặn, 2 là ăn Bombs, 3 là ăn Flames, 4 là ăn Speed
    protected int collision = 0;

    //Các đối tượng đều biết img cụ thể nên không cần truyền vào img nữa
    public Entity( int xUnit, int yUnit, GamePlay gamePlay) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.gamePlay = gamePlay;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCollision() {
        return collision;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
}
