package bomberman.entities.tileEntities;

import bomberman.entities.DynamicEntity;
import bomberman.entities.Entity;
import bomberman.entities.tileEntities.Grass;
import bomberman.graphics.Sprite;
import bomberman.graphics.SpriteSheet;
import bomberman.managers.GamePlay;
import javafx.scene.canvas.GraphicsContext;

public class Bomb extends TileEntity implements DynamicEntity {
    int range;
    int timeToExplode;
    int explodingTime;
    Sprite[] bombs;
    Sprite[] explosion;
    public Bomb(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.bomb.getFxImage();
        bombs = new Sprite[4];
        bombs[0] = Sprite.bomb;
        bombs[1] = Sprite.bomb_1;
        bombs[2] = Sprite.bomb_2;
        bombs[3] = Sprite.bomb_1;
        explosion = new Sprite[5];
        explosion[0] = Sprite.bomb_exploded;
        explosion[1] = Sprite.bomb_exploded1;
        explosion[2] = Sprite.bomb_exploded2;
        explosion[3] = Sprite.bomb_exploded1;
        explosion[4] = Sprite.bomb_exploded;
        timeToExplode = 200;
        explodingTime = 25;
        gamePlay.getMapManager().addTileEntity(this);
        gamePlay.getMapManager().addDynamicEntity(this);
    }

    @Override
    public void update() {
        if (timeToExplode > 0) {
            timeToExplode--;
            img = bombs[(timeToExplode/15) % 4].getFxImage();
        } else {
            //System.out.println("bomb exploded");
            handleExplode();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public void handleExplode() {
        if (explodingTime > 0) {
            explodingTime--;
            img = explosion[explodingTime/5].getFxImage();
        } else if (explodingTime == 0) {
//            Grass g = new Grass(5, 5, gamePlay);
//            gamePlay.getMapManager().getEntityMatrix()[5][5] = g;
//            gamePlay.getEntities().remove(this);
        }
    }
}
