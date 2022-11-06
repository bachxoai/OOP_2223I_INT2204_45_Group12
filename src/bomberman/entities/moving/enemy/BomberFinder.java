package bomberman.entities.moving.enemy;

import bomberman.entities.moving.MovingEntity;
import bomberman.managers.GamePlay;
import bomberman.managers.MapManager;
import java.util.ArrayList;

/**
 * class contains algorithms to find bomber.
 */
public class BomberFinder {
    public static final int CAN_WALK = 0;
    public static final int BLOCKED = 1;
    public static final int BOMBER = 9;
    public static final int ROOT = 8;
    int lengthToFindBomber;
    int finalDirection;
    Enemy owner;
    ArrayList<ArrayList<Integer>> vision;
    GamePlay gamePlay;
    MapManager mapManager;
    ArrayList<ArrayList<Integer>> myQueue;
    int left;
    int right;

    /**
     * Constructor.
     *
     * @param owner                 Enemy that owns this bomberFinder.
     * @param lengthToFindBomber    length to find bomber.
     * @param mapManager            mapManager .
     */
    public BomberFinder(Enemy owner, int lengthToFindBomber, MapManager mapManager) {
        this.owner = owner;
        this.lengthToFindBomber = lengthToFindBomber;
        this.mapManager = mapManager;
        vision = new ArrayList<>();
        for (int j = 0; j < mapManager.getRow(); j++) {
            vision.add(new ArrayList<>());
            for (int i = 0; i < mapManager.getCol(); i++) {
                vision.get(j).add(0);
            }
        }
    }

    /**
     * update the walkable map of the owner.
     *
     * @return the desire map.
     */
    public ArrayList<ArrayList<Integer>> updatedVision() {
        for (int j = 0; j < mapManager.getRow(); j++) {
            for (int i = 0; i < mapManager.getCol(); i++) {
                if (owner.canMove(i, j)) {
                    vision.get(j).set(i, CAN_WALK);
                } else {
                    vision.get(j).set(i, BLOCKED);
                }
            }
        }
        vision.get(mapManager.getBomberman().getYUnit()).set(mapManager.getBomberman().getXUnit(), BOMBER);
        vision.get(owner.getYUnit()).set(owner.getXUnit(), ROOT);
        return vision;
    }

    /**
     * bfs algorithm.
     */
    public void bfs() {
        myQueue = new ArrayList<>();
        myQueue.add(new ArrayList<>());
        myQueue.get(0).add(owner.getXUnit());
        myQueue.get(0).add(owner.getYUnit());
        left = 0;
        right = 1;
        int curX = owner.getXUnit();
        int curY = owner.getYUnit();
        int length = lengthToFindBomber;
        int oldRight = right;

        if (checkDirectionsForBfs(curX, curY, 0, true)) {
            return;
        }

        left = oldRight;
        while (left != right && length > 0) {
            oldRight = right;
            for (int i = left; i < oldRight; i++) {
                curX = myQueue.get(i).get(0);
                curY = myQueue.get(i).get(1);
                if (checkDirectionsForBfs(curX, curY, vision.get(curY).get(curX), false)) {
                    return;
                }
            }
            left = oldRight;
            length--;
        }
    }

    private boolean checkDirectionsForBfs(int curX, int curY,
                                       int dir,
                                       boolean depthOne) {
        int u = dir;
        int d = dir;
        int l = dir;
        int r = dir;
        if (depthOne) {
            u = MovingEntity.UP_STATE;
            d = MovingEntity.DOWN_STATE;
            l = MovingEntity.LEFT_STATE;
            r = MovingEntity.RIGHT_STATE;
        }
        if (vision.get(curY).get(curX + 1) == CAN_WALK) {
            addToMyQueue(curX + 1, curY, myQueue, r);
            right++;
        }
        if (vision.get(curY).get(curX - 1) == CAN_WALK) {
            addToMyQueue(curX - 1, curY, myQueue, l);
            right++;
        }
        if (vision.get(curY + 1).get(curX) == CAN_WALK) {
            addToMyQueue(curX, curY + 1, myQueue, d);
            right++;
        }
        if (vision.get(curY - 1).get(curX) == CAN_WALK) {
            addToMyQueue(curX, curY - 1, myQueue, u);
            right++;
        }
        if (depthOne) {
            if (vision.get(curY).get(curX + 1) == BOMBER) {
                finalDirection = MovingEntity.RIGHT_STATE;
                return true;
            }
            if (vision.get(curY).get(curX - 1) == BOMBER) {
                finalDirection = MovingEntity.LEFT_STATE;
                return true;
            }
            if (vision.get(curY + 1).get(curX) == BOMBER) {
                finalDirection = MovingEntity.DOWN_STATE;
                return true;
            }
            if (vision.get(curY - 1).get(curX) == BOMBER) {
                finalDirection = MovingEntity.UP_STATE;
                return true;
            }
        } else {
            if (vision.get(curY).get(curX + 1) == BOMBER
                    || vision.get(curY).get(curX - 1) == BOMBER
                    || vision.get(curY - 1).get(curX) == BOMBER
                    || vision.get(curY + 1).get(curX) == BOMBER) {
                    finalDirection = vision.get(curY).get(curX);
                return true;
            }
        }
        return false;
    }

    private void addToMyQueue(int x, int y, ArrayList<ArrayList<Integer>> myQueue, int val) {
        vision.get(y).set(x, val);
        ArrayList<Integer> a = new ArrayList<>();
        a.add(x);
        a.add(y);
        myQueue.add(a);
    }

    public int getFinalDirection() {
        return finalDirection;
    }

    public void setFinalDirection(int finalDirection) {
        this.finalDirection = finalDirection;
    }
}
