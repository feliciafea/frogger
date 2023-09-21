package edu.frogger.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private final Paint spritePaint = new Paint();
    private final Paint textPaint = new Paint();
    private TileMap tileMap;
    private final String name;
    private final String difficulty;
    private final int character;
    private int framesPassed = 0;
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final ArrayList<Log> logs = new ArrayList<>();
    private int screenWidth;
    private int screenHeight;
    private Player player;

    public Game(Context context, String name, String difficulty, int character) {
        super(context);
        this.name = name;
        this.difficulty = difficulty;
        this.character = character;
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
        init();
    }

    private void init() {
        PointSystem.resetPoints();
        PointSystem.resetMaxPoints();

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);

        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        tileMap = new TileMap(screenWidth, screenHeight, this);

        Sprites.initializeSprites(getResources(), character);

        player = new Player(difficulty, character);
    }

    public Player getPlayer() {
        return player;
    }

    private void spawnVehiclesAndLogs() {
        // Spawns Racecars and Small Logs every second
        if (framesPassed % 60 == 0) {
            vehicles.add(new Racecar(-TileMap.getTileWidth(),
                    screenHeight - TileMap.getBorderHeight() - (9 * TileMap.getTileWidth())));
            vehicles.add(new Racecar(-TileMap.getTileWidth(),
                    screenHeight - TileMap.getBorderHeight() - (11 * TileMap.getTileWidth())));
            logs.add(new Log(2, -2 * TileMap.getTileWidth(), screenHeight
                    - TileMap.getBorderHeight() - (4 * TileMap.getTileWidth()), 10));
            logs.add(new Log(2, -2 * TileMap.getTileWidth(), screenHeight
                    - TileMap.getBorderHeight() - (6 * TileMap.getTileWidth()), 10));
            logs.add(new Log(2, screenWidth, screenHeight
                    - TileMap.getBorderHeight() - (15 * TileMap.getTileWidth()), -10));
        }

        // Spawns trucks every three seconds
        if (framesPassed % 180 == 0) {
            vehicles.add(new Truck(screenWidth + TileMap.getTileWidth(),
                    screenHeight - TileMap.getBorderHeight() - (3 * TileMap.getTileWidth())));
            vehicles.add(new Truck(screenWidth + TileMap.getTileWidth(),
                    screenHeight - TileMap.getBorderHeight() - (12 * TileMap.getTileWidth())));
            logs.add(new Log(3, screenWidth, screenHeight
                    - TileMap.getBorderHeight() - (5 * TileMap.getTileWidth()), -5));
            logs.add(new Log(3, screenWidth, screenHeight
                    - TileMap.getBorderHeight() - (7 * TileMap.getTileWidth()), -5));
            logs.add(new Log(3, -3 * TileMap.getTileWidth(), screenHeight
                    - TileMap.getBorderHeight() - (14 * TileMap.getTileWidth()), 5));
        }

        // Spawns cars every 2 seconds
        if (framesPassed % 120 == 0) {
            vehicles.add(new Car(screenWidth + TileMap.getTileWidth(),
                    screenHeight - TileMap.getBorderHeight() - (2 * TileMap.getTileWidth())));
            vehicles.add(new Car(screenWidth + TileMap.getTileWidth(),
                    screenHeight - TileMap.getBorderHeight() - (10 * TileMap.getTileWidth())));
        }
    }

    private void updateVehicles() {
        // Moves each vehicle and checks for collision
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
            if (Util.checkCollision(player.getPosX(), player.getPosY(), TileMap.getTileWidth(),
                    vehicle.getPosX(), vehicle.getPosY(),
                    vehicle.getWidth() * TileMap.getTileWidth())) {
                vehicle.collisionOccurred(player);
            }
        }

        // Removes vehicles that are off screen
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getPosX() < -TileMap.getTileWidth() * 3
                    || vehicles.get(i).getPosX() > screenWidth + TileMap.getTileWidth() * 3) {
                vehicles.remove(vehicles.get(i));
            }
        }
    }

    private void updateLogs() {
        player.setOnLog(false);
        // Moves each log and moves player if on log
        for (Log log: logs) {
            if (Util.checkCollision(player.getPosX(), player.getPosY(),
                    TileMap.getTileWidth(), log.getPosX(),
                    log.getPosY(), log.getLength() * TileMap.getTileWidth())) {
                player.setOnLog(true);
                player.setPosX(player.getPosX() + log.getSpeed());
            }
            log.move();
        }

        // Removes logs that are off screen
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getPosX() < -TileMap.getTileWidth() * 4
                    || logs.get(i).getPosX() > screenWidth + TileMap.getTileWidth() * 4) {
                logs.remove(logs.get(i));
            }
        }
    }

    public void update() {
        spawnVehiclesAndLogs();
        updateVehicles();
        updateLogs();

        // Checks if player is on a bad tile or out of bounds
        player.checkBadTile();
        if (player.getPosX() > screenWidth || player.getPosX() < -TileMap.getTileWidth()) {
            player.setLives(player.getLives() - 1);
            player.resetPos();
        }

        // Adds points if height is less than before
        PointSystem.addPoints(0, player.getYIdx());

        // Checks if player has lost all of their lives or if they have reached the end
        if (player.getLives() <= 0) {
            endGame();
        }

        // Checks if player is on the win tile
        if (TileMap.getTile(0, player.getYIdx()) == 3) {
            winGame();
        }

        framesPassed++;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // Paint tile map to screen
        tileMap.draw(canvas);

        // Paint text to screen
        canvas.drawText(String.valueOf(thread.getAvgFPS()), 50, 50, textPaint);
        canvas.drawText("Name: " + name, 50, 100, textPaint);
        canvas.drawText("High Score: " + PointSystem.getMaxPoints(), 50, 150, textPaint);
        canvas.drawText("Score: " + PointSystem.getPoints(), 50, 200, textPaint);
        canvas.drawText("Difficulty: " + difficulty, 50, 250, textPaint);
        canvas.drawText("Lives: " + player.getLives(), 50, 300, textPaint);

        // Paint Vehicles to screen
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                canvas.drawBitmap(Sprites.getCarSprite(), vehicle.getPosX(),
                        vehicle.getPosY(), spritePaint);
            }
            if (vehicle instanceof Racecar) {
                canvas.drawBitmap(Sprites.getRacecarSprite(), vehicle.getPosX(),
                        vehicle.getPosY(), spritePaint);
            }
            if (vehicle instanceof Truck) {
                canvas.drawBitmap(Sprites.getTruckSprite(), vehicle.getPosX(),
                        vehicle.getPosY(), spritePaint);
            }
        }

        // Paint Logs to screen
        for (Log log : logs) {
            if (log.getLength() == 2) {
                canvas.drawBitmap(Sprites.getLog2Sprite(),
                        log.getPosX(), log.getPosY(), spritePaint);
            }
            if (log.getLength() == 3) {
                canvas.drawBitmap(Sprites.getLog3Sprite(),
                        log.getPosX(), log.getPosY(), spritePaint);
            }
        }

        // Paint player sprite to screen
        canvas.drawBitmap(Sprites.getPlayerSprite(),
                player.getPosX(), player.getPosY(), spritePaint);
    }

    public GameThread getThread() {
        return thread;
    }

    public void endGame() {
        int score = PointSystem.getMaxPoints();
        Intent intent = new Intent(getContext(), GameOver.class);
        intent.putExtra("score", Integer.toString(score));
        getContext().startActivity(intent);
    }

    public void winGame() {
        int score = PointSystem.getMaxPoints();
        Intent intent = new Intent(getContext(), GameWin.class);
        intent.putExtra("score", Integer.toString(score));
        getContext().startActivity(intent);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if (thread.getState().equals(Thread.State.TERMINATED)) {
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            thread = new GameThread(surfaceHolder, this);
        }
        thread.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format,
                               int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        thread.stopLoop();
    }
}
