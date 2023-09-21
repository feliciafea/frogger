package edu.frogger.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private final int fps = 60;
    private double avgFPS;
    private final SurfaceHolder surfaceHolder;
    private Game game;
    private boolean running;
    private static Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, Game game) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.game = game;
    }

    public double getAvgFPS() {
        return avgFPS;
    }

    public void startLoop() {
        running = true;
        start();
    }

    public void stopLoop() {
        boolean retry = true;
        while (retry) {
            try {
                this.running = false;
                this.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void run() {
        super.run();

        // Initialize time and cycle count variables
        long startTime = 0;
        long deltaTime;
        long waitTime;
        int frameCount = 0;
        long targetTime = 1000 / fps;

        // Game Loop
        while (running) {
            // Try to update and draw game
            try {
                canvas = this.surfaceHolder.lockHardwareCanvas();
                synchronized (surfaceHolder) {
                    this.game.update();
                    this.game.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // Pause game loop to not exceed FPS
            deltaTime = System.currentTimeMillis() - startTime;
            waitTime = (frameCount * targetTime - deltaTime);
            if (waitTime > 0) {
                try {
                    sleep(waitTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Calculate avgFPS
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime >= 1000) {
                avgFPS = frameCount / (1E-3 * deltaTime);
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }
}
