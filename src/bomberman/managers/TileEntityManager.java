package bomberman.managers;

import bomberman.entities.Entity;
import bomberman.entities.movingEntities.Balloom;
import bomberman.entities.movingEntities.Bomber;
import bomberman.entities.movingEntities.Oneal;
import bomberman.entities.tileEntities.Brick;
import bomberman.entities.tileEntities.Grass;
import bomberman.entities.tileEntities.Item.BombsItem;
import bomberman.entities.tileEntities.Item.FlamesItem;
import bomberman.entities.tileEntities.Item.SpeedItem;
import bomberman.entities.tileEntities.Portal;
import bomberman.entities.tileEntities.Wall;

import java.io.*;

//Đây là một Class để đọc Map, quản lý các đối tượng trong Map. Class này sẽ cần đổi tên lại sau.
public class TileEntityManager {
    //Liên kết Class quản lý map với một map nào đó.
    private GamePlay gamePlay;

    //Level, số hàng, cột được đọc vào từ File Level.txt
    private int level, row, col;

    //Các Entity được load vào map, các Entity này sẽ được khởi tạo trong phuong thức loadMap()
    private Entity entityMatrix[][];

    public TileEntityManager(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public Entity[][] getEntityMatrix() {
        return entityMatrix;
    }

    //Hàm đọc map từ File, sẽ được gọi trong Constructor của Map1. Hàm này sẽ cần xử lý lại các Exception.
    public void loadMap(String path) {//throws IOException {
        try {
            Reader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String firstLine = bufferedReader.readLine();
            String[] tokens = firstLine.split("\\s");
            level = Integer.parseInt(tokens[0]);
            row = Integer.parseInt(tokens[1]);
            col = Integer.parseInt(tokens[2]);

            //Thay đổi kích thước của map dựa theo dữ liệu trong file.
            gamePlay.setWidth(col);
            gamePlay.setHeight(row);

            //Khởi tạo mảng các đối tượng trong Map
            entityMatrix = new Entity[row][col];

            //Đọc file rồi tạo đối tượng trong Map, thêm các đối tượng vào các Array stillObject,...
            for (int i = 0; i < row; i++) {
                String rowText = bufferedReader.readLine();
                for (int j = 0; j < col; j++) {
                    char x = rowText.charAt(j);
                    Entity temp = new Grass(j, i, gamePlay);
                    if (x == '#') {
                        entityMatrix[i][j] = new Wall(j, i, gamePlay);
                    } else if (x == '*') {
                        entityMatrix[i][j] = new Brick(j, i, gamePlay);
                    } else if (x == 'x') {
                        entityMatrix[i][j] = new Portal(j, i, gamePlay);
////                    entityMatrix[i][j] = new Brick(j, i, map);
                    } else if (x == 'p') {
                        entityMatrix[i][j] = new Grass(j, i, gamePlay);
                        gamePlay.setBomberman(new Bomber(j, i, gamePlay));
                        //map.getBomberman() = new Bomber(j, i, map);
                    } else if (x == '1') {
                        entityMatrix[i][j] = new Balloom(j, i, gamePlay);
                    } else if (x == '2') {
                        entityMatrix[i][j] = new Oneal(j, i, gamePlay);
                    } else if (x == 'b') {
                        entityMatrix[i][j] = new BombsItem(j, i, gamePlay);
                    } else if (x == 'f') {
                        entityMatrix[i][j] = new FlamesItem(j, i, gamePlay);
                    } else if (x == 's') {
                        entityMatrix[i][j] = new SpeedItem(j, i, gamePlay);
                    } else {
                        entityMatrix[i][j] = new Grass(j, i, gamePlay);
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
