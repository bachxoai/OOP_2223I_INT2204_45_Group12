package bomberman.entities.movingEntities.Enemy;

import bomberman.entities.movingEntities.Enemy.Enemy;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

import java.awt.*;
//Class quái Balloom
public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.balloom_left1.getFxImage();
        solidArea = new Rectangle(0,0,32,32);
        super.gamePlay = gamePlay;
        velocity = 2;

        Sprite[] right = new Sprite[3];
        right[0] = Sprite.balloom_right1;
        right[1] = Sprite.balloom_right2;
        right[2] = Sprite.balloom_right3;
        Sprite[] left = new Sprite[3];
        left[0] = Sprite.balloom_left1;
        left[1] = Sprite.balloom_left2;
        left[2] = Sprite.balloom_left3;
        Sprite[] dead = new Sprite[1];
        dead[0] = Sprite.balloom_dead;
        setSprite(left, right, left, right, dead);
    }

    @Override
    public void update() {
        animatedLeft();
    }
}
