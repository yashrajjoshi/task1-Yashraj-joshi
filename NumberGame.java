import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    static final int MIN = 1, MAX = 100, ATTEMPTS = 7;
    static final Scanner sc = new Scanner(System.in);
    static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("\n=================================");
        System.out.println("        NUMBER GUESSING GAME");
        System.out.println("=================================");
        System.out.println("Guess the secret number between " + MIN + " and " + MAX + ".");
        System.out.println("You get " + ATTEMPTS + " attempts in each round.");

        int rounds = readNumber("Enter number of rounds (1-10): ", 1, 10);
        int totalScore = 0, wins = 0;

        for (int round = 1; round <= rounds; round++) {
            int secret = random.nextInt(MAX - MIN + 1) + MIN;
            int attempts = 0;
            boolean won = false;

            System.out.println("\n----------- ROUND " + round + " -----------");

            while (attempts < ATTEMPTS) {
                int guess = readNumber("Enter your guess (" + MIN + "-" + MAX + "): ",
                                       MIN, MAX);
                attempts++;

                if (guess == secret) {
                    int score = Math.max(40, 100 - (attempts - 1) * 10);
                    totalScore += score;
                    wins++;
                    won = true;
                    System.out.println("Correct! You found it in " + attempts + " attempt(s).");
                    System.out.println("Round score: " + score);
                    break;
                } else if (guess < secret) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }

                if (attempts < ATTEMPTS)
                    System.out.println("Attempts remaining: " + (ATTEMPTS - attempts));
            }

            if (!won) {
                System.out.println("No attempts left. The number was " + secret + ".");
                System.out.println("Round score: 0");
            }
        }

        System.out.println("\n=================================");
        System.out.println("             GAME OVER");
        System.out.println("=================================");
        System.out.println("Rounds played : " + rounds);
        System.out.println("Rounds won    : " + wins);
        System.out.println("Total score   : " + totalScore);
        System.out.println("=================================");
    }

    static int readNumber(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                int n = Integer.parseInt(input);
                if (n >= min && n <= max) return n;
                System.out.println("Please enter a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a whole number.");
            }
        }
    }
}
