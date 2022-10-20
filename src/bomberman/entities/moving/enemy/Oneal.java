package bomberman.entities.moving.enemy;

import bomberman.entities.Entity;
import bomberman.entities.tile.Brick;
import bomberman.entities.tile.Grass;
import bomberman.entities.tile.Wall;
import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;
import com.sun.javafx.font.directwrite.DWFactory;

import java.awt.*;
import java.util.ArrayList;

/**
 * Oneal moves quickly and randomly.
 * It will move toward Bomberman when he is nearby.
 * It is not likely to get stuck in walls and can be incredibly troublesome.
 * It takes 1 hit to defeat and yields a score of 200 points
 */
public class Oneal extends Enemy {
    private static final int LENGTH_TO_CHASE_BOMBER = 4;
    private static final int CAN_WALK = 0;
    private static final int BLOCKED = 1;
    private static final int BOMBER = 2;
    private static final int PARENT_ON_LEFT = 3;
    private static final int PARENT_ON_TOP = 4;
    private static final int PARENT_ON_RIGHT = 5;
    private static final int PARENT_ON_BOTTOM = 6;
    private static final int ROOT = 9;
    private ArrayList<ArrayList<Integer>> onealMap;
    public Oneal(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.oneal_left1.getFxImage();
        super.gamePlay = gamePlay;
        velocity = 1;

        // Create sprite.
        Sprite[] right = new Sprite[3];
        right[0] = Sprite.oneal_right1;
        right[1] = Sprite.oneal_right2;
        right[2] = Sprite.oneal_right3;
        Sprite[] left = new Sprite[3];
        left[0] = Sprite.oneal_left1;
        left[1] = Sprite.oneal_left2;
        left[2] = Sprite.oneal_left3;
        Sprite[] dead = new Sprite[1];
        dead[0] = Sprite.oneal_dead;
        setSprite(left, right, left, right, dead);

        // init oneal map
        onealMap = new ArrayList<ArrayList<Integer>>();
        for (int j = 0; j < gamePlay.getMapManager().getRow(); j++) {
            onealMap.add(new ArrayList<Integer>());
            for (int i = 0; i < gamePlay.getMapManager().getCol(); i++) {
                onealMap.get(j).add(0);
            }
        }

        state = LEFT_STATE;
    }

    @Override
    protected void setDirection() {
        updateOnealMap();
        bfs();
        moveRandomly();
    }

    private void bfs() {
        int length = LENGTH_TO_CHASE_BOMBER;
        ArrayList<ArrayList<Integer>> myQueue = new ArrayList<>();
        int left = 0;
        int right = 1;
        myQueue.add(new ArrayList<>());
        myQueue.get(0).add(getXUnit());
        myQueue.get(0).add(getYUnit());
        while (left != right && length > 0) {
            int oldRight = right;
            for (int i = left; i< oldRight; i++) {
                int curX = myQueue.get(i).get(0);
                int curY = myQueue.get(i).get(1);
                // có thể phải check trường hợp ngoại lệ ở đây
                // nma quy ước map được bound r nên không sao :)))
                if (onealMap.get(curY).get(curX + 1) == CAN_WALK) {
                    addToMyQueue(curX + 1, curY, myQueue, PARENT_ON_LEFT);
                    right++;
                }
                if (onealMap.get(curY).get(curX - 1) == CAN_WALK) {
                    addToMyQueue(curX - 1, curY, myQueue, PARENT_ON_RIGHT);
                    right++;
                }
                if (onealMap.get(curY + 1).get(curX) == CAN_WALK) {
                    addToMyQueue(curX, curY + 1, myQueue, PARENT_ON_TOP);
                    right++;
                }
                if (onealMap.get(curY - 1).get(curX) == CAN_WALK) {
                    addToMyQueue(curX, curY - 1, myQueue, PARENT_ON_BOTTOM);
                    right++;
                }

                if (onealMap.get(curY).get(curX + 1) == BOMBER) {
                    onealMap.get(curY).set(curX + 1, PARENT_ON_LEFT);
                    traceBack(curX + 1, curY);
                    return;
                }
                if (onealMap.get(curY).get(curX - 1) == BOMBER) {
                    onealMap.get(curY).set(curX - 1, PARENT_ON_RIGHT);
                    traceBack(curX - 1, curY);
                    return;
                }
                if (onealMap.get(curY - 1).get(curX) == BOMBER) {
                    onealMap.get(curY - 1).set(curX, PARENT_ON_BOTTOM);
                    traceBack(curX, curY - 1);
                    return;
                }
                if (onealMap.get(curY + 1).get(curX) == BOMBER) {
                    onealMap.get(curY + 1).set(curX, PARENT_ON_TOP);
                    traceBack(curX, curY + 1);
                    return;
                }

            }
            left = oldRight;
            length--;
        }
    }

    private void traceBack(int x, int y) {
        while (parent(x, y) != ROOT) {
            switch (onealMap.get(y).get(x)) {
                case PARENT_ON_LEFT: {
                    x = x - 1;
                    break;
                }
                case PARENT_ON_RIGHT: {
                    x = x + 1;
                    break;
                }
                case PARENT_ON_TOP: {
                    y = y - 1;
                    break;
                }
                case PARENT_ON_BOTTOM: {
                    y = y + 1;
                    break;
                }
                default:
                    break;
            }
        }
        state = convertToState(onealMap.get(y).get(x));
    }

    private int convertToState(int a) {
        switch (a) {
            case PARENT_ON_LEFT:
                return RIGHT_STATE;
            case PARENT_ON_RIGHT:
                return LEFT_STATE;
            case PARENT_ON_TOP:
                return DOWN_STATE;
            case PARENT_ON_BOTTOM:
                return UP_STATE;
            default:
                return -1;
        }
    }

    private Integer parent(int x, int y) {
        switch (onealMap.get(y).get(x)) {
            case PARENT_ON_LEFT: {
                return onealMap.get(y).get(x - 1);
            }
            case PARENT_ON_RIGHT: {
                return onealMap.get(y).get(x + 1);
            }
            case PARENT_ON_TOP: {
                return onealMap.get(y - 1).get(x);
            }
            case PARENT_ON_BOTTOM:
                return onealMap.get(y + 1).get(x);
            default:
                return -1;
        }
    }

    private void addToMyQueue(int x, int y, ArrayList<ArrayList<Integer>> myQueue, int parent) {
        onealMap.get(y).set(x, parent);
        ArrayList<Integer> a = new ArrayList<>();
        a.add(x);
        a.add(y);
        myQueue.add(a);
    }

    private ArrayList<ArrayList<Integer>> updateOnealMap() {
        // find from map
        for (int j = 0; j < gamePlay.getMapManager().getRow(); j++) {
            for (int i = 0; i < gamePlay.getMapManager().getCol(); i++) {
                if (!canMove(i, j)) {
                    onealMap.get(j).set(i, BLOCKED);
                } else {
                    onealMap.get(j).set(i, CAN_WALK);
                }
            }
        }
        // put bomber and this oneal
        onealMap.get(gamePlay.getBomberman().getYUnit()).set(gamePlay.getBomberman().getXUnit(), BOMBER);
        onealMap.get(getYUnit()).set(getXUnit(), ROOT);
        return onealMap;
    }

    protected boolean canMove(int x, int y) {
        Entity e = gamePlay.getMapManager().getTopTileAt(x, y);
        return e instanceof Grass;
    }

    private void printMap() {
        for (int j = 0; j < gamePlay.getMapManager().getRow(); j++) {
            for (int i = 0; i < gamePlay.getMapManager().getCol(); i++) {
                System.out.print(onealMap.get(j).get(i) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
