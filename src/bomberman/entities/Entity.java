package bomberman.entities;

import bomberman.ScreenController.Map1;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //Map chứa entity đó.
    protected Map1 map;


    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
//    public Entity( int xUnit, int yUnit, Image img, Map1 map) {
//        this.x = xUnit * Sprite.SCALED_SIZE;
//        this.y = yUnit * Sprite.SCALED_SIZE;
//        this.img = img;
//        this.map = map;
//    }

    //Các đối tượng đều biết img cụ thể nên không cần truyền vào img nữa
    public Entity( int xUnit, int yUnit, Map1 map) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.map = map;
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
