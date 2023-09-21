package edu.frogger.game;

public class Log {
    private final int length;
    private int posX;
    private final int posY;
    private final int speed;

    public Log(int length, int posX, int posY, int speed) {
        this.length = length;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
    }

    public void move() {
        posX += speed;
    }

    public int getLength() {
        return length;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSpeed() {
        return speed;
    }
}
