package edu.frogger.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RespawnTests {

    int screenWidth = 1056;
    int screenHeight = 1896;

    @Test
    public void testPointsRacecarCollision() {
        int startPosX = 500;
        int startPosY = 600;

        Vehicle raceCar = new Racecar(startPosX, startPosY);
        Player player = new Player("Easy", 2);

        PointSystem.setPoints(100);
        PointSystem.setMaxPoints(100);

        raceCar.collisionOccurred(player);
        
        assertEquals(PointSystem.getPoints(), 0);
        assertEquals(PointSystem.getMaxPoints(), 100);
    }

    @Test
    public void testPointsTruckCollision() {
        int startPosX = 500;
        int startPosY = 600;

        Vehicle truck = new Truck(startPosX, startPosY);
        Player player = new Player("Easy", 2);

        PointSystem.setPoints(100);
        PointSystem.setMaxPoints(100);

        truck.collisionOccurred(player);

        assertEquals(PointSystem.getPoints(), 0);
        assertEquals(PointSystem.getMaxPoints(), 100);
    }

    @Test
    public void testPointsCarCollision() {
        int startPosX = 500;
        int startPosY = 600;

        Vehicle car = new Car(startPosX, startPosY);
        Player player = new Player("Easy", 2);

        PointSystem.setPoints(100);
        PointSystem.setMaxPoints(100);

        car.collisionOccurred(player);

        assertEquals(PointSystem.getPoints(), 0);
        assertEquals(PointSystem.getMaxPoints(), 100);
    }

    @Test
    public void testRespawnRacecarCollision() {
        int startPosX = 500;
        int startPosY = 600;
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int respawnPosX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int respawnPosY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());

        Player player = new Player("Easy", 2);
        Vehicle raceCar = new Racecar(startPosX, startPosY);

        player.setPosY(startPosY);

        assertEquals(player.getPosY(), startPosY);

        raceCar.collisionOccurred(player);

        assertEquals(player.getPosX(), respawnPosX);
        assertEquals(player.getPosY(), respawnPosY);
    }

    @Test
    public void testRespawnTruckCollision() {
        int startPosX = 500;
        int startPosY = 600;
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int respawnPosX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int respawnPosY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());

        Player player = new Player("Easy", 2);
        Vehicle truck = new Racecar(startPosX, startPosY);

        player.setPosY(startPosY);

        assertEquals(player.getPosY(), startPosY);

        truck.collisionOccurred(player);

        assertEquals(player.getPosX(), respawnPosX);
        assertEquals(player.getPosY(), respawnPosY);
    }

    @Test
    public void testRespawnCarCollision() {
        int startPosX = 500;
        int startPosY = 600;
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int respawnPosX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int respawnPosY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());

        Player player = new Player("Easy", 2);
        Vehicle car = new Car(startPosX, startPosY);

        player.setPosY(startPosY);

        assertEquals(player.getPosY(), startPosY);

        car.collisionOccurred(player);

        assertEquals(player.getPosX(), respawnPosX);
        assertEquals(player.getPosY(), respawnPosY);
    }

    @Test
    public void testWaterTileRespawn() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        final int initPosX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        final int initPosY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());
        int posY = initPosY;

        Player player = new Player("Easy", 1);
        for(int i=0; i<3; i++) {
            player.setPosY(posY - TileMap.getTileWidth());
            posY -= TileMap.getTileWidth();
            player.checkBadTile();
        }
        assertEquals(initPosX, player.getPosX());
        assertEquals(initPosY, player.getPosY());
    }

    @Test
    public void testWaterTileResetPoints() {
        PointSystem.setPoints(100);
        PointSystem.setMaxPoints(100);
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        final int initPosX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        final int initPosY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());
        int posY = initPosY;
        Player player = new Player("Easy", 1);
        for(int i=0; i<3; i++) {
            player.setPosY(posY - TileMap.getTileWidth());
            posY -= TileMap.getTileWidth();
            player.checkBadTile();
        }
        assertEquals(PointSystem.getPoints(), 0);
        assertEquals(PointSystem.getMaxPoints(), 100);
    }
}
