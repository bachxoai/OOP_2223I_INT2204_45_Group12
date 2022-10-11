package bomberman.entities.tileEntities;

import bomberman.managers.GamePlay;
import bomberman.entities.Entity;

//Đây là Class chung cho các Entity đứng yên.
public abstract class TileEntity extends Entity {
    //Đây là biến int trả về giá trị khi có va chạm.
    // 0 là bình thường, 1 là bị chặn, 2 là ăn Bombs, 3 là ăn Flames, 4 là ăn Speed
    protected int collision = 0;

    public TileEntity(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
    }

    public int getCollision() {
        return collision;
    }

    @Override
    public void update() {

    }
}
