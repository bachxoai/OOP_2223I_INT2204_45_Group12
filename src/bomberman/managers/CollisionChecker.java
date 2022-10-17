package bomberman.managers;
import bomberman.entities.moving.MovingEntity;
import bomberman.entities.moving.enemy.Enemy;
import bomberman.entities.tile.TileEntity;
import bomberman.graphics.Sprite;

import java.util.ArrayList;

//Class kiểm tra va chạm
public class CollisionChecker {

    public static String NULL_COLLISION = "null";
    public static String BOMB_COLLISION = "bomb";
    public static String FLAME_COLLISION = "flame";
    public static String WALL_COLLISION = "wall";
    public static String BRICK_COLLISION = "brick";
    public static String PORTAL_COLLISION = "portal";
    public static String BOMBS_ITEM_COLLISION = "bombsItem";
    public static String FLAMES_ITEM_COLLISION = "flamesItem";
    public static String SPEED_ITEM_COLLISION = "speedsItem";
    public static String ENEMY_COLLISION = "enemy";

    /**
     * Hàm kiểm tra va chạm với các TileEntity trong GamePlay nào đó.
     *
     * @param entity
     * @param gamePlay
     */
    public static void checkTileEntity(MovingEntity entity, GamePlay gamePlay) {
        int entityLeftX = entity.getX();
        int entityRightX = entity.getX() + Sprite.SCALED_SIZE - 9;
        int entityTopY = entity.getY();
        int entityBottomY = entity.getY() + Sprite.SCALED_SIZE - 1;

        //Xác định hàng, cột của hitbox của entity
        int entityLeftCol = entityLeftX/Sprite.SCALED_SIZE;
        int entityRightCol = entityRightX/Sprite.SCALED_SIZE;
        int entityTopRow = entityTopY/Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomY/Sprite.SCALED_SIZE;

        //Kiểm tra va chạm theo từng hướng
        switch (entity.getState()) {
            case MovingEntity.UP_STATE: {
                checkTileVertical(entity, (entityTopY - entity.getVelocity())/Sprite.SCALED_SIZE, entityLeftX, entityRightX, entityLeftCol, entityRightCol, entity, gamePlay);
                break;
            }
            case MovingEntity.DOWN_STATE: {
                checkTileVertical(entity, (entityBottomY + entity.getVelocity())/Sprite.SCALED_SIZE, entityLeftX, entityRightX, entityLeftCol, entityRightCol, entity, gamePlay);
                break;
            }
            case MovingEntity.LEFT_STATE: {
                checkTileHorizontal(entity, (entityLeftX - entity.getVelocity())/Sprite.SCALED_SIZE, entityTopRow, entityBottomRow, entityTopY, entityBottomY, entity, gamePlay);
                break;
            }
            case MovingEntity.RIGHT_STATE: {
                checkTileHorizontal(entity, (entityRightX + entity.getVelocity())/Sprite.SCALED_SIZE, entityTopRow, entityBottomRow, entityTopY, entityBottomY, entity, gamePlay);
                break;
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
        int bomberLeftX = bomber.getX();
        int bomberRightX = bomber.getX() + Sprite.SCALED_SIZE - 9;
        int bomberTopY = bomber.getY();
        int bomberBottomY = bomber.getY() + Sprite.SCALED_SIZE - 1;

        ArrayList<Enemy> newEnemies = gamePlay.getMapManager().getEnemies();
        for (int i = 0; i < newEnemies.size(); i++) {
            int enemyLeftX = newEnemies.get(i).getX();
            int enemyRightX = newEnemies.get(i).getX() + Sprite.SCALED_SIZE - 1;
            int enemyTopY = newEnemies.get(i).getY();
            int enemyBottomY = newEnemies.get(i).getY() + Sprite.SCALED_SIZE - 1;
            if (!(bomberRightX < enemyLeftX || bomberLeftX > enemyRightX || bomberTopY > enemyBottomY || bomberBottomY < enemyTopY)) {
                bomber.setPresentCollision(ENEMY_COLLISION);
            }
        }
    }

    /**
     * Hàm kiểm tra va chạm với TileEntity theo chiều dọc
     *
     * @param moving
     * @param row
     * @param entityLeftX
     * @param entityRightX
     * @param entityLeftCol
     * @param entityRightCol
     * @param entity
     * @param gamePlay
     */
    private static void checkTileVertical(MovingEntity moving, int row, int entityLeftX, int entityRightX, int entityLeftCol, int entityRightCol, MovingEntity entity, GamePlay gamePlay) {
        if (entityLeftCol == entityRightCol) {
            String collision = ((TileEntity) gamePlay.getMapManager().getTopTileAt(entityLeftCol, row)).getCollision();
            if (collision != NULL_COLLISION) {
                if (collision == BOMB_COLLISION && getCollisionAtCurrentPosition(moving, gamePlay) == BOMB_COLLISION) {
                    return;
                }
                entity.setFutureCollision(collision);
            }
        } else {
            String collision1 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(entityLeftCol, row)).getCollision();
            String collision2 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(entityRightCol, row)).getCollision();

            if (collision1 != NULL_COLLISION || collision2 != NULL_COLLISION) {
                if (collision1 != NULL_COLLISION && collision2 == NULL_COLLISION && entityRightCol * Sprite.SCALED_SIZE - entityLeftX <= 8) {
                    entity.setX(entityRightCol * Sprite.SCALED_SIZE);
                } else if (collision2 != NULL_COLLISION && collision1 == NULL_COLLISION && entityRightX - entityLeftCol * Sprite.SCALED_SIZE - 31 <= 8) {
                    entity.setX(entityLeftCol * Sprite.SCALED_SIZE + 9);
                } else {
                    entity.setFutureCollision((collision1 != NULL_COLLISION) ? collision1 : collision2);
                }
            }
        }
    }

    /**
     * Hàm kiểm tra va chạm với TileEntity theo chiều ngang
     *
     * @param moving
     * @param col
     * @param entityTopRow
     * @param entityBottomRow
     * @param entityTopY
     * @param entityBottomY
     * @param entity
     * @param gamePlay
     */
    private static void checkTileHorizontal(MovingEntity moving, int col, int entityTopRow, int entityBottomRow, int entityTopY, int entityBottomY, MovingEntity entity, GamePlay gamePlay) {
        if (entityTopRow == entityBottomRow) {
            String collision = ((TileEntity) gamePlay.getMapManager().getTopTileAt(col, entityTopRow)).getCollision();
            if (collision != NULL_COLLISION) {
                if (collision == BOMB_COLLISION && getCollisionAtCurrentPosition(moving, gamePlay) == BOMB_COLLISION) {
                    return;
                }
                entity.setFutureCollision(collision);
            }
        } else {
            String collision1 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(col, entityTopRow)).getCollision();
            String collision2 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(col, entityBottomRow)).getCollision();

            if (collision1 != NULL_COLLISION || collision2 != NULL_COLLISION) {
                if (collision1 != NULL_COLLISION && collision2 == NULL_COLLISION && entityBottomRow * Sprite.SCALED_SIZE - entityTopY <= 8) {
                    entity.setY(entityBottomRow * Sprite.SCALED_SIZE);
                } else if (collision2 != NULL_COLLISION && collision1 == NULL_COLLISION && entityBottomY - entityTopRow * Sprite.SCALED_SIZE - 31 <= 8) {
                    entity.setY(entityTopRow * Sprite.SCALED_SIZE);
                } else {
                    entity.setFutureCollision((collision1 != NULL_COLLISION) ? collision1 : collision2);
                }
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
    private static String getCollisionAtCurrentPosition(MovingEntity entity, GamePlay gamePlay) {
        TileEntity tileEntity = (TileEntity) gamePlay.getMapManager().getTopTileAt(entity.getXUnit(), entity.getYUnit());
        return tileEntity.getCollision();
    }

    /**
     * Hàm kiểm tra va chạm nếu như Entity đứng yên ko di chuyen.
     *
     * @param entity
     * @param gamePlay
     */
    public static void checkTileStable(MovingEntity entity, GamePlay gamePlay) {
        TileEntity tileEntity = (TileEntity) gamePlay.getMapManager().getTopTileAt(entity.getXUnit(), entity.getYUnit());
        if (tileEntity.getCollision() == BOMB_COLLISION) {
            return;
        }
        entity.setPresentCollision(tileEntity.getCollision());
    }

    /**
     * Hàm kiểm tra va chạm các ô xung quanh trả về 1 xâu. Lần lượt top,bot,left,right. Nếu bị chặn thì là 1, ngược lại thì là 0
     * VD: 0011: top và bot không bị chặn, left và right bị chặn
     * @param enemy
     * @param gamePlay
     * @return
     */
    public static String getCollisionAround(Enemy enemy, GamePlay gamePlay) {
        String top = gamePlay.getMapManager().getTopTileAt(enemy.getXUnit(), enemy.getYUnit() - 1).getCollision();
        String bottom = gamePlay.getMapManager().getTopTileAt(enemy.getXUnit(), enemy.getYUnit() + 1).getCollision();
        String left = gamePlay.getMapManager().getTopTileAt(enemy.getXUnit() - 1, enemy.getYUnit()).getCollision();
        String right = gamePlay.getMapManager().getTopTileAt(enemy.getXUnit() + 1, enemy.getYUnit()).getCollision();

        return getCharForGetCollisionAround(top) + getCharForGetCollisionAround(bottom)
                + getCharForGetCollisionAround(left) + getCharForGetCollisionAround(right);
    }

    private static String getCharForGetCollisionAround(String s) {
        if (s != BRICK_COLLISION && s != WALL_COLLISION && s != BOMB_COLLISION) {
            return "0";
        } else {
            return "1";
        }
    }
}
