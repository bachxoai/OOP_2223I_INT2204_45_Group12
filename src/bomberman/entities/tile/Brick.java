package bomberman.entities.tile;

import bomberman.entities.DynamicEntity;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

//Class cho gạch
public class Brick extends TileEntity implements DynamicEntity {
    public Brick(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.brick.getFxImage();
        collision = "block"; //Không cho người chơi đi qua
    }
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
