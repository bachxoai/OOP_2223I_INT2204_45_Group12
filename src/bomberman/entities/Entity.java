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


    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
//    public Entity( int xUnit, int yUnit, Image img, Map1 map) {
//        this.x = xUnit * Sprite.SCALED_SIZE;
//        this.y = yUnit * Sprite.SCALED_SIZE;
//        this.img = img;
//        this.map = map;
//    }

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

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
}
