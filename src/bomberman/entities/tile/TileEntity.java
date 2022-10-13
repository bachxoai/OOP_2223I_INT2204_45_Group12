package bomberman.entities.tile;

import bomberman.managers.GamePlay;
import bomberman.entities.Entity;

/**
 * Class dành cho các đối tượng đứng yên. Liên quan đến toạ độ trong map
 */
public abstract class TileEntity extends Entity {

    public TileEntity(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        gamePlay.getMapManager().addTileEntity(this);
    }

    //Đây là biến int trả về giá trị khi có va chạm.
    // 0 là bình thường, 1 là bị chặn, 2 là ăn Bombs, 3 là ăn Flames, 4 là ăn Speed
    protected int collision = 0;

    public int getCollision() {
        return collision;
    }
}
