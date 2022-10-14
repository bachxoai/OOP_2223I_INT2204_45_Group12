package bomberman.entities.moving.enemy;

import bomberman.entities.moving.MovingEntity;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;

public abstract class Enemy extends MovingEntity {
    public Enemy(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        gamePlay.getMapManager().addEnemies(this);
    }
    @Override
    public void update() {

        if (animationDeadTime == 0) {
            gamePlay.getMapManager().getEnemies().remove(this);
            gamePlay.getMapManager().getMovingEntities().remove(this);
        }

        if (collisionStatus == "dead") {
            animationDeadTime--;
            animatedDead();
            return;
        }

        animatedLeft();
        CollisionChecker.checkTileEntity(this, gamePlay);

        if (collisionStatus == "flame") {
            animationDeadTime = 20;
            collisionStatus = "dead";
        }
    }
}
