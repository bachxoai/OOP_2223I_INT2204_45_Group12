package bomberman.entities;

import bomberman.entities.moving.Bomber;
import bomberman.graphics.Sprite;
import bomberman.managers.GamePlay;
import bomberman.managers.MapManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Thực thể trong map.
 * Cần được render.
 */
public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;
    //Toạ độ x tính từ góc trái trên trong map.
    protected int xUnit;
    //Toạ độ y tính từ góc trái trên trong map.
    protected int yUnit;
    //Ảnh đc render của đối tượng.
    protected Image img;
    //MapManager chứa entity đó.
    protected MapManager mapManager;

    public Entity(int xUnit, int yUnit, MapManager mapManager) {
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.mapManager = mapManager;
    }

    public void render(GraphicsContext gc) {
        double screenX = x - mapManager.getBomberman().getX() + mapManager.getBomberman().getScreenX();
        double screenY = y - mapManager.getBomberman().getY() + mapManager.getBomberman().getScreenY();

        if (x + Sprite.SCALED_SIZE > mapManager.getBomberman().getX() - mapManager.getBomberman().getScreenX() &&
                x - Sprite.SCALED_SIZE < mapManager.getBomberman().getX() + mapManager.getBomberman().getScreenX() &&
                y + Sprite.SCALED_SIZE > mapManager.getBomberman().getY() - mapManager.getBomberman().getScreenY() &&
                y - Sprite.SCALED_SIZE < mapManager.getBomberman().getY() + mapManager.getBomberman().getScreenY()) {
            gc.drawImage(img, screenX, screenY);
        }
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

    public MapManager getMapManager() {
        return mapManager;
    }

    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public abstract boolean handleEntityCollision(Bomber bomber);
}
