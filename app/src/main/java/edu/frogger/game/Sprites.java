package edu.frogger.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Sprites {
    private static Bitmap playerSprite;
    private static Bitmap carSprite;
    private static Bitmap racecarSprite;
    private static Bitmap truckSprite;
    private static Bitmap log2Sprite;
    private static Bitmap log3Sprite;

    public static void initializeSprites(Resources res, int character) {
        if (character == 1) {
            playerSprite = BitmapFactory.decodeResource(res, R.drawable.character1);
            playerSprite = Bitmap.createScaledBitmap(playerSprite, TileMap.getTileWidth(), TileMap
                    .getTileWidth(), false);

        } else if (character == 2) {
            playerSprite = BitmapFactory.decodeResource(res, R.drawable.character2);
            playerSprite = Bitmap.createScaledBitmap(playerSprite, TileMap.getTileWidth(), TileMap
                    .getTileWidth(), false);
        } else {
            playerSprite = BitmapFactory.decodeResource(res, R.drawable.character3);
            playerSprite = Bitmap.createScaledBitmap(playerSprite, TileMap.getTileWidth(), TileMap
                    .getTileWidth(), false);
        }

        carSprite = BitmapFactory.decodeResource(res, R.drawable.car);
        carSprite = Bitmap.createScaledBitmap(carSprite, TileMap.getTileWidth(), TileMap
                .getTileWidth(), false);
        racecarSprite = BitmapFactory.decodeResource(res, R.drawable.racecar);
        racecarSprite = Bitmap.createScaledBitmap(racecarSprite, TileMap.getTileWidth(), TileMap
                .getTileWidth(), false);
        racecarSprite = Util.flipBitmapX(racecarSprite);
        truckSprite = BitmapFactory.decodeResource(res, R.drawable.truck);
        truckSprite = Bitmap.createScaledBitmap(truckSprite, TileMap.getTileWidth() * 2, TileMap
                .getTileWidth(), false);

        log2Sprite = BitmapFactory.decodeResource(res, R.drawable.log2);
        log2Sprite = Bitmap.createScaledBitmap(log2Sprite, TileMap.getTileWidth() * 2, TileMap
                .getTileWidth(), false);
        log3Sprite = BitmapFactory.decodeResource(res, R.drawable.log3);
        log3Sprite = Bitmap.createScaledBitmap(log3Sprite, TileMap.getTileWidth() * 3, TileMap
                .getTileWidth(), false);
    }

    public static Bitmap getPlayerSprite() {
        return playerSprite;
    }

    public static Bitmap getCarSprite() {
        return carSprite;
    }

    public static Bitmap getRacecarSprite() {
        return racecarSprite;
    }

    public static Bitmap getTruckSprite() {
        return truckSprite;
    }

    public static Bitmap getLog2Sprite() {
        return log2Sprite;
    }

    public static Bitmap getLog3Sprite() {
        return log3Sprite;
    }
}
