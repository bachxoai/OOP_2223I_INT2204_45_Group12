package bomberman.entities.tile.bomb;

import bomberman.graphics.Sprite;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;

/**
 * Đối tượng gạch nổ.
 */
public class ExplosionBrick extends Explosion {
    /**
     * Constructor.
     *
     * @param xUnit     giá trị để khởi tạo xUnit.
     * @param yUnit     giá trị để khởi tạo yUnit.
     * @param gamePlay  giá trị để khởi tạo gamePlay.
     */
    public ExplosionBrick(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay,
                Sprite.brick_exploded,
                Sprite.brick_exploded1,
                Sprite.brick_exploded2);
        collision = CollisionChecker.BRICK_COLLISION;
    }
}
