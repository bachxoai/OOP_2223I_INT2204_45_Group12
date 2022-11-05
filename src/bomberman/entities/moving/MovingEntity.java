package bomberman.entities.moving;

import bomberman.entities.DynamicEntity;
import bomberman.entities.tile.TileEntity;
import bomberman.managers.GamePlay;
import bomberman.entities.Entity;
import bomberman.graphics.Sprite;
import bomberman.managers.MapManager;

/**
 * Class dành cho các đối tượng có thể di chuyển. Liên quan đến toạ độ pixel.
 */
public abstract class MovingEntity extends Entity implements DynamicEntity {
    //Các trạng thái của Moving Entity
    public static final int NORMAL_STATE = 0;
    public static final int DEAD_STATE = 1;
    public static final int UP_STATE = 2;
    public static final int DOWN_STATE = 3;
    public static final int LEFT_STATE = 4;
    public static final int RIGHT_STATE = 5;
    public static final int DEAD_TIME = 20;
    //Trạng thái của Moving Entity
    protected int state;

    //Các Sprite lưu animation của Entity.
    protected Sprite[] up;
    protected Sprite[] down;
    protected Sprite[] left;
    protected Sprite[] right;
    protected Sprite[] dead;

    //Va chạm của ô sắp gặp
    protected Entity futureCollision = null;

    //Va chạm của ô đang đứng
    protected Entity presentCollision = null;

    //Vận tốc
    protected double velocity;

    //Số khung hình trên giây
    public final int ANIMATED_FRAME = 6;

    //Thời gian hoạt ảnh animation luc chết
    protected int animationDeadTime = DEAD_TIME;

    //Biến thể hiện rằng Entity còn sống
    protected boolean isAlive = true;

    protected boolean canWalkThroughBomb;
    protected boolean canWalkThroughBrick;
    protected boolean canWalkThroughFlame;

    protected final TileEntity[] futureTilesCollision = new TileEntity[2];
    protected TileEntity presentTileCollision;


    public MovingEntity(int xUnit, int yUnit, MapManager mapManager) {
        super(xUnit, yUnit, mapManager);
        mapManager.addMovingEntities(this);
        state = NORMAL_STATE;
    }

    //Phương thức gán các hình ảnh để tạo animation cho MovingEntity
    public void setSprite(Sprite[] up, Sprite[] down, Sprite[] left, Sprite[] right, Sprite[] dead) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.dead = dead;
    }

    public int getState() {
        return state;
    }

    public void setFutureCollision(Entity collisionStatus) {
        this.futureCollision = collisionStatus;
    }

    public void setPresentCollision(Entity presentCollision) {
        this.presentCollision = presentCollision;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public abstract void update();

    /**
     * Hàm tạo hoạt ảnh cho nhân vật
     * @param state
     */
    protected void animation(int state) {
        int n = GamePlay.frameCount/(60/ANIMATED_FRAME);
        switch (state) {
            case UP_STATE:
                img = up[n % up.length].getFxImage();
                break;
            case DOWN_STATE:
                img = down[n % down.length].getFxImage();
                break;
            case LEFT_STATE:
                img = left[n % left.length].getFxImage();
                break;
            case RIGHT_STATE:
                img = right[n % right.length].getFxImage();
                break;
            case DEAD_STATE:
                img = dead[n % dead.length].getFxImage();
                break;
        }
    }

    /**
     * Hàm thay đổi vị trí cho nhân vật
     */
    protected void move() {
        switch (state) {
            case UP_STATE: {
                setY(getY() - velocity);
                break;
            }
            case DOWN_STATE: {
                setY(getY() + velocity);
                break;
            }
            case LEFT_STATE: {
                setX(getX() - velocity);
                break;
            }
            case RIGHT_STATE: {
                setX(getX() + velocity);
                break;
            }
        }
    }

    /**
     * Hàm thực hiện khi nhân vật chết
     */
    protected void handleDeadState() {
        animationDeadTime--;
        if (animationDeadTime == 0) {
            if (this instanceof Bomber && ((Bomber) this).lives != 0) {
                return;
            }
            mapManager.getEnemies().remove(this);
            mapManager.getMovingEntities().remove(this);
            return;
        }
        animation(DEAD_STATE);
    }

    public boolean handleOtherBomberCollision(Bomber bomber) {
        return true;
    }

    /**
     * Check two front tiles in the direction of this.
     *
     * @return sth.
     */
    public TileEntity[] updateFutureTilesCollision() {
//        futureTilesCollision[0] = null;

        int leftCol = (int) getX() / Sprite.SCALED_SIZE;
        int rightCol = (int) (getX() + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE;
        int topRow = (int) getY() / Sprite.SCALED_SIZE;
        int bottomRow = (int) (getY() + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE;

        switch (getState()) {
            case MovingEntity.UP_STATE: {
                futureTilesCollision[0] = getMapManager().getTopTileAt(leftCol, topRow);
                futureTilesCollision[1] = getMapManager().getTopTileAt(rightCol, topRow);
                break;
            }
            case MovingEntity.DOWN_STATE: {
                futureTilesCollision[0] = getMapManager().getTopTileAt(leftCol, bottomRow);
                futureTilesCollision[1] = getMapManager().getTopTileAt(rightCol, bottomRow);
                break;
            }
            case MovingEntity.RIGHT_STATE: {
                futureTilesCollision[0] = getMapManager().getTopTileAt(rightCol, topRow);
                futureTilesCollision[1] = getMapManager().getTopTileAt(rightCol, bottomRow);
                break;
            }
            case MovingEntity.LEFT_STATE: {
                futureTilesCollision[0] = getMapManager().getTopTileAt(leftCol, topRow);
                futureTilesCollision[1] = getMapManager().getTopTileAt(leftCol, bottomRow);
                break;
            }
            default: {
                futureTilesCollision[0] = mapManager.getTopTileAt(leftCol, topRow);
                futureTilesCollision[1] = mapManager.getTopTileAt(leftCol, topRow);
            }
        }



        return this.futureTilesCollision;
    }

    /**
     * check what is standing tile
     *
     * @return the
     */
    public TileEntity updatePresentTileCollision() {
        presentTileCollision = mapManager.getTopTileAt(getXUnit(), getYUnit());
        return this.presentTileCollision;
    }

    /**
     * Check collision with all MovingEntity in Map
     * until all the MovingEntity are checked
     * or the checkHandleOtherXCollision return false;
     */
    public void checkMovingCollisions() {

    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isCanWalkThroughBomb() {
        return canWalkThroughBomb;
    }

    public void setCanWalkThroughBomb(boolean canWalkThroughBomb) {
        this.canWalkThroughBomb = canWalkThroughBomb;
    }

    public boolean isCanWalkThroughBrick() {
        return canWalkThroughBrick;
    }

    public void setCanWalkThroughBrick(boolean canWalkThroughBrick) {
        this.canWalkThroughBrick = canWalkThroughBrick;
    }

    public boolean isCanWalkThroughFlame() {
        return canWalkThroughFlame;
    }

    public void setCanWalkThroughFlame(boolean canWalkThroughFlame) {
        this.canWalkThroughFlame = canWalkThroughFlame;
    }
}
