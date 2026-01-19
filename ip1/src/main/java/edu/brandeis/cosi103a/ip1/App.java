package edu.brandeis.cosi103a.ip1;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        final int SIDES = 6;
        final int TURNS = 10;
        final int MAX_REROLLS = 2;

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter name for Player 1 (or press Enter for 'Player 1'): ");
        String p1 = sc.nextLine().trim();
        if (p1.isEmpty()) p1 = "Player 1";
        System.out.print("Enter name for Player 2 (or press Enter for 'Player 2'): ");
        String p2 = sc.nextLine().trim();
        if (p2.isEmpty()) p2 = "Player 2";

        int[] scores = new int[2];

        for (int turn = 1; turn <= TURNS; turn++) {
            for (int player = 0; player < 2; player++) {
                String name = (player == 0) ? p1 : p2;
                System.out.println();
                System.out.println("Turn " + turn + " - " + name + "'s turn. Current score: " + scores[player]);

                int rerolls = 0;
                int die = DiceGame.rollDie(rand, SIDES);

                while (true) {
                    System.out.println("Rolled: " + die);

                    if (rerolls >= MAX_REROLLS) {
                        System.out.println("No rerolls left.");
                        break;
                    }

                    System.out.print("Reroll? (y/n): ");
                    String line = sc.nextLine().trim().toLowerCase();

                    if (line.equals("y") || line.equals("yes")) {
                        die = DiceGame.rollDie(rand, SIDES);
                        rerolls++;
                        continue;
                    } else if (line.equals("n") || line.equals("no") || line.isEmpty()) {
                        break;
                    } else {
                        System.out.println("Please enter 'y' or 'n'.");
                    }
                }

                scores[player] += die;
                System.out.println(name + " ends turn with " + die + " points. Total: " + scores[player]);
            }
        }

        System.out.println();
        System.out.println("Game over!");
        System.out.println(p1 + " score: " + scores[0]);
        System.out.println(p2 + " score: " + scores[1]);

        int winner = DiceGame.determineWinner(scores);
        if (winner == 0) {
            System.out.println(p1 + " wins!");
        } else if (winner == 1) {
            System.out.println(p2 + " wins!");
        } else {
            System.out.println("It's a tie!");
        }

        sc.close();
    }
}
