package edu.frogger.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerLogTests {

    int screenWidth = 1056;
    int screenHeight = 1896;

    @Test
    public void testPlayerDiesLogOffScreen() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());
        Log log = new Log(2, posX, posY, 5);
        Player player = new Player("Easy", 1);
        player.setOnLog(true);

        while (true) {
            if (player.getPosX() <= 0) {;
                player.setLives(player.getLives() - 1);
                break;
            }
            log.move();
            player.setPosX(player.getPosX() - log.getSpeed());
            System.out.println(player.getPosX());
        }

        assertEquals(2, player.getLives());

    }

    @Test
    public void testPlayerOnLog() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());
        Log log = new Log(2, posX, posY, 5);
        Player player = new Player("Easy", 1);
        assertTrue(Util.checkCollision(player.getPosX(), player.getPosY(),
                TileMap.getTileWidth(), log.getPosX(),
                log.getPosY(), log.getLength() *TileMap.getTileWidth()));
        assertEquals(3, player.getLives());
    }

    @Test
    public void testPlayerMovesWithLog() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        int posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());
        Log log = new Log(2, posX, posY, 5);
        Player player = new Player("Easy", 1);
        log.move();
        player.setPosX(player.getPosX() - log.getSpeed());
        assertTrue(Util.checkCollision(player.getPosX(), player.getPosY(),
                TileMap.getTileWidth(), log.getPosX(),
                log.getPosY(), log.getLength() *TileMap.getTileWidth()));
    }

}
