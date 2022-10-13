package bomberman.managers;

import bomberman.entities.movingEntities.MovingEntity;
import bomberman.entities.tileentities.TileEntity;
import bomberman.graphics.Sprite;

//Class kiểm tra va chạm
public class CollisionChecker {

    public static void checkTileEntity(MovingEntity entity, GamePlay gamePlay) {
        int entityLeftX = entity.getX();
        int entityRightX = entity.getX() + Sprite.SCALED_SIZE - 8;
        int entityTopY = entity.getY();
        int entityBottomY = entity.getY() + Sprite.SCALED_SIZE;

        //Xác định hàng, cột của hitbox của entity
        int entityLeftCol = entityLeftX/Sprite.SCALED_SIZE;
        int entityRightCol = entityRightX/Sprite.SCALED_SIZE;
        int entityTopRow = entityTopY/Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomY/Sprite.SCALED_SIZE;

        //Kiểm tra va chạm theo từng hướng
        switch (entity.getDirection()) {
            case "up": {
                checkTileVertical((entityTopY - entity.getVelocity())/Sprite.SCALED_SIZE, entityLeftX, entityRightX, entityLeftCol, entityRightCol, entity, gamePlay);
                break;
            }
            case "down": {
                checkTileVertical((entityBottomY + entity.getVelocity())/Sprite.SCALED_SIZE, entityLeftX, entityRightX, entityLeftCol, entityRightCol, entity, gamePlay);
                break;
            }
            case "left": {
                checkTileHorizontal((entityLeftX - entity.getVelocity())/Sprite.SCALED_SIZE, entityTopRow, entityBottomRow, entityTopY, entityBottomY, entity, gamePlay);
                break;
            }
            case "right": {
                checkTileHorizontal((entityRightX + entity.getVelocity())/Sprite.SCALED_SIZE, entityTopRow, entityBottomRow, entityTopY, entityBottomY, entity, gamePlay);
                break;
            }
        }
    }

    public static void checkMovingEntity() {}

    private static void checkTileVertical(int row, int entityLeftX, int entityRightX, int entityLeftCol, int entityRightCol, MovingEntity entity, GamePlay gamePlay) {
        TileEntity tileEntity1 = (TileEntity) gamePlay.getMapManager().getTopTileAt(entityLeftCol, row);
        TileEntity tileEntity2 = (TileEntity) gamePlay.getMapManager().getTopTileAt(entityRightCol, row);

        if (tileEntity1.getCollision() == 1 || tileEntity2.getCollision() == 1) {
            if (tileEntity1.getCollision() == 1 && tileEntity2.getCollision() != 1 && entityRightCol * Sprite.SCALED_SIZE - entityLeftX <= 8) {
                entity.setX(entityRightCol * Sprite.SCALED_SIZE);
            } else if (tileEntity2.getCollision() == 1 && tileEntity1.getCollision() != 1 && entityRightX - entityLeftCol * Sprite.SCALED_SIZE - 31 <= 8) {
                entity.setX(entityLeftCol * Sprite.SCALED_SIZE + 8);
            } else {
                entity.setCollisionOn(1);
            }
        }
    }

    private static void checkTileHorizontal(int col, int entityTopRow, int entityBottomRow, int entityTopY, int entityBottomY, MovingEntity entity, GamePlay gamePlay) {
        TileEntity tileEntity1 = (TileEntity) gamePlay.getMapManager().getTopTileAt(col, entityTopRow);
        TileEntity tileEntity2 = (TileEntity) gamePlay.getMapManager().getTopTileAt(col, entityBottomRow);

        if (tileEntity1.getCollision() == 1 || tileEntity2.getCollision() == 1) {
            if (tileEntity1.getCollision() == 1 && tileEntity2.getCollision() != 1 && entityBottomRow * Sprite.SCALED_SIZE - entityTopY <= 8) {
                entity.setY(entityBottomRow * Sprite.SCALED_SIZE);
            } else if (tileEntity2.getCollision() == 1 && tileEntity1.getCollision() != 1 && entityBottomY - entityTopRow * Sprite.SCALED_SIZE - 31 <= 8) {
                entity.setY(entityTopRow * Sprite.SCALED_SIZE);
            } else {
                entity.setCollisionOn(1);
            }
        }
    }
}
