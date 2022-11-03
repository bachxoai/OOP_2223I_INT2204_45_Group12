package bomberman.entities.tile.bomb;

import bomberman.entities.DynamicEntity;
import bomberman.entities.tile.TileEntity;
import bomberman.graphics.Sprite;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.managers.Sound;

/**
 * Đối tượng được sinh ra sau khi bom nổ.
 */
public class Explosion extends TileEntity implements DynamicEntity {
    public static final int ANIMATED_FRAME = 30;
    int timeLeft;
    Sprite[] sprites;

    public Explosion(int xUnit, int yUnit, GamePlay gamePlay,
                     Sprite sprite,
                     Sprite sprite1,
                     Sprite sprite2) {
        super(xUnit, yUnit, gamePlay);
        timeLeft = ANIMATED_FRAME;
        sprites = new Sprite[5];
        sprites[0] = sprite;
        sprites[1] = sprite1;
        sprites[2] = sprite2;
        sprites[3] = sprite1;
        sprites[4] = sprite;
    }

    @Override
    public void update() {
        if (timeLeft > 0) {
            timeLeft--;
            img = sprites[timeLeft / (ANIMATED_FRAME / 5)].getFxImage();

        } else if (timeLeft == 0) {
            gamePlay.getMapManager().getTilesAt(getXUnit(), getYUnit()).remove(this);
        }
    }
}
