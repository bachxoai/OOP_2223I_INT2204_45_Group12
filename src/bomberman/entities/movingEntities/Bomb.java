package bomberman.entities.movingEntities;

import bomberman.entities.Entity;
import bomberman.entities.tileEntities.Grass;
import bomberman.graphics.Sprite;
import bomberman.graphics.SpriteSheet;
import bomberman.managers.GamePlay;

public class Bomb extends Entity {
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

    public void handleExplode() {
        if (explodingTime > 0) {
            explodingTime--;
            img = explosion[explodingTime/5].getFxImage();
        } else {
            Grass g = new Grass(5, 5, gamePlay);
//            gamePlay.getMapManager().getEntityMatrix()[5][5] = g;
            gamePlay.setStillObjectAt(5, 5, g);
            gamePlay.addStillObject(this);
            //gamePlay.getEntities().remove(this);
        }
    }
}
