package bomberman.managers;
import bomberman.entities.Entity;
import bomberman.entities.moving.Bomber;
import bomberman.entities.moving.MovingEntity;
import bomberman.entities.moving.enemy.Enemy;
import bomberman.entities.tile.Brick;
import bomberman.entities.tile.Grass;
import bomberman.entities.tile.TileEntity;
import bomberman.entities.tile.bomb.Bomb;
import bomberman.graphics.Sprite;
import java.util.ArrayList;

//Class kiểm tra va chạm
public class CollisionChecker {

    /**
     * Hàm kiểm tra va chạm với các TileEntity trong GamePlay nào đó.
     *
     * @param entity
     * @param gamePlay
     */
    public static void checkTileEntity(MovingEntity entity, GamePlay gamePlay) {
        double entityLeftX = entity.getX();
        double entityRightX = entity.getX() + Sprite.SCALED_SIZE - 1;
        double entityTopY = entity.getY();
        double entityBottomY = entity.getY() + Sprite.SCALED_SIZE - 1;

        //Xác định hàng, cột của hitbox của entity
        int entityLeftCol = (int) entityLeftX/Sprite.SCALED_SIZE;
        int entityRightCol = (int) entityRightX/Sprite.SCALED_SIZE;
        int entityTopRow = (int) entityTopY/Sprite.SCALED_SIZE;
        int entityBottomRow = (int) entityBottomY/Sprite.SCALED_SIZE;

        //Kiểm tra va chạm theo từng hướng
        switch (entity.getState()) {
            case MovingEntity.UP_STATE: {
                checkTileVertical(entity, (int) (entityTopY - entity.getVelocity())/Sprite.SCALED_SIZE, entityLeftX, entityRightX, entityLeftCol, entityRightCol, entity, gamePlay);
                break;
            }
            case MovingEntity.DOWN_STATE: {
                checkTileVertical(entity, (int) (entityBottomY + entity.getVelocity())/Sprite.SCALED_SIZE, entityLeftX, entityRightX, entityLeftCol, entityRightCol, entity, gamePlay);
                break;
            }
            case MovingEntity.LEFT_STATE: {
                checkTileHorizontal(entity, (int) (entityLeftX - entity.getVelocity())/Sprite.SCALED_SIZE, entityTopRow, entityBottomRow, entityTopY, entityBottomY, entity, gamePlay);
                break;
            }
            case MovingEntity.RIGHT_STATE: {
                checkTileHorizontal(entity,  (int ) (entityRightX + entity.getVelocity())/Sprite.SCALED_SIZE, entityTopRow, entityBottomRow, entityTopY, entityBottomY, entity, gamePlay);
                break;
            }
        }
    }

    private static void checkTileVertical(MovingEntity moving, int row, double entityLeftX, double entityRightX, int entityLeftCol, int entityRightCol, MovingEntity entity, GamePlay gamePlay) {
        if (entityLeftCol == entityRightCol) {
            Entity collision = ((TileEntity) gamePlay.getMapManager().getTopTileAt(entityLeftCol, row));
            if (collision instanceof Grass) {
                collision = null;
            }
            if (moving instanceof Bomber) {
                if (((Bomber) moving).isCanWalkThroughBrick() && collision instanceof Brick) {
                    collision = null;
                }
                if (((Bomber) moving).isCanWalkThroughBomb() && collision instanceof Bomb) {
                    collision = null;
                }
            }
            if (collision != null) {
                if (collision instanceof Bomb && getCollisionAtCurrentPosition(moving, gamePlay) instanceof Bomb) {
                    return;
                }
                entity.setFutureCollision(collision);
            }
        } else {
            Entity collision1 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(entityLeftCol, row));
            Entity collision2 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(entityRightCol, row));
            if (collision1 instanceof Grass) {
                collision1 = null;
            }
            if (collision2 instanceof Grass) {
                collision2 = null;
            }

            if (moving instanceof Bomber) {
                if (((Bomber) moving).isCanWalkThroughBrick()) {
                    if (collision1 instanceof Brick) {
                        collision1 = null;
                    }
                    if (collision2 instanceof Brick) {
                        collision2 = null;
                    }
                }
                if (((Bomber) moving).isCanWalkThroughBomb()) {
                    if (collision1 instanceof Bomb) {
                        collision1 = null;
                    }
                    if (collision2 instanceof Bomb) {
                        collision2 = null;
                    }
                }

            }

            if (collision1 != null || collision2 != null) {
                if (collision1 != null && collision2 == null && entityRightCol * Sprite.SCALED_SIZE - entityLeftX <= 8) {
                    entity.setX(entityRightCol * Sprite.SCALED_SIZE);
                } else if (collision2 != null && collision1 == null && entityRightX - entityLeftCol * Sprite.SCALED_SIZE - 31 <= 8) {
                    entity.setX(entityLeftCol * Sprite.SCALED_SIZE );
                } else {
                    entity.setFutureCollision((collision1 != null) ? collision1 : collision2);
                }
            }
        }
    }

    private static void checkTileHorizontal(MovingEntity moving, int col, int entityTopRow, int entityBottomRow, double entityTopY, double entityBottomY, MovingEntity entity, GamePlay gamePlay) {
        if (entityTopRow == entityBottomRow) {
            Entity collision = ((TileEntity) gamePlay.getMapManager().getTopTileAt(col, entityTopRow));
            if (collision instanceof Grass) {
                collision = null;
            }
            if (moving instanceof Bomber) {
                if (((Bomber) moving).isCanWalkThroughBrick() && collision instanceof Brick) {
                    collision = null;
                }
                if (((Bomber) moving).isCanWalkThroughBomb() && collision instanceof Bomb) {
                    collision = null;
                }
            }
            if (collision != null) {
                if (collision instanceof Bomb && getCollisionAtCurrentPosition(moving, gamePlay) instanceof Bomb) {
                    return;
                }
                entity.setFutureCollision(collision);
            }
        } else {
            Entity collision1 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(col, entityTopRow));
            Entity collision2 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(col, entityBottomRow));
            if (collision1 instanceof Grass) {
                collision1 = null;
            }
            if (collision2 instanceof Grass) {
                collision2 = null;
            }
            if (moving instanceof Bomber) {
                if (((Bomber) moving).isCanWalkThroughBrick()) {
                    if (collision1 instanceof Brick) {
                        collision1 = null;
                    }
                    if (collision2 instanceof Brick) {
                        collision2 = null;
                    }
                }
                if (((Bomber) moving).isCanWalkThroughBomb()) {
                    if (collision1 instanceof Bomb) {
                        collision1 = null;
                    }
                    if (collision2 instanceof Bomb) {
                        collision2 = null;
                    }
                }

            }

            if (collision1 != null || collision2 != null) {
                if (collision1 != null && collision2 == null && entityBottomRow * Sprite.SCALED_SIZE - entityTopY <= 8) {
                    entity.setY(entityBottomRow * Sprite.SCALED_SIZE);
                } else if (collision2 != null && collision1 == null && entityBottomY - entityTopRow * Sprite.SCALED_SIZE - 31 <= 8) {
                    entity.setY(entityTopRow * Sprite.SCALED_SIZE);
                } else {
                    entity.setFutureCollision((collision1 != null) ? collision1 : collision2);
                }
            }
        }
    }

    /**
     * Hàm kiểm tra va chạm bomberman với các enemies trong một gamePlay nào đó
     *
     * @param bomber Tham chiếu bomberman
     * @param gamePlay tham chiếu đến gamePlay hện tại
     */
    public static void checkMovingEntity(MovingEntity bomber, GamePlay gamePlay) {
        if (bomber instanceof Bomber && ((Bomber) bomber).isImmortal()) {
            bomber.setPresentCollision(null);
            return;
        }
        double bomberLeftX = bomber.getX();
        double bomberRightX = bomber.getX() + Sprite.SCALED_SIZE - 1;
        double bomberTopY = bomber.getY();
        double bomberBottomY = bomber.getY() + Sprite.SCALED_SIZE - 1;

        ArrayList<Enemy> newEnemies = gamePlay.getMapManager().getEnemies();
        for (int i = 0; i < newEnemies.size(); i++) {
            double enemyLeftX = newEnemies.get(i).getX();
            double enemyRightX = newEnemies.get(i).getX() + Sprite.SCALED_SIZE - 1;
            double enemyTopY = newEnemies.get(i).getY();
            double enemyBottomY = newEnemies.get(i).getY() + Sprite.SCALED_SIZE - 1;
            if (!(bomberRightX < enemyLeftX || bomberLeftX > enemyRightX || bomberTopY > enemyBottomY || bomberBottomY < enemyTopY)) {
                bomber.setPresentCollision(newEnemies.get(i));
            }
        }
    }

    /**
     * Hàm trả về Collision của TileEntity mà Bomberman đang đứng lên
     *
     * @param entity
     * @param gamePlay
     * @return String là Collision của TileEntity đó
     */
    private static Entity getCollisionAtCurrentPosition(MovingEntity entity, GamePlay gamePlay) {
        return (TileEntity) gamePlay.getMapManager().getTopTileAt(entity.getXUnit(), entity.getYUnit());
    }

    /**
     * Hàm kiểm tra va chạm nếu như Entity đứng yên ko di chuyen.
     *
     * @param entity
     * @param gamePlay
     */
    public static void checkTileStable(MovingEntity entity, GamePlay gamePlay) {
        TileEntity tileEntity = (TileEntity) gamePlay.getMapManager().getTopTileAt(entity.getXUnit(), entity.getYUnit());
        if (tileEntity instanceof Bomb) {
            return;
        }
        if (entity instanceof Bomber && ((Bomber) entity).isImmortal()) {
            entity.setPresentCollision(null);
            return;
        }
        entity.setPresentCollision(tileEntity);
    }
}
