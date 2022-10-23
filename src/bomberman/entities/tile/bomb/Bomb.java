package bomberman.entities.tile.bomb;

import bomberman.managers.Sound;
import bomberman.managers.SoundEffect;
import bomberman.screen.levelscreen.InformationPane;
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
    public static final int TIME_TO_EXPLODE = 120;
    public static final int ANIMATED_FRAME = 10;
    int range;
    int timeToExplode;
    Sprite[] bombs;

    public Bomb(int xUnit, int yUnit, GamePlay gamePlay, int range) {
        super(xUnit, yUnit, gamePlay);
        this.range = range;
        img = Sprite.bomb.getFxImage();
        bombs = new Sprite[4];
        bombs[0] = Sprite.bomb;
        bombs[1] = Sprite.bomb_1;
        bombs[2] = Sprite.bomb_2;
        bombs[3] = Sprite.bomb_1;
        timeToExplode = TIME_TO_EXPLODE;
        collision = CollisionChecker.BOMB_COLLISION;
    }

    @Override
    public void update() {
        if (timeToExplode > 0) {
            timeToExplode--;
            img = bombs[(timeToExplode / ANIMATED_FRAME) % 4].getFxImage();
        } else if (timeToExplode == 0) {
            handleAfterExplosion();
        }
    }

    private void handleAfterExplosion() {
        new Explosion(getXUnit(), getYUnit(), gamePlay,
                Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2);
        createExplosion();
        gamePlay.getMapManager().getTilesAt(getXUnit(), getYUnit()).remove(this);
        int addedBombNums = gamePlay.getBomberman().getBombNums() + 1;
        gamePlay.getBomberman().setBombNums(addedBombNums);
        gamePlay.getContainedLevelScreen().setBomberStat(InformationPane.BOMBNO, addedBombNums);
        if(SoundEffect.hasSoundEffect) {
            SoundEffect.playSE(SoundEffect.bombExplosion);
        }
    }

    /**
     * Tạo ra các đối tượng Explosion khi hết thời gian charge của bomb.
     */
    private void createExplosion() {
        // nếu muốn cho bom phá xuyên tường thì chỉ cần thêm đk ở
//        if (flameBlocked(getXUnit(), getYUnit() + i) && cannotPierce) {
//            break;
//        }
        // DOWN
        for (int i = 1; i <= range; i++) {
            if (flameBlocked(getXUnit(), getYUnit() + i)) {
                break;
            }
            if (i == range) {
                new Explosion(getXUnit(), getYUnit() + range, gamePlay,
                        Sprite.explosion_vertical_down_last,
                        Sprite.explosion_vertical_down_last1,
                        Sprite.explosion_vertical_down_last2);
                break;
            }
            new Explosion(getXUnit(), getYUnit() + i, gamePlay,
                    Sprite.explosion_vertical,
                    Sprite.explosion_vertical1,
                    Sprite.explosion_vertical2);
        }

        // UP
        for (int i = 1; i <= range; i++) {
            if (flameBlocked(getXUnit(), getYUnit() - i)) {
                break;
            }
            if (i == range) {
                new Explosion(getXUnit(), getYUnit() - range, gamePlay,
                        Sprite.explosion_vertical_top_last,
                        Sprite.explosion_vertical_top_last1,
                        Sprite.explosion_vertical_top_last2);
                break;
            }
            new Explosion(getXUnit(), getYUnit() - i, gamePlay,
                    Sprite.explosion_vertical,
                    Sprite.explosion_vertical1,
                    Sprite.explosion_vertical2);
        }

        // LEFT
        for (int i = 1; i <= range; i++) {
            if (flameBlocked(getXUnit() - i, getYUnit())) {
                break;
            }
            if (i == range) {
                new Explosion(getXUnit() - range, getYUnit(), gamePlay,
                        Sprite.explosion_horizontal_left_last,
                        Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2);
                break;
            }
            new Explosion(getXUnit() - i, getYUnit(), gamePlay,
                    Sprite.explosion_horizontal,
                    Sprite.explosion_horizontal1,
                    Sprite.explosion_horizontal2);
        }

        // RIGHT
        for (int i = 1; i <= range; i++) {
            if (flameBlocked(getXUnit() + i, getYUnit())) {
                break;
            }
            if (i == range) {
                new Explosion(getXUnit() + range, getYUnit(), gamePlay,
                        Sprite.explosion_horizontal_right_last,
                        Sprite.explosion_horizontal_right_last1,
                        Sprite.explosion_horizontal_right_last2);
                break;
            }
            new Explosion(getXUnit() + i, getYUnit(), gamePlay,
                    Sprite.explosion_horizontal,
                    Sprite.explosion_horizontal1,
                    Sprite.explosion_horizontal2);
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

    private boolean flameBlocked(int x, int y) {
        Entity e = gamePlay.getMapManager().getTopTileAt(x, y);
        if (e instanceof Brick) {
            createExplodedBrick(x, y);
            return true;
        }
        return e instanceof Wall;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, getX(), getY());
    }
}