package edu.frogger.game;

public class Truck extends Vehicle {
    public Truck(int posX, int posY) {
        super(posX, posY, 2, -5);
    }

    @Override
    public void collisionOccurred(Player player) {
        player.setLives(player.getLives() - 3);
        player.resetPos();
    }
}
