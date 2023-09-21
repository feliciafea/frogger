package edu.frogger.game;

public class PointSystem {

    private static int points = 0;
    private static int minYIdx = 15;
    private static final int[] POINT_AMOUNTS = {10, 20, 30, 40};
    private static int maxPoints = 0;


    public static void addPoints(int xIdx, int yIdx) {
        if (yIdx < minYIdx) {
            int tileID = TileMap.getTile(xIdx, yIdx + 1);
            if (yIdx == 0) {
                points += POINT_AMOUNTS[3];
            }
            points += POINT_AMOUNTS[tileID];
            if (points > maxPoints) {
                maxPoints = points;
            }
            minYIdx = yIdx;
        }
    }

    public static void setPoints(int points) {
        PointSystem.points = points;
    }

    public static void setMaxPoints(int maxPoints) {
        PointSystem.maxPoints = maxPoints;
    }

    public static void resetPoints() {
        points = 0;
        minYIdx = 15;
    }

    public static int getPoints() {
        return points;
    }

    public static int getMaxPoints() {
        return maxPoints;
    }

    public static void resetMaxPoints() {
        maxPoints = 0;
    }

}
