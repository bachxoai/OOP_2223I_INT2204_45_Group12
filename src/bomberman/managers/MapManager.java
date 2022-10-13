package bomberman.managers;

import bomberman.entities.movingEntities.Bomber;
import bomberman.entities.movingEntities.Enemy.Balloom;
import bomberman.entities.movingEntities.Enemy.Oneal;
import bomberman.entities.movingEntities.MovingEntity;
import bomberman.entities.tileentities.*;
import bomberman.entities.tileentities.Item.BombsItem;
import bomberman.entities.tileentities.Item.FlamesItem;
import bomberman.entities.tileentities.Item.SpeedItem;
import java.io.*;
import java.util.ArrayList;

/**
 * Class quản lý các đối tượng có trong Map.
 */
public class MapManager {
    //Liên kết Class quản lý map với một map nào đó.
    private GamePlay gamePlay;

    //Level, số hàng, cột được đọc vào từ File Level.txt
    private int level, row, col;

    // Các đối tượng có thể đè lên nhau
    // Mảng 3 chiều của các Tile entities
    // Trong đó 2 chiều đầu là vị trí của entity trong map
    // Chiều thứ 3 là thứ tự của một entity tại toạ độ trong map
    private ArrayList<ArrayList<ArrayList<TileEntity>>> tileEntitiesMatrix;

    // Mảng gồm các moving entities.
    private ArrayList<MovingEntity> movingEntities = new ArrayList<>();

    public MapManager(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    /**
     * Hàm đọc map từ File, sẽ được gọi trong Constructor của Map1.
     * Hàm này sẽ cần sử lý lại các Exception.
     *
     * @param path đường dẫn đến file map.
     */
    public void loadMap(String path) {
        try {
            Reader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String firstLine = bufferedReader.readLine();
            String[] tokens = firstLine.split("\\s");
            level = Integer.parseInt(tokens[0]);
            row = Integer.parseInt(tokens[1]);
            col = Integer.parseInt(tokens[2]);

            //Thay đổi kích thước của map dựa theo dữ liệu trong file.
            gamePlay.setHeight(row);
            gamePlay.setWidth(col);
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
                    new Grass(i, j, gamePlay);
                    if (x == '#') {
                        new Wall(i, j, gamePlay);
                    } else if (x == '*') {
                        new Brick(i, j, gamePlay);
                    } else if (x == 'x') {
                        new Portal(i, j, gamePlay);
                        new Brick(i, j, gamePlay);
                    } else if (x == 'p') {
                        gamePlay.setBomberman(new Bomber(i, j, gamePlay));
                    } else if (x == '1') {
                        new Balloom(i, j, gamePlay);
                    } else if (x == '2') {
                        new Oneal(i, j, gamePlay);
                    } else if (x == 'b') {
                        new BombsItem(i, j, gamePlay);
                        new Brick(i, j, gamePlay);
                    } else if (x == 'f') {
                        new FlamesItem(i, j, gamePlay);
                        new Brick(i, j, gamePlay);
                    } else if (x == 's') {
                        new SpeedItem(i, j, gamePlay);
                        new Brick(i, j, gamePlay);
                    }
                }
            }
            reader.close();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Cannot read map");
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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
