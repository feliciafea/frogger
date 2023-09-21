package edu.frogger.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TileMapTests {

    int screenWidth = 1056;
    int screenHeight = 1896;

    @Test
    public void testTileWidth() {
        int tileWidth = screenWidth / 12; // 88
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        assertEquals(TileMap.getTileWidth(), tileWidth);
    }

    @Test
    public void testTileMapSize() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        int borderWidth = (screenWidth - (TileMap.getTileWidth() * TileMap.getMapWidth())) / 2;
        int borderHeight = (screenHeight - (TileMap.getTileWidth() * TileMap.getMapHeight())) / 2;
        assertEquals(TileMap.getBorderWidth(), borderWidth);
        assertEquals(TileMap.getBorderHeight(), borderHeight);
        assertEquals(screenWidth, (TileMap.getBorderWidth() * 2) + (TileMap.getMapWidth() * TileMap.getTileWidth()));
        assertEquals(screenHeight, (TileMap.getBorderHeight() * 2) + (TileMap.getMapHeight() * TileMap.getTileWidth()));
    }




}
