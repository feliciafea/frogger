package edu.frogger.game;

public class Car extends Vehicle {
    public Car(int posX, int posY) {
        super(posX, posY, 1, -10);
    }
    @Override
    public void collisionOccurred(Player player) {
        player.setLives(player.getLives() - 1);
        player.resetPos();
    }
}
