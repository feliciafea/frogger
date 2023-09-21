package edu.frogger.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;

public class TileMap {

    // 0 = safe
    // 1 = road
    // 2 = water
    // 3 = goal

    private static int[][] map = {{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                                  {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                  {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                  {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                  {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                  {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};


    private static final int WIDTH = 12;
    private static final int HEIGHT = 16;
    private static int tileWidth;
    private static int borderWidth;
    private static int borderHeight;
    private Game game;
    private Picture picture;
    private Bitmap safeTile;
    private Bitmap roadTile;
    private Bitmap waterTile;
    private Bitmap goalTile;

    public TileMap(int screenWidth, int screenHeight, Game game) {
        calcTileAndBorder(screenWidth, screenHeight);
        this.game = game;

        Resources res = this.game.getResources();
        this.safeTile = BitmapFactory.decodeResource(res, R.drawable.safe_tile);
        this.safeTile = Bitmap.createScaledBitmap(this.safeTile, tileWidth, tileWidth, false);
        this.roadTile = BitmapFactory.decodeResource(res, R.drawable.road_tile);
        this.roadTile = Bitmap.createScaledBitmap(this.roadTile, tileWidth, tileWidth, false);
        this.waterTile = BitmapFactory.decodeResource(res, R.drawable.water_tile);
        this.waterTile = Bitmap.createScaledBitmap(this.waterTile, tileWidth, tileWidth, false);
        this.goalTile = BitmapFactory.decodeResource(res, R.drawable.goal_tile);
        this.goalTile = Bitmap.createScaledBitmap(this.goalTile, tileWidth, tileWidth, false);

        this.picture = new Picture();
        Canvas canvas = picture.beginRecording(WIDTH * tileWidth, HEIGHT * tileWidth);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (map[i][j] == 0) {
                    canvas.drawBitmap(safeTile, j * tileWidth, i * tileWidth, null);
                }
                if (map[i][j] == 1) {
                    canvas.drawBitmap(roadTile, j * tileWidth, i * tileWidth, null);
                }
                if (map[i][j] == 2) {
                    canvas.drawBitmap(waterTile, j * tileWidth, i * tileWidth, null);
                }
                if (map[i][j] == 3) {
                    canvas.drawBitmap(goalTile, j * tileWidth, i * tileWidth, null);
                }
            }
        }
        picture.endRecording();
    }

    public void draw(Canvas canvas) {
        int bottom = borderHeight + (tileWidth * HEIGHT);
        int right = borderWidth + (tileWidth * WIDTH);
        canvas.drawPicture(picture, new Rect(borderWidth, borderHeight, right, bottom));
    }

    public static int getMapWidth() {
        return WIDTH;
    }

    public static int getMapHeight() {
        return HEIGHT;
    }

    public static int getTileWidth() {
        return tileWidth;
    }

    public static int getBorderWidth() {
        return borderWidth;
    }

    public static int getBorderHeight() {
        return borderHeight;
    }

    public static int getTile(int x, int y) {
        return map[y][x];
    }

    public static void calcTileAndBorder(int screenWidth, int screenHeight) {
        tileWidth = screenWidth / 12;
        borderWidth = (screenWidth - (tileWidth * WIDTH)) / 2;
        borderHeight = (screenHeight - (tileWidth * HEIGHT)) / 2;
    }
}
