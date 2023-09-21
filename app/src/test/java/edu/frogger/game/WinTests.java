package edu.frogger.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Intent;

import org.junit.Test;

import java.util.ArrayList;

public class WinTests {

    int screenWidth = 1056;
    int screenHeight = 1896;

    @Test
    public void displayMaxPointsOnWin() {
        int score = PointSystem.getMaxPoints();
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

        String scoreString = Integer.toString(PointSystem.getMaxPoints());
        String scoreText = "Highest Score: " + scoreString;
        String excpectedScoreText = "Highest Score: " + 120;

        assertEquals(excpectedScoreText, scoreText);
    }

    @Test
    public void resetCurrentPoints() {
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

        PointSystem.resetPoints();
        PointSystem.resetMaxPoints();
        int pointsExpectedRestart = 0;
        assertEquals(pointsExpectedRestart, PointSystem.getPoints());
    }

    @Test
    public void resetMaxPoints() {
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

        PointSystem.resetPoints();
        PointSystem.resetMaxPoints();
        int pointsExpectedRestart = 0;
        assertEquals(pointsExpectedRestart, PointSystem.getMaxPoints());
    }

    @Test
    public void testGoalPoints() {
        for (int i = TileMap.getMapWidth() - 1; i >= 0; i--) {
            PointSystem.addPoints(i, 0);
            assertEquals(70, PointSystem.getPoints());
            PointSystem.resetPoints();
            PointSystem.resetMaxPoints();
        }

    }

    @Test
    public void testWinTile() {
        TileMap.calcTileAndBorder(screenWidth, screenHeight);
        Player player = new Player("Easy", 1);
        while (TileMap.getTile(5, player.getYIdx()) != 3) {
            player.moveUp();
            PointSystem.addPoints(5, player.getYIdx());
            System.out.println(TileMap.getTile(5, player.getYIdx()));
        }
        assertEquals(370, PointSystem.getPoints());
        PointSystem.resetPoints();
        PointSystem.resetMaxPoints();
        assertEquals(0, PointSystem.getPoints());
    }

    @Test
    public void testWinTilePointAmount() {
        int prevPoints = 0;
        PointSystem.addPoints(5, 14);
        int safeAmount = PointSystem.getPoints() - prevPoints;
        prevPoints = PointSystem.getPoints();
        PointSystem.addPoints(5, 13);
        int roadAmount = PointSystem.getPoints() - prevPoints;
        prevPoints = PointSystem.getPoints();
        PointSystem.addPoints(5, 11);
        int waterAmount = PointSystem.getPoints() - prevPoints;
        prevPoints = PointSystem.getPoints();
        PointSystem.addPoints(5, 0);
        int goalAmount = PointSystem.getPoints() - prevPoints - 30;
        assertEquals(40, goalAmount);
        assertTrue(safeAmount < goalAmount);
        assertTrue(roadAmount < goalAmount);
        assertTrue(waterAmount < goalAmount);
    }

}