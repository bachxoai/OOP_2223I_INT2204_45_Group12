package bomberman.entities.tile.bomb;

import bomberman.entities.DynamicEntity;
import bomberman.entities.moving.Bomber;
import bomberman.entities.moving.enemy.Enemy;
import bomberman.entities.tile.TileEntity;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;
import bomberman.managers.SoundEffect;

/**
 * Đối tượng được sinh ra sau khi bom nổ.
 */
public class Explosion extends TileEntity implements DynamicEntity {
    public static final int ANIMATED_FRAME = 30;
    int timeLeft;
    Sprite[] sprites;

    /**
     * Constructor 1.
     *
     * @param xUnit         position x in map.
     * @param yUnit         position y in map.
     * @param mapManager    the MapManager to be initialized.
     * @param sprite        sprite.
     * @param sprite1       sprite1.
     * @param sprite2       sprite2.
     */
    public Explosion(int xUnit, int yUnit, MapManager mapManager,
                     Sprite sprite,
                     Sprite sprite1,
                     Sprite sprite2) {
        super(xUnit, yUnit, mapManager);
        timeLeft = ANIMATED_FRAME;
        sprites = new Sprite[5];
        sprites[0] = sprite;
        sprites[1] = sprite1;
        sprites[2] = sprite2;
        sprites[3] = sprite1;
        sprites[4] = sprite;
    }

    @Override
    public void update() {
        if (timeLeft > 0) {
            timeLeft--;
            img = sprites[timeLeft / (ANIMATED_FRAME / 5)].getFxImage();

        } else if (timeLeft == 0) {
            mapManager.getTilesAt(getXUnit(), getYUnit()).remove(this);
        }
    }

    @Override
    public boolean handleOtherBomberCollision(Bomber bomber) {
        if (!bomber.isCanWalkThroughFlame() && !bomber.isImmortal()) {
            bomber.handleDeath();
        }
        return false;
    }

    @Override
    public boolean handleOtherEnemyCollision(Enemy enemy) {
        enemy.setAlive(false);
        SoundEffect.playSE(SoundEffect.enemyDeath);
        return false;
    }
}
