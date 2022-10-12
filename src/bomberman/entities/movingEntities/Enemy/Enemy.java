package bomberman.entities.movingEntities.Enemy;

import bomberman.entities.DynamicEntity;
import bomberman.entities.movingEntities.MovingEntity;
import bomberman.managers.GamePlay;

public abstract class Enemy extends MovingEntity {
    public Enemy(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
    }
}
