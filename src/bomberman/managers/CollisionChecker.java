package bomberman.managers;

import bomberman.entities.Entity;
import bomberman.entities.movingEntities.MovingEntity;
import bomberman.entities.tileEntities.Grass;
import bomberman.entities.tileEntities.TileEntity;
import bomberman.graphics.Sprite;

//Class kiểm tra va chạm
public class CollisionChecker {

    //Liên kết với một map cụ thể
    private GamePlay gamePlay;
    public CollisionChecker(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public void checkTile(MovingEntity entity) {
        int entityLeftX = entity.getX();
        int entityRightX = entity.getX() + Sprite.SCALED_SIZE;
        int entityTopY = entity.getY();
        int entityBottomY = entity.getY() + Sprite.SCALED_SIZE;

        //Xác định hàng, cột của hitbox của entity
        int entityLeftCol = entityLeftX/Sprite.SCALED_SIZE;
        int entityRightCol = entityRightX/Sprite.SCALED_SIZE;
        int entityTopRow = entityTopY/Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomY/Sprite.SCALED_SIZE;

        //Tạo hai Entity phụ để kiểm tra va chạm
        TileEntity tileEntity1, tileEntity2;

        //Kiểm tra va chạm theo từng hướng
        switch (entity.getDirection()) {
            case "up": {
                //Tính vị trí sắp tới của nhân vật
                entityTopRow = (entityTopY - entity.getVelocity())/Sprite.SCALED_SIZE;

                //Tạo ra hai Entity phụ (ở bên trái và phải của nhân vật) để lưu Entity trong Map mà nhân vật sắp va chạm.
                tileEntity1 = (TileEntity) gamePlay.getStillObjectAt(entityLeftCol, entityTopRow);
                tileEntity2 = (TileEntity) gamePlay.getStillObjectAt(entityRightCol, entityTopRow);

                    //Nếu một trong hai tileEntity.getCollision() == 1 nghĩa là một trong hai Entity nhân vật
                    //sắp va chạm có collision = 1 (ngăn cản người chơi đi qua)
                    if (tileEntity1.getCollision() == 1 || tileEntity2.getCollision() == 1) {
                        if (tileEntity1.getCollision() == 1 && tileEntity2.getCollision() != 1 && entityRightCol * Sprite.SCALED_SIZE - entityLeftX <= 8) {
                            entity.setX(entityRightCol * Sprite.SCALED_SIZE);
                        } else if (tileEntity2.getCollision() == 1 && tileEntity1.getCollision() != 1 && entityRightX - entityLeftCol * Sprite.SCALED_SIZE - 31 <= 8) {
                            entity.setX(entityLeftCol * Sprite.SCALED_SIZE);
                        } else {
                            entity.setCollisionOn(1);
                        }
                    }
                break;
            }
            case "down": {
                entityBottomRow = (entityBottomY + entity.getVelocity())/Sprite.SCALED_SIZE;
                tileEntity1 = (TileEntity) gamePlay.getStillObjectAt(entityLeftCol, entityBottomRow);
                tileEntity2 = (TileEntity) gamePlay.getStillObjectAt(entityRightCol, entityBottomRow);

                if (tileEntity1.getCollision() == 1 || tileEntity2.getCollision() == 1) {
                    if (tileEntity1.getCollision() == 1 && tileEntity2.getCollision() != 1 && entityRightCol * Sprite.SCALED_SIZE - entityLeftX <= 8) {
                        entity.setX(entityRightCol * Sprite.SCALED_SIZE);
                    } else if (tileEntity2.getCollision() == 1 && tileEntity1.getCollision() != 1 && entityRightX - entityLeftCol * Sprite.SCALED_SIZE - 31 <= 8) {
                        entity.setX(entityLeftCol * Sprite.SCALED_SIZE);
                    } else {
                        entity.setCollisionOn(1);
                    }
                }
                break;
            }
            case "left": {
                entityLeftCol = (entityLeftX - entity.getVelocity())/Sprite.SCALED_SIZE;
                tileEntity1 = (TileEntity) gamePlay.getStillObjectAt(entityLeftCol, entityTopRow);
                tileEntity2 = (TileEntity) gamePlay.getStillObjectAt(entityLeftCol, entityBottomRow);

                if (tileEntity1.getCollision() == 1 || tileEntity2.getCollision() == 1) {
                    if (tileEntity1.getCollision() == 1 && tileEntity2.getCollision() != 1 && entityBottomRow * Sprite.SCALED_SIZE - entityTopY <= 8) {
                        entity.setY(entityBottomRow * Sprite.SCALED_SIZE);
                    } else if (tileEntity2.getCollision() == 1 && tileEntity1.getCollision() != 1 && entityBottomY - entityTopRow * Sprite.SCALED_SIZE - 31 <= 8) {
                        entity.setY(entityTopRow * Sprite.SCALED_SIZE);
                    } else {
                        entity.setCollisionOn(1);
                    }
                }
                break;
            }
            case "right": {
                entityRightCol = (entityRightX + entity.getVelocity())/Sprite.SCALED_SIZE;
                tileEntity1 = (TileEntity) gamePlay.getStillObjectAt(entityRightCol, entityTopRow);
                tileEntity2 = (TileEntity) gamePlay.getStillObjectAt(entityRightCol, entityBottomRow);

                if (tileEntity1.getCollision() == 1 || tileEntity2.getCollision() == 1) {
                    if (tileEntity1.getCollision() == 1 && tileEntity2.getCollision() != 1 && entityBottomRow * Sprite.SCALED_SIZE - entityTopY <= 8) {
                        entity.setY(entityBottomRow * Sprite.SCALED_SIZE);
                    } else if (tileEntity2.getCollision() == 1 && tileEntity1.getCollision() != 1 && entityBottomY - entityTopRow * Sprite.SCALED_SIZE - 31 <= 8) {
                        entity.setY(entityTopRow * Sprite.SCALED_SIZE);
                    } else {
                        entity.setCollisionOn(1);
                    }
                }
                break;
            }
        }
    }
}
