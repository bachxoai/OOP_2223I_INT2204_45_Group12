package bomberman.managers;

import bomberman.entities.DynamicEntity;
import bomberman.entities.Entity;
import bomberman.entities.movingEntities.Enemy.Balloom;
import bomberman.entities.movingEntities.Bomber;
import bomberman.entities.movingEntities.Enemy.Oneal;
import bomberman.entities.tileEntities.*;
import bomberman.entities.tileEntities.Item.BombsItem;
import bomberman.entities.tileEntities.Item.FlamesItem;
import bomberman.entities.tileEntities.Item.SpeedItem;
import bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;

//Đây là một Class để đọc Map, quản lý các đối tượng trong Map. Class này sẽ cần đổi tên lại sau.
public class MapManager {
    //Liên kết Class quản lý map với một map nào đó.
    private GamePlay gamePlay;

    //Level, số hàng, cột được đọc vào từ File Level.txt
    private int level, row, col;


    private ArrayList<DynamicEntity> dynamicEntities = new ArrayList<>();

    private ArrayList<Entity> tileEntities = new ArrayList<>();

    public void addDynamicEntity(DynamicEntity entity) {
        if (!(dynamicEntities.contains(entity))) {
            dynamicEntities.add(entity);
        }
    }

    public void addTileEntity(TileEntity entity) {
        if (!(tileEntities.contains(entity))) {
            tileEntities.add(entity);
            tileEntitiesMatrix[entity.getY() / Sprite.SCALED_SIZE][entity.getX() / Sprite.SCALED_SIZE] = entity;
        }
    }

    public void removeTileEntity(TileEntity entity) {
        if (tileEntities.contains(entity)) {
            tileEntities.remove(entity);
            tileEntitiesMatrix[entity.getY() / Sprite.SCALED_SIZE][entity.getX() / Sprite.SCALED_SIZE] = (TileEntity) getTileEntityAt(entity.getX() / Sprite.SCALED_SIZE, entity.getY() / Sprite.SCALED_SIZE);
        }
    }

    private TileEntity[][] tileEntitiesMatrix;

    //Phương thức lấy ra một StillObject nào đó
    public Entity getTileEntityAt(int xCol, int yRow) {
        for (int i = tileEntities.size() - 1; i >= 0; i--) {
            if (tileEntities.get(i).getX() == xCol * Sprite.SCALED_SIZE && tileEntities.get(i).getY() == yRow * Sprite.SCALED_SIZE) {
                return tileEntities.get(i);
            }
        }
        return null;
    }

    public int getDynamicEntitiesSize() {
        return dynamicEntities.size();
    }

    public int getTileEntitiesSize() {
        return tileEntities.size();
    }

    public DynamicEntity getDynamicEntityAtIndex(int i) {
        return dynamicEntities.get(i);
    }

    public Entity getTileEntityAtIndex(int i) {
        return tileEntities.get(i);
    }

    public MapManager(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

//    public TileEntity[][] getTileEntitiesMatrix() {
//        return tileEntitiesMatrix;
//    }

    public TileEntity getTileEntityMatrixAt(int row, int col) {
        return tileEntitiesMatrix[row][col];
    }

    //Hàm đọc map từ File, sẽ được gọi trong Constructor của Map1. Hàm này sẽ cần xử lý lại các Exception.
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

            tileEntitiesMatrix = new TileEntity[row][col];

            //Đọc file rồi tạo đối tượng trong Map, thêm các đối tượng vào các Array stillObject,...
            for (int i = 0; i < row; i++) {
                String rowText = bufferedReader.readLine();
                for (int j = 0; j < col; j++) {
                    char x = rowText.charAt(j);
                    new Grass(j, i, gamePlay);
                    if (x == '#') {
                        new Wall(j, i, gamePlay);
                    } else if (x == '*') {
                        new Brick(j, i, gamePlay);
                    } else if (x == 'x') {
                        new Portal(j, i, gamePlay);
                        new Brick(j, i, gamePlay);
                    } else if (x == 'p') {
                        gamePlay.setBomberman(new Bomber(j, i, gamePlay));
                    } else if (x == '1') {
                        new Balloom(j, i, gamePlay);
                    } else if (x == '2') {
                        new Oneal(j, i, gamePlay);
                    } else if (x == 'b') {
                        new BombsItem(j, i, gamePlay);
                        new Brick(j, i, gamePlay);
                    } else if (x == 'f') {
                        new FlamesItem(j, i, gamePlay);
                        new Brick(j, i, gamePlay);
                    } else if (x == 's') {
                        new SpeedItem(j, i, gamePlay);
                        new Brick(j, i, gamePlay);
                    }
                }
            }
            reader.close();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Cannot read map");
        }
    }
}
