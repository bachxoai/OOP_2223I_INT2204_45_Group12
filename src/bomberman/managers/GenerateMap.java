package bomberman.managers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class GenerateMap {
    public static void generate(String mapName) {
        Random random = new Random();

        int col = 1 + 2 * random.nextInt(20) + 5;
        int row = 1 + 2 * random.nextInt(20) + 5;

        int emptySpace = row * col - row * 2 - col * 2 - (row / 2 - 1) * (col / 2 - 1) + 4;

        int itemTotal = emptySpace / 2 / 5;
        int monsterTotal = emptySpace / 2 / 10;
        int[] items = new int[itemTotal];
        int[] enemies = new int[monsterTotal];
        for (int i = 0; i < itemTotal; i++) {
            items[i] = random.nextInt((emptySpace / 2) / itemTotal) + 1;
        }
        for (int i = 0; i < monsterTotal; i++) {
            enemies[i] = random.nextInt((emptySpace / 2) / monsterTotal) + 1;
        }
        char arr[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0 || i == row - 1 || j == col - 1 || (i % 2 == 0 && j % 2 == 0)) {
                    arr[i][j] = '#';
                } else {
                    if (i == 1 && j == 1) {
                        arr[i][j] = 'p';
                    } else if ((i == 1 && j == 2) || (i == 2 && j == 1)) {
                        arr[i][j] = ' ';
                    }
                    else if ((i == 1 && j == 3) || (i == 3 && j == 1)) {
                        arr[i][j] = '*';
                    }
                    else if (random.nextInt(2) == 0) {
                        arr[i][j] = ' ';
                    } else {
                        arr[i][j] = '*';
                    }

                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == '*') {
                    for (int k = 0; k < items.length; k++) {
                        if (items[k] == 0) {
                            arr[i][j] = charOfItem(k);
                            items[k]--;
                            break;
                        }
                        if (items[k] > 0) {
                            items[k]--;
                            break;
                        }
                    }
                }
                if (arr[i][j] == ' ') {
                    for (int k = 0; k < enemies.length; k++) {
                        if ((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1)) {
                            break;
                        }
                        if (enemies[k] == 0) {
                            arr[i][j] = charOfEnemy(k);
                            enemies[k]--;
                            break;
                        }
                        if (enemies[k] > 0) {
                            enemies[k]--;
                            break;
                        }
                    }
                }
            }
        }

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("res\\levels\\" + mapName + ".txt"));
            bufferedWriter.write("1 " + row + " " + col + "\n");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    bufferedWriter.write(arr[i][j]);
                }
                bufferedWriter.write("\n");
            }
        } catch (Exception e) {
            System.err.println("Error from generate()/GenerateMap");
        } finally {
            try {
                bufferedWriter.close();
            } catch (Exception e) {
                System.err.println("Can not close bufferedWriter in loadMap/MapManager.");
            }
        }
    }

    private static char charOfItem(int k) {
        switch (k % 12) {
            case 0:
                return 'x';
            case 1:
                return 's';
            case 2:
            case 3:
            case 4:
                return 'b';
            case 6:
            case 5:
            case 7:
                return 'f';
            case 8:
                return 'a';
            case 9:
                return 'c';
            case 10:
                return 'd';
            case 11:
                return 'e';

        }
        return '1';
    }
    private static char charOfEnemy(int k) {
        switch (k % 8) {
            case 0:
            case 1:
            case 2:
                return '1';
            case 3:
            case 4:
                return '2';
            case 5:
                return '3';
            case 6:
            case 7:
                return '4';

        }
        return '1';
    }
}

