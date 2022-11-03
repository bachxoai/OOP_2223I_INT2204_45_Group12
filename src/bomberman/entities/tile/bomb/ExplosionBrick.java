package bomberman.entities.tile.bomb;

import bomberman.entities.tile.Brick;
import bomberman.graphics.Sprite;
import bomberman.managers.CollisionChecker;
import bomberman.managers.MapManager;

/**
 * Đối tượng gạch nổ.
 */
public class ExplosionBrick extends Explosion {
    /**
     * Constructor.
     *
     * @param xUnit     giá trị để khởi tạo xUnit.
     * @param yUnit     giá trị để khởi tạo yUnit.
     * @param mapManager  giá trị để khởi tạo mapManager.
     */
    public ExplosionBrick(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager,
                Sprite.brick_exploded,
                Sprite.brick_exploded1,
                Sprite.brick_exploded2);
    }
}
