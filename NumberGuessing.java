import java.util.Scanner;
import java.util.Random;
import java.time.Duration;
import java.time.Instant;

class NumberGenerator {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String play = "yes";

        while (play.equals("yes")) {
            System.out.println("Do you want to play in Challenge Mode? (yes/no)");
            String challengeMode = sc.next().toLowerCase();
            int maxTries = challengeMode.equals("yes") ? 7 : Integer.MAX_VALUE;

            Random rd = new Random();
            int randnum = rd.nextInt(100) + 1; // Generates random number between 1 and 100
            int guess = -1;
            int tries = 0;

            Instant startTime = Instant.now(); // Start time for guessing

            while (guess != randnum && tries < maxTries) {
                System.out.println("Guess the number between 1 and 100:");
                guess = sc.nextInt();
                tries++;

                if (guess == randnum) {
                    Instant endTime = Instant.now(); // End time for guessing
                    Duration timeElapsed = Duration.between(startTime, endTime);

                    System.out.println("Amazing!! You guessed the right number!");
                    System.out.println("You took " + tries + " tries and " + timeElapsed.toSeconds() + " seconds to guess it.");
                    break;
                }
                else if (guess > randnum) {
                    System.out.println("The number is too high!");

                    if (Math.abs(guess - randnum) <= 10) {
                        System.out.println("But you're close! Try a smaller number.");
                    }
                }
                else {
                    System.out.println("The number is too low!");

                    if (Math.abs(guess - randnum) <= 10) {
                        System.out.println("But you're close! Try a bigger number.");
                    }
                }

                if (tries >= maxTries && guess != randnum) {
                    System.out.println("You've run out of tries! The correct number was " + randnum + ".");
                }
            }

            System.out.println("Would you like to play again? (yes/no)");
            play = sc.next().toLowerCase();
        }
        sc.close();
    }
}
