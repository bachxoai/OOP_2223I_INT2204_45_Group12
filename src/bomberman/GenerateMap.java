package bomberman;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class GenerateMap {
    public static void generate(String mapName, int row, int col) {
        Random random = new Random();
        int randomBlock = row * col - row * 2 - col * 2 - (row / 2 - 1) * (col / 2 - 1) + 4;
        int randomItems[] = new int[12];
        int randomEnemies[] = new int[8];
        for (int i = 0; i < 12; i++) {
            randomItems[i] = random.nextInt((randomBlock / 2) / 12) + 1;
        }
        for (int i = 0; i < 8; i++) {
            randomEnemies[i] = random.nextInt((randomBlock / 2) / 8) + 1;
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
                    for (int k = 0; k < randomItems.length; k++) {
                        if (randomItems[k] == 0) {
                            arr[i][j] = charOfItem(k);
                            randomItems[k]--;
                            break;
                        }
                        if (randomItems[k] > 0) {
                            randomItems[k]--;
                            break;
                        }
                    }
                }
                if (arr[i][j] == ' ') {
                    for (int k = 0; k < randomEnemies.length; k++) {
                        if ((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1)) {
                            break;
                        }
                        if (randomEnemies[k] == 0) {
                            arr[i][j] = charOfEnemy(k);
                            randomEnemies[k]--;
                            break;
                        }
                        if (randomEnemies[k] > 0) {
                            randomEnemies[k]--;
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

    private static void countdown(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    private static boolean done(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                return false;
            }
        }
        return true;
    }

    private static char charOfItem(int k) {
        switch (k) {
            case 0:
                return 'x';
            case 1:
                return 'a';
            case 2:
            case 3:
            case 4:
                return 'b';
            case 5:
                return 'c';
            case 6:
            case 7:
            case 8:
                return 'f';
            case 9:
            case 10:
                return 's';
            case 11:
                return 'e';

        }
        return '1';
    }
    private static char charOfEnemy(int k) {
        switch (k) {
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

