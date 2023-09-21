package edu.frogger.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayersNameTest {
    @Test
    public void emptyStringTest() {
        assertEquals(InitActivity.isEmpty(null), true);
        assertEquals(InitActivity.isEmpty(""), true);
    }

    @Test
    public void whiteSpaceTest() {
        assertEquals(InitActivity.isEmpty(" "), true);
        assertEquals(InitActivity.isEmpty("    "), true);

    }
    @Test
    public void validNameTest() {
        assertEquals(InitActivity.isEmpty(" Bob"), false);
        assertEquals(InitActivity.isEmpty(" B o b"), false);
        assertEquals(InitActivity.isEmpty(" Robert"), false);
        assertEquals(InitActivity.isEmpty(" Bo bb y"), false);
        assertEquals(InitActivity.isEmpty("            Roberto"), false);
    }
}
