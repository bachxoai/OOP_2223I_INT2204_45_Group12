package bomberman.managers;
import bomberman.entities.moving.MovingEntity;
import bomberman.entities.moving.enemy.Enemy;
import bomberman.entities.tile.TileEntity;
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
        int entityLeftX = entity.getX();
        int entityRightX = entity.getX() + Sprite.SCALED_SIZE - 9;
        int entityTopY = entity.getY();
        int entityBottomY = entity.getY() + Sprite.SCALED_SIZE - 1;

        //Xác định hàng, cột của hitbox của entity
        int entityLeftCol = entityLeftX/Sprite.SCALED_SIZE;
        int entityRightCol = entityRightX/Sprite.SCALED_SIZE;
        int entityTopRow = entityTopY/Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomY/Sprite.SCALED_SIZE;

        checkTileStable(entity, gamePlay);
        //Kiểm tra va chạm theo từng hướng
        switch (entity.getDirection()) {
            case "up": {
                checkTileVertical(entity, (entityTopY - entity.getVelocity())/Sprite.SCALED_SIZE, entityLeftX, entityRightX, entityLeftCol, entityRightCol, entity, gamePlay);
                break;
            }
            case "down": {
                checkTileVertical(entity, (entityBottomY + entity.getVelocity())/Sprite.SCALED_SIZE, entityLeftX, entityRightX, entityLeftCol, entityRightCol, entity, gamePlay);
                break;
            }
            case "left": {
                checkTileHorizontal(entity, (entityLeftX - entity.getVelocity())/Sprite.SCALED_SIZE, entityTopRow, entityBottomRow, entityTopY, entityBottomY, entity, gamePlay);
                break;
            }
            case "right": {
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
                bomber.setCollisionStatus("getAttacked");
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
            if (collision != "null") {
                if (collision == "bomb" && getCollisionAtCurrentPosition(moving, gamePlay) == "bomb") {
                    return;
                }
                entity.setCollisionStatus(collision);
            }
        } else {
            String collision1 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(entityLeftCol, row)).getCollision();
            String collision2 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(entityRightCol, row)).getCollision();

            if (collision1 != "null" || collision2 != "null") {
                if (collision1 != "null" && collision2 == "null" && entityRightCol * Sprite.SCALED_SIZE - entityLeftX <= 8) {
                    entity.setX(entityRightCol * Sprite.SCALED_SIZE);
                } else if (collision2 != "null" && collision1 == "null" && entityRightX - entityLeftCol * Sprite.SCALED_SIZE - 31 <= 8) {
                    entity.setX(entityLeftCol * Sprite.SCALED_SIZE + 9);
                } else {
                    entity.setCollisionStatus((collision1 != "null") ? collision1 : collision2);
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
            if (collision != "null") {
                if (collision == "bomb" && getCollisionAtCurrentPosition(moving, gamePlay) == "bomb") {
                    return;
                }
                entity.setCollisionStatus(collision);
            }
        } else {
            String collision1 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(col, entityTopRow)).getCollision();
            String collision2 = ((TileEntity) gamePlay.getMapManager().getTopTileAt(col, entityBottomRow)).getCollision();

            if (collision1 != "null" || collision2 != "null") {
                if (collision1 != "null" && collision2 == "null" && entityBottomRow * Sprite.SCALED_SIZE - entityTopY <= 8) {
                    entity.setY(entityBottomRow * Sprite.SCALED_SIZE);
                } else if (collision2 != "null" && collision1 == "null" && entityBottomY - entityTopRow * Sprite.SCALED_SIZE - 31 <= 8) {
                    entity.setY(entityTopRow * Sprite.SCALED_SIZE);
                } else {
                    entity.setCollisionStatus((collision1 != "null") ? collision1 : collision2);
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
     * Hàm kiểm tra va chạm nếu như Bomberman đứng yên.
     *
     * @param entity
     * @param gamePlay
     */
    private static void checkTileStable(MovingEntity entity, GamePlay gamePlay) {
        TileEntity tileEntity = (TileEntity) gamePlay.getMapManager().getTopTileAt(entity.getXUnit(), entity.getYUnit());
        if (tileEntity.getCollision() == "bomb") {
            return;
        }
        entity.setCollisionStatus(tileEntity.getCollision());
    }
}
