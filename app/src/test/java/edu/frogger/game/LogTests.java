package edu.frogger.game;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LogTests {

    @Test
    public void testLogMovesSlow() {
        Log log = new Log(2, 0, 0, 5);
        log.move();
        assertEquals(log.getPosX(), 5);
        log.move();
        assertEquals(log.getPosX(), 10);
        log.move();
        assertEquals(log.getPosX(), 15);
    }

    @Test
    public void testLogMovesFast() {
        Log log = new Log(2, 0, 0, 10);
        log.move();
        assertEquals(log.getPosX(), 10);
        log.move();
        assertEquals(log.getPosX(), 20);
        log.move();
        assertEquals(log.getPosX(), 30);
    }

    @Test
    public void testLogMovesDifferentDirections() {
        Log log1 = new Log(3, 100, 0, 10);
        Log log2 = new Log(2, 100, 0, -10);
        log1.move();
        log2.move();
        assertEquals(log1.getPosX(), 110);
        assertEquals(log2.getPosX(), 90);
    }
}
