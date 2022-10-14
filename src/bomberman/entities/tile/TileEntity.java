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

    /**
     * Một thuộc tính xác định TileEntity có chặn người chơi đi qua hay không.
     */
    protected String collision = "null";

    public String getCollision() {
        return collision;
    }
}
