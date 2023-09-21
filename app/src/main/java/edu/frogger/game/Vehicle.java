package edu.frogger.game;

public class Vehicle {

    private int posX;
    private final int posY;
    private final int speed;
    private final int width;

    public Vehicle(int posX, int posY, int width, int speed) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.speed = speed;
    }

    public void move() {
        posX += speed;
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

    public int getWidth() {
        return width;
    }

    public void collisionOccurred(Player player) {
        player.resetPos();
    };
}
