package edu.frogger.game;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SwipeListener implements View.OnTouchListener {
    //create gesture detector variable
    private GestureDetector gestureDetector;

    //create constructor
    SwipeListener(View view, Game game) {
        int threshold = 100;
        int velocityThreshold = 100;
        GestureDetector.SimpleOnGestureListener listener =
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent e) {
                        return super.onDown(e);
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2,
                                           float velocityX, float velocityY) {
                        //get x and y difference
                        float xDiff = e2.getX() - e1.getX();
                        float yDiff = e2.getY() - e1.getY();

                        try {
                            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                                if (Math.abs(xDiff) > threshold
                                        && Math.abs(velocityX) > velocityThreshold) {
                                    if (xDiff > 0) { //right
                                        game.getPlayer().moveRight();

                                    } else if (xDiff < 0) { //left
                                        game.getPlayer().moveLeft();
                                    }
                                    return true;
                                }
                            } else {
                                if (Math.abs(yDiff) > threshold
                                        && Math.abs(velocityY) > velocityThreshold) {
                                    if (yDiff > 0) { //down
                                        game.getPlayer().moveDown();
                                    } else if (yDiff < 0) { //up
                                        game.getPlayer().moveUp();
                                    }
                                    return true;
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                };
        //init gesture

        gestureDetector = new GestureDetector(game.getContext(), listener);

        view.setOnTouchListener(this);



    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }
}