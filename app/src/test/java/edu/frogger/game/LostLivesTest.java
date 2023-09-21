package edu.frogger.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LostLivesTest {

    int screenWidth = 1056;
    int screenHeight = 1896;

    @Test
    public void testWaterTileLifeLost() {

        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());

        Player player = new Player("Easy", 1);
        for(int i=0; i<3; i++) {
            player.setPosY(posY - TileMap.getTileWidth());
            posY -= TileMap.getTileWidth();
            player.checkBadTile();
        }
        assertEquals(2, player.getLives());
    }

    @Test
    public void carLivesLost() {
        int startPosX = 500;
        int startPosY = 600;

        Vehicle car = new Car(startPosX, startPosY);
        Player player = new Player("Easy", 1);

        car.collisionOccurred(player);

        assertEquals(player.getLives(), 2);
    }

    @Test
    public void raceCarLivesLost() {
        int startPosX = 500;
        int startPosY = 600;

        Vehicle raceCar = new Racecar(startPosX, startPosY);
        Player player = new Player("Easy", 1);

        raceCar.collisionOccurred(player);

        assertEquals(player.getLives(), 1);
    }

    @Test
    public void truckLivesLost() {
        int startPosX = 500;
        int startPosY = 600;

        Vehicle truck = new Truck(startPosX, startPosY);
        Player player = new Player("Easy", 1);

        truck.collisionOccurred(player);

        assertEquals(player.getLives(), 0);
    }

}
