package bomberman.entities.tileentities;

import bomberman.entities.DynamicEntity;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

//Class cho gạch
public class Brick extends TileEntity implements DynamicEntity {
    public Brick(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.brick.getFxImage();
        collision = 1; //Không cho người chơi đi qua
    }
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
