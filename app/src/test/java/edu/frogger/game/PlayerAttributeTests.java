package edu.frogger.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerAttributeTests {

    @Test
    public void testDiffLives() {
        Player player1 = new Player("Easy", 1);
        Player player2 = new Player("Medium", 1);
        Player player3 = new Player("Hard", 1);
        assertEquals(player1.getLives(), 3);
        assertEquals(player2.getLives(), 2);
        assertEquals(player3.getLives(), 1);
    }

    @Test
    public void testDiffChar() {
        Player player1 = new Player("Easy", 1);
        Player player2 = new Player("Easy", 2);
        Player player3 = new Player("Easy", 3);
        assertEquals(player1.getCharacter(), "Billy");
        assertEquals(player2.getCharacter(), "Josh");
        assertEquals(player3.getCharacter(), "Aurora");
    }
}
