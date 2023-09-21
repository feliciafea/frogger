package edu.frogger.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerMovementTest {

    int screenWidth = 1056;
    int screenHeight = 1896;


    @Test
    public void testMoveUp() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());

        Player player = new Player("Easy", 2);
        player.setPosY(posY - TileMap.getTileWidth());
        assertEquals(player.getPosY(), posY - TileMap.getTileWidth());
        assertEquals(player.getPosX(), posX);
    }

    @Test
    public void testMoveDown() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int posY = TileMap.getBorderHeight() + (14 * TileMap.getTileWidth());

        Player player = new Player("Easy", 2);
        player.setPosY(posY + TileMap.getTileWidth());
        assertEquals(player.getPosY(), posY + TileMap.getTileWidth());
        assertEquals(player.getPosX(), posX);
    }

    @Test
    public void testMoveRight() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());

        Player player = new Player("Easy", 2);
        player.setPosX(posX + TileMap.getTileWidth());
        assertEquals(player.getPosX(), posX + TileMap.getTileWidth());
        assertEquals(player.getPosY(), posY);
    }

    @Test
    public void testMoveLeft() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());

        Player player = new Player("Easy", 2);
        player.setPosX(posX - TileMap.getTileWidth());
        assertEquals(player.getPosX(), posX - TileMap.getTileWidth());
        assertEquals(player.getPosY(), posY);
    }

    @Test
    public void testMoveOffScreen() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());

        Player player = new Player("Easy", 2);

        //test right: posX < TileMap.getMapWidth() * TileMap.getTileWidth() && posX >= 0
        for (int i = 0; i < 200; i++) {
            player.setPosX(posX + TileMap.getTileWidth());
        }

        assertEquals(528, player.getPosX());
        assertEquals(player.getPosY(), posY);

        //test left
        posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());

        for (int i = 0; i < 200; i++) {
            player.setPosX(posX - TileMap.getTileWidth());
        }

        assertEquals(352, player.getPosX());
        assertEquals(player.getPosY(), posY);

        //test down
        posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());
        player.setPosX(posX);

        for (int i = 0; i < 200; i++) {
            player.setPosY(posY + TileMap.getTileWidth());
        }

        assertEquals(1564, player.getPosY());
        assertEquals(player.getPosX(), posX);

        //test up
        posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());

        for (int i = 0; i < 200; i++) {
            player.setPosY(posY - TileMap.getTileWidth());
        }

        assertEquals(1476, player.getPosY());
        assertEquals(player.getPosX(), posX);

    }





}