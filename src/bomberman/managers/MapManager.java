package bomberman.managers;

import bomberman.entities.Entity;
import bomberman.entities.moving.Bomber;
import bomberman.entities.moving.enemy.*;
import bomberman.entities.moving.MovingEntity;
import bomberman.entities.tile.*;
import bomberman.entities.tile.item.*;
import bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;

/**
 * Class quản lý các đối tượng có trong Map.
 */
public class MapManager {
    //Liên kết Class quản lý map với một map nào đó.
    final char BombPassItem = 'a';
    final char BombsItem = 'b';
    final char DetonatorItem = 'c';
    final char FlamePassItem = 'd';
    final char FlamesItem = 'f';
    final char SpeedItem = 's';
    final char WallPassItem = 'e';

    private GamePlay gamePlay;
    private Bomber bomberman;

    //Level, số hàng, cột được đọc vào từ File Level.txt
    private int level, row, col;

    public Grass tempGrass;

    // Các đối tượng có thể đè lên nhau
    // Mảng 3 chiều của các Tile entities
    // Trong đó 2 chiều đầu là vị trí của entity trong map
    // Chiều thứ 3 là thứ tự của một entity tại toạ độ trong map
    private ArrayList<ArrayList<ArrayList<TileEntity>>> tileEntitiesMatrix;

    // Mảng gồm các moving entities.
    private ArrayList<MovingEntity> movingEntities = new ArrayList<>();

    //Mảng Enemy chứa các đối tượng quái vật, sử dụng để CheckCollision Bomber và Quái trong CollisionChecker
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public MapManager(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    /**
     * Hàm đọc map từ File, sẽ được gọi trong Constructor của Map1.
     *
     * @param path đường dẫn đến file map.
     */
    public void loadMap(String path) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));

            String firstLine = bufferedReader.readLine();
            String[] tokens = firstLine.split("\\s");
            level = Integer.parseInt(tokens[0]);
            row = Integer.parseInt(tokens[1]);
            col = Integer.parseInt(tokens[2]);

            //Thay đổi kích thước của map dựa theo dữ liệu trong file.
            gamePlay.setMaxRow(row);
            gamePlay.setMaxCol(col);

            gamePlay.setMapWidth(col * Sprite.SCALED_SIZE);
            gamePlay.setMapHeight(row * Sprite.SCALED_SIZE);

            tileEntitiesMatrix = new ArrayList<>();
            for (int j = 0; j < row; j++) {
                tileEntitiesMatrix.add(new ArrayList<>());
                for (int i = 0; i < col; i++) {
                    tileEntitiesMatrix.get(j).add(new ArrayList<>());
                }
            }

            //Đọc file rồi tạo đối tượng trong Map, thêm các đối tượng vào các Array stillObject,...
            for (int j = 0; j < row; j++) {
                String rowText = bufferedReader.readLine();
                for (int i = 0; i < col; i++) {
                    char x = rowText.charAt(i);
                    new Grass(i, j, this);
                    if (x == '#') {
                        new Wall(i, j, this);
                    } else if (x == '*') {
                        new Brick(i, j, this);
                    } else if (x == 'x') {
                        new Portal(i, j, this);
                        new Brick(i, j, this);
                    } else if (x == 'p') {
                        bomberman = new Bomber(i, j, this);
                    } else if (x == '1') {
                        new Balloom(i, j, this);
                    } else if (x == '2') {
                        new Oneal(i, j, this);
                    } else if (x == '3') {
                        new Kondoria(i, j, this);
                    } else if (x == '4') {
                        new Doll(i, j, this);
                    } else if (x == BombPassItem) {
                        new BombPassItem(i, j, this);
                        new Brick(i, j, this);
                    } else if (x == BombsItem) {
                        new BombsItem(i, j, this);
                        new Brick(i, j, this);
                    } else if (x == DetonatorItem) {
                        new DetonatorItem(i, j, this);
                        new Brick(i, j, this);
                    } else if (x == FlamePassItem) {
                        new FlamePassItem(i, j, this);
                        new Brick(i, j, this);
                    } else if (x == FlamesItem) {
                        new FlamesItem(i, j, this);
                        new Brick(i, j, this);
                    } else if (x == SpeedItem) {
                        new SpeedItem(i, j, this);
                        new Brick(i, j, this);
                    } else if (x == WallPassItem) {
                        new WallPassItem(i, j, this);
                        new Brick(i, j, this);
                    }
                }
            }
            tempGrass = new Grass(1, 1, this);
        } catch (IOException e) {
            System.err.println("Cannot read file path in loadMap/MapManager.");
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                System.err.println("Can not close bufferedReader in loadMap/MapManager.");
            }
        }
    }

    /**
     * Thêm các đối tượng TileEntity.
     * Được gọi ngay trong hàm khởi tạo.
     *
     * @param entity Đối tượng được thêm vào.
     */
    public void addTileEntity(TileEntity entity) {
        tileEntitiesMatrix.get(entity.getYUnit()).get(entity.getXUnit()).add(entity);
    }

    /**
     * tileEntitiesMatrix getter.
     *
     * @return tileEntitiesMatrix.
     */
    public ArrayList<ArrayList<ArrayList<TileEntity>>> getTileEntitiesMatrix() {
        return tileEntitiesMatrix;
    }

    /**
     * Lấy TileEntity cao nhất tại một vị trí trong map.
     *
     * @param x toạ độ x của vị trí cần lấy.
     * @param y toạ độ y của vị trí cần lấy.
     * @return TileEntity ở trên cùng tại vị trí có toạ độ (x, y).
     */
    public TileEntity getTopTileAt(int x, int y) {
        return tileEntitiesMatrix.get(y).get(x).get(tileEntitiesMatrix.get(y).get(x).size() - 1);
    }

    /**
     * Lấy các TileEntity tại một vị trí trong map.
     *
     * @param x toạ độ x của vị trí cần lấy.
     * @param y toạ độ y của vị trí cần lấy.
     * @return ArrayList tileEntity tại vị trí có toạ độ (x, y).
     */
    public ArrayList<TileEntity> getTilesAt(int x, int y) {
        return tileEntitiesMatrix.get(y).get(x);
    }

    public void removeTile(TileEntity tileEntity) {
        getTilesAt(tileEntity.getXUnit(), tileEntity.getYUnit()).remove(tileEntity);
    }

    /**
     * movingEntities getter.
     *
     * @return movingEntites.
     */
    public ArrayList<MovingEntity> getMovingEntities() {
        return movingEntities;
    }

    /**
     * Thêm các đối tượng movingEntity vào danh sách để quản lý.
     *
     * @param me đối tượng movingEntity cần được thêm.
     */
    public void addMovingEntities(MovingEntity me) {
        movingEntities.add(me);
    }

    /**
     * Thêm các Enemy vào danh sách enemies để check collision trong Collision Checker
     *
     * @param enemy
     */
    public void addEnemies(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Trả về mảng enemies
     *
     * @return mảng enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public GamePlay getGamePlay() {
        return gamePlay;
    }

    public Bomber getBomberman() {
        return bomberman;
    }

    public void setBomberman(Bomber bomberman) {
        this.bomberman = bomberman;
    }

    public Grass getTempGrass() {
        return tempGrass;
    }
}
