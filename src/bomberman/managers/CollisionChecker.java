package bomberman.managers;

import bomberman.ScreenController.ClassicMap;
import bomberman.entities.Entity;
import bomberman.entities.movingEntities.MovingEntity;
import bomberman.entities.tileEntities.Grass;
import bomberman.entities.tileEntities.TileEntity;
import bomberman.graphics.Sprite;

//Class kiểm tra va chạm
public class CollisionChecker {

    //Liên kết với một map cụ thể
    private ClassicMap map;
    public CollisionChecker(ClassicMap map) {
        this.map = map;
    }

    public void checkTile(MovingEntity entity) {

        //Xác định tọa độ của hitbox của entity
        int entityLeftX = entity.getX() + entity.getSolidArea().x;
        int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopY = entity.getY() + entity.getSolidArea().y;
        int entityBottomY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

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
                tileEntity1 = (TileEntity) map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityLeftCol];
                tileEntity2 = (TileEntity) map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityRightCol];

                //Nếu một trong hai tileEntity.getCollision() == 1 nghĩa là một trong hai Entity nhân vật
                //sắp va chạm có collision = 1 (ngăn cản người chơi đi qua)
                if (tileEntity1.getCollision() == 1 || tileEntity2.getCollision() == 1) {
                    //Trả về tín hiệu bị chặn cho nhân vật -> Không thể đi qua
                    entity.setCollisionOn(1);
                } else {
                    //Nếu Entity bên trái == Entity bên phải (nhân vật không phải ở giữa 2 ô)
                    if (entityLeftCol == entityRightCol) {
                        //Trả ra ngoài tín hiệu cho phép đi qua, tín hiệu nhặt được vật phẩm.
                        entity.setCollisionOn(tileEntity1.getCollision());
                        //Nếu là tín hiệu nhặt được vật phẩm thì vật phẩm đó sẽ bị xóa, thay thế bằng một ô Grass;
                        if (tileEntity1.getCollision() == 2 || tileEntity1.getCollision() == 3 || tileEntity1.getCollision() == 4){
                            Entity temp = map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityLeftCol];
                            map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityLeftCol] = new Grass(entityLeftCol, entityTopRow, map);
                            map.getStillObjects().remove(temp);
                        }
                    }
                }
                break;
            }
            case "down": {
                entityBottomRow = (entityBottomY + entity.getVelocity())/Sprite.SCALED_SIZE;
                tileEntity1 = (TileEntity) map.getTileEntityManager().getEntityMatrix()[entityBottomRow][entityLeftCol];
                tileEntity2 = (TileEntity) map.getTileEntityManager().getEntityMatrix()[entityBottomRow][entityRightCol];
                if (tileEntity1.getCollision() == 1 || tileEntity2.getCollision() == 1) {
                    entity.setCollisionOn(1);
                } else {
                    if (entityLeftCol == entityRightCol) {
                        entity.setCollisionOn(tileEntity1.getCollision());
                        if (tileEntity1.getCollision() == 2 || tileEntity1.getCollision() == 3 || tileEntity1.getCollision() == 4){
                            Entity temp = map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityLeftCol];
                            map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityLeftCol] = new Grass(entityLeftCol, entityTopRow, map);
                            map.getStillObjects().remove(temp);
                        }
                    }
                }
                break;
            }
            case "left": {
                entityLeftCol = (entityLeftX - entity.getVelocity())/Sprite.SCALED_SIZE;
                tileEntity1 = (TileEntity) map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityLeftCol];
                tileEntity2 = (TileEntity) map.getTileEntityManager().getEntityMatrix()[entityBottomRow][entityLeftCol];
                if (tileEntity1.getCollision() == 1 || tileEntity2.getCollision() == 1) {
                    entity.setCollisionOn(1);
                } else {
                    if (entityTopRow == entityBottomRow) {
                        entity.setCollisionOn(tileEntity1.getCollision());
                        if (tileEntity1.getCollision() == 2 || tileEntity1.getCollision() == 3 || tileEntity1.getCollision() == 4){
                            Entity temp = map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityLeftCol];
                            map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityLeftCol] = new Grass(entityLeftCol, entityTopRow, map);
                            map.getStillObjects().remove(temp);
                        }
                    }
                }
                break;
            }
            case "right": {
                entityRightCol = (entityRightX + entity.getVelocity())/Sprite.SCALED_SIZE;
                tileEntity1 = (TileEntity) map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityRightCol];
                tileEntity2 = (TileEntity) map.getTileEntityManager().getEntityMatrix()[entityBottomRow][entityRightCol];
                if (tileEntity1.getCollision() == 1 || tileEntity2.getCollision() == 1) {
                    entity.setCollisionOn(1);
                } else {
                    if (entityTopRow == entityBottomRow) {
                        entity.setCollisionOn(tileEntity1.getCollision());
                        if (tileEntity1.getCollision() == 2 || tileEntity1.getCollision() == 3 || tileEntity1.getCollision() == 4){
                            Entity temp = map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityLeftCol];
                            map.getTileEntityManager().getEntityMatrix()[entityTopRow][entityLeftCol] = new Grass(entityLeftCol, entityTopRow, map);
                            map.getStillObjects().remove(temp);
                        }
                    }
                }
                break;
            }
        }
    }
}
