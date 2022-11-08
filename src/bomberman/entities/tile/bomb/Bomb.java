package bomberman.entities.tile.bomb;

import bomberman.entities.DynamicEntity;
import bomberman.entities.moving.Bomber;
import bomberman.entities.moving.MovingEntity;
import bomberman.entities.tile.TileEntity;
import bomberman.graphics.Sprite;
import bomberman.managers.*;
import bomberman.screen.levelscreen.InformationPane;
import bomberman.sounds.SoundEffect;
import bomberman.sounds.SoundManager;

/**
 * Charging bomb class.
 */
public class Bomb extends TileEntity implements DynamicEntity {
    public static final int TIME_TO_EXPLODE = 120;
    public static final int ANIMATED_FRAME = 10;
    int range;
    int timeToExplode;
    Sprite[] bombs;
    private final Bomber bomber;

    /**
     * Constructor.
     *
     * @param xUnit         position x in map.
     * @param yUnit         position y in map.
     * @param mapManager    the MapManager to be initialized.
     * @param range         range of this bomb.
     */
    public Bomb(int xUnit, int yUnit, MapManager mapManager, int range, Bomber bomber) {
        super(xUnit, yUnit, mapManager);
        this.range = range;
        this.bomber = bomber;
        img = Sprite.bomb.getFxImage();
        bombs = new Sprite[6];
        bombs[0] = Sprite.bomb;
        bombs[1] = Sprite.bomb_1;
        bombs[2] = Sprite.bomb_2;
        bombs[3] = Sprite.bomb_3;
        bombs[4] = Sprite.bomb_4;
        bombs[5] = Sprite.bomb_5;
        timeToExplode = TIME_TO_EXPLODE;
    }

    @Override
    public void update() {
        if (timeToExplode > 0) {
            timeToExplode--;
            img = bombs[(timeToExplode / ANIMATED_FRAME) % bombs.length].getFxImage();
        } else if (timeToExplode == 0) {
            explode();
        }
    }

    public void explode() {
        new Explosion(getXUnit(), getYUnit(), mapManager,
                Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2);
        createExplosion();
        mapManager.getTilesAt(getXUnit(), getYUnit()).remove(this);
        bomber.getPlacedBombs().remove(this);
        int addedBombNums = mapManager.getBomberman().getBombNums() + 1;
        mapManager.getBomberman().setBombNums(addedBombNums);
        mapManager.getGamePlay().getContainedLevelScreen().setBomberStat(InformationPane.BOMB_NO, addedBombNums);
//        SoundEffect.playSE(SoundEffect.bombExplosion);
        SoundManager.soundEffect.play(SoundEffect.bombExplosion);
    }

    /**
     * Tạo ra các đối tượng Explosion khi hết thời gian charge của bomb.
     */
    private void createExplosion() {
        // nếu muốn cho bom phá xuyên tường thì chỉ cần thêm đk ở
//        if (getMapManager().getTopTileAt(getXUnit(), getYUnit() + i).blockFlame() && bomber.canPierce()) {
//            break;
//        }
        // DOWN
        for (int i = 1; i <= range; i++) {
            if (getMapManager().getTopTileAt(getXUnit(), getYUnit() + i).blockFlame()) {
                break;
            }
            if (i == range) {
                new Explosion(getXUnit(), getYUnit() + range, mapManager,
                        Sprite.explosion_vertical_down_last,
                        Sprite.explosion_vertical_down_last1,
                        Sprite.explosion_vertical_down_last2);
                break;
            }
            new Explosion(getXUnit(), getYUnit() + i, mapManager,
                    Sprite.explosion_vertical,
                    Sprite.explosion_vertical1,
                    Sprite.explosion_vertical2);
        }

        // UP
        for (int i = 1; i <= range; i++) {
            if (getMapManager().getTopTileAt(getXUnit(), getYUnit() - 1).blockFlame()) {
                break;
            }
            if (i == range) {
                new Explosion(getXUnit(), getYUnit() - range, mapManager,
                        Sprite.explosion_vertical_top_last,
                        Sprite.explosion_vertical_top_last1,
                        Sprite.explosion_vertical_top_last2);
                break;
            }
            new Explosion(getXUnit(), getYUnit() - i, mapManager,
                    Sprite.explosion_vertical,
                    Sprite.explosion_vertical1,
                    Sprite.explosion_vertical2);
        }

        // LEFT
        for (int i = 1; i <= range; i++) {
            if (getMapManager().getTopTileAt(getXUnit() - i, getYUnit()).blockFlame()) {
                break;
            }
            if (i == range) {
                new Explosion(getXUnit() - range, getYUnit(), mapManager,
                        Sprite.explosion_horizontal_left_last,
                        Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2);
                break;
            }
            new Explosion(getXUnit() - i, getYUnit(), mapManager,
                    Sprite.explosion_horizontal,
                    Sprite.explosion_horizontal1,
                    Sprite.explosion_horizontal2);
        }

        // RIGHT
        for (int i = 1; i <= range; i++) {
            if (getMapManager().getTopTileAt(getXUnit() + i, getYUnit()).blockFlame()) {
                break;
            }
            if (i == range) {
                new Explosion(getXUnit() + range, getYUnit(), mapManager,
                        Sprite.explosion_horizontal_right_last,
                        Sprite.explosion_horizontal_right_last1,
                        Sprite.explosion_horizontal_right_last2);
                break;
            }
            new Explosion(getXUnit() + i, getYUnit(), mapManager,
                    Sprite.explosion_horizontal,
                    Sprite.explosion_horizontal1,
                    Sprite.explosion_horizontal2);
        }
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        return false;
    }

    @Override
    public boolean allowWalkThrough(MovingEntity movingEntity) {
        return movingEntity.isCanWalkThroughBomb()
                || !(getX() + Sprite.SCALED_SIZE - 1 < movingEntity.getX()
                || getX() > movingEntity.getX() + Sprite.SCALED_SIZE - 1
                || getY() > movingEntity.getY() + Sprite.SCALED_SIZE - 1
                || getY() + Sprite.SCALED_SIZE - 1 < movingEntity.getY());
    }

    @Override
    public boolean blockFlame() {
        explode();
        return true;
    }
}