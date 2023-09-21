package edu.frogger.game;

import static org.junit.Assert.assertEquals;

import android.graphics.Point;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class PointSystemTests {

    @Test
    public void testRoadCrossing() {
        PointSystem.resetPoints();
        PointSystem.resetMaxPoints();

        ArrayList<Integer> roadRows = new ArrayList<Integer>();
        for (int i = TileMap.getMapHeight() - 1; i >= 0; i--) {
            if (TileMap.getTile(5, i) == 1) {
                roadRows.add(i);
            }
        }

        int expectedPoints = 20;
        for (int i = 0; i < roadRows.size(); i++) {
            PointSystem.addPoints(5, roadRows.get(i)-1);
            assertEquals(expectedPoints, PointSystem.getPoints());
            expectedPoints += 20;
        }
    }

    @Test
    public void testWaterCrossing() {
        PointSystem.resetPoints();
        PointSystem.resetMaxPoints();

        ArrayList<Integer> waterRows = new ArrayList<Integer>();
        for (int i = TileMap.getMapHeight() - 1; i >= 0; i--) {
            if (TileMap.getTile(5, i) == 2) {
                waterRows.add(i);
            }
        }

        int expectedPoints = 30;
        for (int i = 0; i < waterRows.size() - 1; i++) {
            PointSystem.addPoints(5, waterRows.get(i)-1);
            assertEquals(expectedPoints, PointSystem.getPoints());
            expectedPoints += 30;
        }
        expectedPoints += 40;
        PointSystem.addPoints(5, waterRows.get(waterRows.size() - 1)-1);
        assertEquals(expectedPoints, PointSystem.getPoints());
    }

    @Test
    public void testGrassCrossing() {
        PointSystem.resetPoints();
        PointSystem.resetMaxPoints();

        ArrayList<Integer> grassRows = new ArrayList<Integer>();
        for (int i = TileMap.getMapHeight() - 1; i >= 0; i--) {
            if (TileMap.getTile(5, i) == 0) {
                grassRows.add(i);
            }
        }

        int expectedPoints = 10;
        for (int i = 0; i < grassRows.size(); i++) {
            PointSystem.addPoints(5, grassRows.get(i)-1);
            assertEquals(expectedPoints, PointSystem.getPoints());
            expectedPoints += 10;
        }
    }

    @Test
    public void testSidewaysBackwardsNoPoints() {
        PointSystem.resetPoints();
        PointSystem.resetMaxPoints();

        int xPos = 5;
        int yPos = 14;
        PointSystem.addPoints(xPos, yPos);
        assertEquals(10, PointSystem.getPoints());
        xPos--;
        PointSystem.addPoints(xPos, yPos);
        assertEquals(10, PointSystem.getPoints());
        xPos += 2;
        PointSystem.addPoints(xPos, yPos);
        assertEquals(10, PointSystem.getPoints());
        yPos++;
        PointSystem.addPoints(xPos, yPos);
        assertEquals(10, PointSystem.getPoints());
    }

    @Test
    public void testSameTileNoPoints() {
        PointSystem.resetPoints();
        PointSystem.resetMaxPoints();
        
        PointSystem.addPoints(5, 14);
        assertEquals(10, PointSystem.getPoints());
        PointSystem.addPoints(5, 13);
        assertEquals(30, PointSystem.getPoints());
        PointSystem.addPoints(5, 14);
        assertEquals(30, PointSystem.getPoints());
    }

}
