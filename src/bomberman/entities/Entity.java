package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.managers.GamePlay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Thực thể trong map.
 * Cần được render.
 */
public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    private double x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    private double y;
    //Toạ độ x tính từ góc trái trên trong map.
    private int xUnit;
    //Toạ độ y tính từ góc trái trên trong map.
    private int yUnit;
    //Ảnh đc render của đối tượng.
    protected Image img;
    //GamePlay chứa entity đó.
    protected GamePlay gamePlay;

    public Entity(int xUnit, int yUnit, GamePlay gamePlay) {
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.gamePlay = gamePlay;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
        xUnit = (int) (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    public void setY(double y) {
        this.y = y;
        yUnit = (int) (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    public int getXUnit() {
        return (int) (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    public int getYUnit() {
        return (int) (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    /**
     * render đối tượng lên màn hình.
     *
     * @param gc gc được vẽ lên
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
