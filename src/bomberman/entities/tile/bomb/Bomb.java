package bomberman.entities.tile.bomb;

import bomberman.entities.DynamicEntity;
import bomberman.entities.Entity;
import bomberman.entities.tile.Brick;
import bomberman.entities.tile.TileEntity;
import bomberman.entities.tile.Wall;
import bomberman.graphics.Sprite;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import javafx.scene.canvas.GraphicsContext;

/**
 * Charging bomb class.
 */
public class Bomb extends TileEntity implements DynamicEntity {
    public static final int TIME_TO_EXPLODE = 200;
    public static final int ANIMATED_FRAME = 15;
    int range = 4;
    int timeToExplode;
    Sprite[] bombs;
    public Bomb(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.bomb.getFxImage();
        bombs = new Sprite[4];
        bombs[0] = Sprite.bomb;
        bombs[1] = Sprite.bomb_1;
        bombs[2] = Sprite.bomb_2;
        bombs[3] = Sprite.bomb_1;
        timeToExplode = TIME_TO_EXPLODE;
        gamePlay.getMapManager().addTileEntity(this);
        collision = CollisionChecker.BOMB_COLLISION;
    }

    @Override
    public void update() {
        if (timeToExplode > 0) {
            timeToExplode--;
            img = bombs[(timeToExplode / ANIMATED_FRAME) % 4].getFxImage();
        } else {
            new Explosion(getXUnit(), getYUnit(), gamePlay,
                    Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2);
            createExplosion();
            gamePlay.getMapManager().getTilesAt(getXUnit(), getYUnit()).remove(this);
        }
    }

    /**
     * Tạo ra các đối tượng Explosion khi hết thời gian charge của bomb.
     */
    private void createExplosion() {
        // Down
        boolean blocked = false;
        int blockPos = 0;
        for (int i = 1; i <= range; i++) {
            if (gamePlay.getMapManager().getTopTileAt(getXUnit(), getYUnit() + i)
                    instanceof Brick) {
                blockPos = i;
                blocked = true;
                break;
            }
            if (gamePlay.getMapManager().getTopTileAt(getXUnit(), getYUnit() + i)
                    instanceof ExplosionBrick) {
                blockPos = i;
                blocked = true;
                break;
            }
            if (gamePlay.getMapManager().getTopTileAt(getXUnit(), getYUnit() + i)
                    instanceof Wall) {
                blocked = true;
                blockPos = i;
                break;
            }
            if (i == range) {
                break;
            }
            new Explosion(getXUnit(), getYUnit() + i, gamePlay,
                    Sprite.explosion_vertical,
                    Sprite.explosion_vertical1,
                    Sprite.explosion_vertical2);
        }
        if (!blocked) {
            new Explosion(getXUnit(), getYUnit() + range, gamePlay,
                    Sprite.explosion_vertical_down_last,
                    Sprite.explosion_vertical_down_last1,
                    Sprite.explosion_vertical_down_last2);
        } else {
            if (gamePlay.getMapManager().getTopTileAt(getXUnit(), getYUnit() + blockPos)
                    instanceof Brick) {
                createExplodedBrick(getXUnit(), getYUnit() + blockPos);
            }
        }

        // Right
        blocked = false;
        for (int i = 1; i <= range; i++) {
            if (gamePlay.getMapManager().getTopTileAt(getXUnit() + i, getYUnit())
                    instanceof Brick) {
                blockPos = i;
                blocked = true;
                break;
            }
            if (gamePlay.getMapManager().getTopTileAt(getXUnit() + i, getYUnit())
                    instanceof ExplosionBrick) {
                blockPos = i;
                blocked = true;
                break;
            }
            if (gamePlay.getMapManager().getTopTileAt(getXUnit() + i, getYUnit())
                    instanceof Wall) {
                blocked = true;
                blockPos = i;
                break;
            }
            if (i == range) {
                break;
            }
            new Explosion(getXUnit() + i, getYUnit(), gamePlay,
                    Sprite.explosion_horizontal,
                    Sprite.explosion_horizontal1,
                    Sprite.explosion_horizontal2);
        }
        if (!blocked) {
            new Explosion(getXUnit() + range, getYUnit(), gamePlay,
                    Sprite.explosion_horizontal_right_last,
                    Sprite.explosion_horizontal_right_last1,
                    Sprite.explosion_horizontal_right_last1);
        } else {
            if (gamePlay.getMapManager().getTopTileAt(getXUnit() + blockPos, getYUnit())
                    instanceof Brick) {
                createExplodedBrick(getXUnit() + blockPos, getYUnit());
            }
        }

        // Top
        blocked = false;
        for (int i = 1; i <= range; i++) {
            if (gamePlay.getMapManager().getTopTileAt(getXUnit(), getYUnit() - i)
                    instanceof Brick) {
                blockPos = i;
                blocked = true;
                break;
            }
            if (gamePlay.getMapManager().getTopTileAt(getXUnit(), getYUnit() - i)
                    instanceof ExplosionBrick) {
                blockPos = i;
                blocked = true;
                break;
            }
            if (gamePlay.getMapManager().getTopTileAt(getXUnit(), getYUnit() - i)
                    instanceof Wall) {
                blocked = true;
                blockPos = i;
                break;
            }
            if (i == range) {
                break;
            }
            new Explosion(getXUnit(), getYUnit() - i, gamePlay,
                    Sprite.explosion_vertical,
                    Sprite.explosion_vertical1,
                    Sprite.explosion_vertical2);
        }
        if (!blocked) {
            new Explosion(getXUnit(), getYUnit() - range, gamePlay,
                    Sprite.explosion_vertical_top_last,
                    Sprite.explosion_vertical_top_last1,
                    Sprite.explosion_vertical_top_last2);
        } else {
            if (gamePlay.getMapManager().getTopTileAt(getXUnit(), getYUnit() - blockPos)
                    instanceof Brick) {
                createExplodedBrick(getXUnit(), getYUnit() - blockPos);
            }
        }

        // Left
        blocked = false;
        for (int i = 1; i <= range; i++) {
            if (gamePlay.getMapManager().getTopTileAt(getXUnit() - i, getYUnit())
                    instanceof Brick) {
                blockPos = i;
                blocked = true;
                break;
            }
            if (gamePlay.getMapManager().getTopTileAt(getXUnit() - i, getYUnit())
                    instanceof ExplosionBrick) {
                blockPos = i;
                blocked = true;
                break;
            }
            if (gamePlay.getMapManager().getTopTileAt(getXUnit() - i, getYUnit())
                    instanceof Wall) {
                blocked = true;
                blockPos = i;
                break;
            }
            if (i == range) {
                break;
            }
            new Explosion(getXUnit() - i, getYUnit(), gamePlay,
                    Sprite.explosion_horizontal,
                    Sprite.explosion_horizontal1,
                    Sprite.explosion_horizontal2);
        }
        if (!blocked) {
            new Explosion(getXUnit() - range, getYUnit(), gamePlay,
                    Sprite.explosion_horizontal_left_last,
                    Sprite.explosion_horizontal_left_last1,
                    Sprite.explosion_horizontal_left_last2);
        } else {
            if (gamePlay.getMapManager().getTopTileAt(getXUnit() - blockPos, getYUnit())
                    instanceof Brick) {
                createExplodedBrick(getXUnit() - blockPos, getYUnit());
            }
        }
    }

    /**
     * Xử lí gạch vỡ khi bị bom phá.
     * Thay Brick bằng ExplosionBrick.
     *
     * @param x toạ độ x của gạch bị bom phá
     * @param y toạ độ y của gạch bị bom phá.
     */
    private void createExplodedBrick(int x, int y) {
        Entity tmp = gamePlay.getMapManager().getTopTileAt(x, y);
        gamePlay.getMapManager().getTilesAt(x, y).remove(tmp);
        new ExplosionBrick(x, y, gamePlay);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, getX(), getY());
    }
}