package bomberman.entities.moving.enemy;

import bomberman.entities.moving.MovingEntity;
import bomberman.managers.GamePlay;

public abstract class Enemy extends MovingEntity {
    public Enemy(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
    }
}
