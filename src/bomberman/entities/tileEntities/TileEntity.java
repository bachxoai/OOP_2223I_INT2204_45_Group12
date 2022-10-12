package bomberman.entities.tileEntities;

import bomberman.managers.GamePlay;
import bomberman.entities.Entity;

//Đây là Class chung cho các Entity đứng yên.
public abstract class TileEntity extends Entity {

    public TileEntity(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        gamePlay.addButDoNotRemoveStillObject(this);
    }



    @Override
    public void update() {

    }
}
