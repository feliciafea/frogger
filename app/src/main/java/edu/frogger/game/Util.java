package edu.frogger.game;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class Util {

    public static boolean checkCollision(int x1, int y1, int w1, int x2, int y2, int w2) {
        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + w1 && y1 + w1 > y2;
    }

    public static Bitmap flipBitmapX(Bitmap bInput) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bInput, 0, 0, bInput.getWidth(),
                bInput.getHeight(), matrix, false);
    }

}
