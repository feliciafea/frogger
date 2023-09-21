package edu.frogger.game;

public class Racecar extends Vehicle {
    public Racecar(int posX, int posY) {
        super(posX, posY, 1, 20);
    }
    @Override
    public void collisionOccurred(Player player) {
        player.setLives(player.getLives() - 2);
        player.resetPos();
    }
}
