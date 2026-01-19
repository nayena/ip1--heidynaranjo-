package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class DiceGameTest {

    @Test
    public void testRollDieRange() {
        Random rand = new Random(12345L); // deterministic sequence
        for (int i = 0; i < 100; i++) {
            int v = DiceGame.rollDie(rand, 6);
            assertTrue("roll must be >=1", v >= 1);
            assertTrue("roll must be <=6", v <= 6);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRollDieNullRandom() {
        DiceGame.rollDie(null, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRollDieInvalidSides() {
        DiceGame.rollDie(new Random(), 0);
    }

    @Test
    public void testFinalDieFromRollsBasic() {
        assertEquals(3, DiceGame.finalDieFromRolls(new int[] {3}, 2));
        assertEquals(1, DiceGame.finalDieFromRolls(new int[] {4,1}, 2));
        // when more rolls are provided than allowed rerolls, the last allowed is used
        assertEquals(5, DiceGame.finalDieFromRolls(new int[] {2,5,6,3}, 1));
        // when fewer rolls are provided than max rerolls it's fine
        assertEquals(6, DiceGame.finalDieFromRolls(new int[] {6,6}, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFinalDieFromRollsEmpty() {
        DiceGame.finalDieFromRolls(new int[] {}, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFinalDieFromRollsNegativeMax() {
        DiceGame.finalDieFromRolls(new int[] {1}, -1);
    }

    @Test
    public void testDetermineWinner() {
        assertEquals(0, DiceGame.determineWinner(new int[] {10, 5}));
        assertEquals(1, DiceGame.determineWinner(new int[] {3, 9}));
        assertEquals(2, DiceGame.determineWinner(new int[] {7, 7}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDetermineWinnerInvalidScores() {
        DiceGame.determineWinner(new int[] {5});
    }
}
