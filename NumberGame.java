import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int roundsplayed = 0;
        int roundswon = 0;

        System.out.println("Welcome to the number guessing game!");

        boolean playAgain = true;

        while (playAgain) {
            roundsplayed++;
            int numbertoguess = random.nextInt(100) + 1;
            int attempts = 7;

            System.out.println("\nRound: " + roundsplayed);
            System.out.println("User have to guess the number from 1 to 100");
            System.out.println("You have " + attempts + " attempts to guess the number");

            boolean guesscorrectly = false;

            while (attempts > 0) {
                System.out.println("Enter the number to guess:");
                int userguess = scanner.nextInt();

                if (userguess < 1 || userguess > 100) {
                    System.out.println("Invalid input! Please enter a number between 1 to 100");
                }

                else {
                    if (userguess == numbertoguess) {
                        System.out.println("Congratulations!! You have guess the number correctly");
                        guesscorrectly = true;
                        roundswon++;
                        break;
                    } else if (userguess < numbertoguess) {
                        System.out.println("Your guess is too low!");
                    } else {
                        System.out.println("Your guess is too high!");
                    }
                }

                attempts--;
                System.out.println("You have " + attempts + " attempts left");
            }

            if (!guesscorrectly) {
                System.out.println("Out of attempts!! The number to guess was: " + numbertoguess);
            }

            System.out.println("Rounds played: " + roundsplayed);
            System.out.println("Rounds won: " + roundswon);

            System.out.println("Do you want to play again? (y/n):");
            String response = scanner.next();
            playAgain = response.equals("y");
        }

        System.out.println("Thanks for playing");
        scanner.close();
    }
}