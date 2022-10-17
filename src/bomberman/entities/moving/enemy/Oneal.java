package bomberman.entities.moving.enemy;

import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

import java.awt.*;

/**
 * tốc độ di chuyển thay đổi, lúc nhanh, lúc chậm
 * di chuyển "thông minh" hơn so với Balloom (biết đuổi theo Bomber).
 */
public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.oneal_left1.getFxImage();
        super.gamePlay = gamePlay;
        velocity = 2;

        Sprite[] right = new Sprite[3];
        right[0] = Sprite.oneal_right1;
        right[1] = Sprite.oneal_right2;
        right[2] = Sprite.oneal_right3;
        Sprite[] left = new Sprite[3];
        left[0] = Sprite.oneal_left1;
        left[1] = Sprite.oneal_left2;
        left[2] = Sprite.oneal_left3;
        Sprite[] dead = new Sprite[1];
        dead[0] = Sprite.oneal_dead;
        setSprite(left, right, left, right, dead);
    }
}
