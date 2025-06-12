import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        String[] choices = {"rock", "paper", "scissor"};
        boolean playAgain = true;
        int userWins = 0;
        int computerWins = 0;

        while (playAgain) {
            System.out.println("Choose mode: 1) Normal 2) Challenge (3 rounds)");
            int mode = sc.nextInt();
            sc.nextLine();

            if (mode == 1) {
                Boolean result = playNormalMode(sc, rd, choices);
                if (result != null) {
                    if (result) {
                        System.out.println("You triumphed this round!");
                        userWins++;
                    } else {
                        System.out.println("The computer outsmarted you!");
                        computerWins++;
                    }
                }
            } else if (mode == 2) {
                playChallengeMode(sc, rd, choices);
            } else {
                System.out.println("Invalid mode. Please choose 1 or 2.");
                continue;
            }

            System.out.println("Do you want to play again? (yes/no): ");
            String playAgainChoice = sc.nextLine().toLowerCase();
            playAgain = playAgainChoice.equals("yes");
        }

        System.out.println("Final Score for normal round:");
        System.out.println("You won: " + userWins + " times");
        System.out.println("Computer won: " + computerWins + " times");
        sc.close();
    }

    public static Boolean playNormalMode(Scanner sc, Random rd, String[] choices) {
        int computerChoiceIndex = rd.nextInt(3);
        String computerChoice = choices[computerChoiceIndex];

        System.out.println("Enter your choice (rock, paper, or scissor): ");
        String userChoice = sc.nextLine().toLowerCase();

        if (!isValidChoice(userChoice)) {
            System.out.println("Invalid choice. Please select rock, paper, or scissor.");
            return null;
        }

        System.out.println("User's choice: " + userChoice);
        System.out.println("Computer's choice: " + computerChoice);

        return determineWinner(userChoice, computerChoice);
    }

    public static void playChallengeMode(Scanner sc, Random rd, String[] choices) {
        int attempts = 3;
        int userWins = 0;
        int computerWins = 0;

        for (int i = 0; i < attempts; i++) {
            int computerChoiceIndex = rd.nextInt(3);
            String computerChoice = choices[computerChoiceIndex];

            System.out.println("Round " + (i + 1) + ": Enter your choice (rock, paper, or scissor): ");
            String userChoice = sc.nextLine().toLowerCase();

            if (!isValidChoice(userChoice)) {
                System.out.println("Invalid choice. Please select rock, paper, or scissor.");
                i--;  // This round is invalid, retry this round
                continue;
            }

            System.out.println("User's choice: " + userChoice);
            System.out.println("Computer's choice: " + computerChoice);

            Boolean result = determineWinner(userChoice, computerChoice);

            if (result == null) {
                System.out.println("It's a tie! Try again.");
            } else if (result) {
                System.out.println("You won this round!");
                userWins++;
            } else {
                System.out.println("Computer won this round.");
                computerWins++;
            }
        }

        System.out.println("Challenge mode over!");
        System.out.println("User won " + userWins + " out of 3 rounds.");
        System.out.println("Computer won " + computerWins + " out of 3 rounds.");
    }

    public static boolean isValidChoice(String choice) {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissor");
    }

    public static Boolean determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return null;
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissor")) ||
                (userChoice.equals("scissor") && computerChoice.equals("paper")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock"))) {
            return true;
        } else {
            return false;
        }
    }
}
