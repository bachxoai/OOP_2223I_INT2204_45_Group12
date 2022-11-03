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
     * Hàm kiểm tra va chạm với các TileEntity trong mapManager nào đó.
     *
     * @param entity
     * @param mapManager
     */
    public static void checkTileEntity(MovingEntity entity,MapManager mapManager) {
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
                checkTileVertical(entity, (int) (entityTopY - entity.getVelocity())/Sprite.SCALED_SIZE, entityLeftX, entityRightX, entityLeftCol, entityRightCol, mapManager);
                break;
            }
            case MovingEntity.DOWN_STATE: {
                checkTileVertical(entity, (int) (entityBottomY + entity.getVelocity())/Sprite.SCALED_SIZE, entityLeftX, entityRightX, entityLeftCol, entityRightCol, mapManager);
                break;
            }
            case MovingEntity.LEFT_STATE: {
                checkTileHorizontal(entity, (int) (entityLeftX - entity.getVelocity())/Sprite.SCALED_SIZE, entityTopRow, entityBottomRow, entityTopY, entityBottomY, mapManager);
                break;
            }
            case MovingEntity.RIGHT_STATE: {
                checkTileHorizontal(entity,  (int ) (entityRightX + entity.getVelocity())/Sprite.SCALED_SIZE, entityTopRow, entityBottomRow, entityTopY, entityBottomY, mapManager);
                break;
            }
        }
    }

    private static void checkTileVertical(MovingEntity moving, int row, double entityLeftX, double entityRightX, int entityLeftCol, int entityRightCol,MapManager mapManager) {
        if (entityLeftCol == entityRightCol) {
            checkOneBlock(mapManager, moving, entityLeftCol, row);
        } else {
            checkTwoBlocks(mapManager, moving, entityLeftCol, entityRightCol, row, row, entityLeftCol, entityRightCol, entityLeftX, entityRightX);
        }
    }

    private static void checkTileHorizontal(MovingEntity moving, int col, int entityTopRow, int entityBottomRow, double entityTopY, double entityBottomY,MapManager mapManager) {
        if (entityTopRow == entityBottomRow) {
            checkOneBlock(mapManager, moving, col, entityTopRow);
        } else {
            checkTwoBlocks(mapManager, moving, col, col, entityTopRow, entityBottomRow, entityTopRow, entityBottomRow, entityTopY, entityBottomY);
        }
    }

    private static void checkOneBlock(MapManager mapManager, MovingEntity moving, int col, int row) {
        Entity collision = ((TileEntity) mapManager.getTopTileAt(col, row));
        if (moving instanceof Bomber) {
            if (((Bomber) moving).isCanWalkThroughBrick() && collision instanceof Brick
                    || ((Bomber) moving).isCanWalkThroughBomb() && collision instanceof Bomb
                    || ((TileEntity) mapManager.getTopTileAt(moving.getXUnit(), moving.getYUnit()) instanceof Bomb
                    && collision instanceof Bomb)) {
                collision = mapManager.getTempGrass();
            }
        }
        if (!(collision instanceof Grass)) {
            moving.setFutureCollision(collision);
        }
    }

    private static void checkTwoBlocks(MapManager mapManager, MovingEntity moving, int leftCol, int rightCol, int topRow, int bottomRow
            , int smallModifyCR, int bigModifyCR, double smallModifyXY, double bigModifyXY) {
        Entity collision1 = ((TileEntity) mapManager.getTopTileAt(leftCol, topRow));
        Entity collision2 = ((TileEntity) mapManager.getTopTileAt(rightCol, bottomRow));

        if (moving instanceof Bomber) {
            if (((Bomber) moving).isCanWalkThroughBrick()) {
                if (collision1 instanceof Brick) {
                    collision1 = mapManager.getTempGrass();
                }
                if (collision2 instanceof Brick) {
                    collision2 = mapManager.getTempGrass();
                }
            }
            if (((Bomber) moving).isCanWalkThroughBomb()) {
                if (collision1 instanceof Bomb) {
                    collision1 = mapManager.getTempGrass();
                }
                if (collision2 instanceof Bomb) {
                    collision2 = mapManager.getTempGrass();
                }
            }
        }

        if (!(collision1 instanceof Grass) || !(collision2 instanceof Grass)) {
            if (!(collision1 instanceof Grass) && (collision2 instanceof Grass) && bigModifyCR * Sprite.SCALED_SIZE - smallModifyXY <= 8) {
                if (topRow == bottomRow) {
                    moving.setX(bigModifyCR * Sprite.SCALED_SIZE);
                }
                if (leftCol == rightCol) {
                    moving.setY(bigModifyCR * Sprite.SCALED_SIZE);
                }
            } else if (!(collision2 instanceof Grass) && (collision1 instanceof Grass) && bigModifyXY - smallModifyCR * Sprite.SCALED_SIZE - 31 <= 8) {
                if (topRow == bottomRow) {
                    moving.setX(smallModifyCR * Sprite.SCALED_SIZE);
                }
                if (leftCol == rightCol) {
                    moving.setY(smallModifyCR * Sprite.SCALED_SIZE);
                }
            } else {
                moving.setFutureCollision((!(collision1 instanceof Grass)) ? collision1 : collision2);
            }
        }
    }

    /**
     * Hàm kiểm tra va chạm bomberman với các enemies trong một mapManager nào đó
     *
     * @param bomber Tham chiếu bomberman
     * @param mapManager tham chiếu đến mapManager hện tại
     */
    public static void checkMovingEntity(MovingEntity bomber, MapManager mapManager) {
        if (bomber instanceof Bomber && ((Bomber) bomber).isImmortal()) {
            bomber.setPresentCollision(mapManager.getTempGrass());
            return;
        }
        double bomberLeftX = bomber.getX();
        double bomberRightX = bomber.getX() + Sprite.SCALED_SIZE - 1;
        double bomberTopY = bomber.getY();
        double bomberBottomY = bomber.getY() + Sprite.SCALED_SIZE - 1;

        ArrayList<Enemy> newEnemies = mapManager.getEnemies();
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
     * Hàm kiểm tra va chạm nếu như Entity đứng yên ko di chuyen.
     *
     * @param entity
     * @param mapManager
     */
    public static void checkTileStable(MovingEntity entity, MapManager mapManager) {
        TileEntity tileEntity = (TileEntity) mapManager.getTopTileAt(entity.getXUnit(), entity.getYUnit());
        if (tileEntity instanceof Bomb) {
            return;
        }
        if (entity instanceof Bomber && ((Bomber) entity).isImmortal()) {
            entity.setPresentCollision(mapManager.getTempGrass());
            return;
        }
        entity.setPresentCollision(tileEntity);
    }
}
