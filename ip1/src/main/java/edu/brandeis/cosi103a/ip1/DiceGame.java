package edu.brandeis.cosi103a.ip1;

import java.util.Random;

public class DiceGame {

    /** Roll a die with the given number of sides using the provided Random. */
    public static int rollDie(Random rand, int sides) {
        if (rand == null) throw new IllegalArgumentException("rand must not be null");
        if (sides < 1) throw new IllegalArgumentException("sides must be >= 1");
        return rand.nextInt(sides) + 1;
    }

    /**
     * Given an array of die results (first element is the initial roll, subsequent elements are rerolls),
     * and the maximum allowed rerolls, return the final die value that would be counted for the turn.
     */
    public static int finalDieFromRolls(int[] rolls, int maxRerolls) {
        if (rolls == null || rolls.length == 0) throw new IllegalArgumentException("rolls must contain at least one value");
        if (maxRerolls < 0) throw new IllegalArgumentException("maxRerolls must be >= 0");

        int usable = Math.min(rolls.length, maxRerolls + 1); // initial roll + up to maxRerolls
        return rolls[usable - 1];
    }

    /**
     * Determine the winner from a two-element scores array.
     * Returns 0 if player 1 wins, 1 if player 2 wins, 2 if tie.
     */
    public static int determineWinner(int[] scores) {
        if (scores == null || scores.length < 2) throw new IllegalArgumentException("scores must have at least two elements");
        if (scores[0] > scores[1]) return 0;
        if (scores[1] > scores[0]) return 1;
        return 2;
    }
}
