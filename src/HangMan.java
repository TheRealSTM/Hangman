import  java.util.Scanner;
import java.util.Random;
class HangMan {
    public static void main(String[] args) {
        String[] wordList = {"pickles", "carnival", "dance", "karaoke"};
        Random rand = new Random();
        boolean gameStatus;
        Scanner getUserInput = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String username = getUserInput.nextLine();
        System.out.println("Welcome, " + username +
                " to the initial version of Hangman");
        System.out.println("Do you want to play Hangman");
        String playGame = getUserInput.nextLine();
        while (!playGame.equals("Yes") || !playGame.equals("No")) {
            System.out.println("Sorry, I don't understand. Do you want " +
                    " to play Hangman");
            playGame = getUserInput.nextLine();
        }
        if (playGame.equals("Yes")) {
            gameStatus = true;
        } else {
            gameStatus = false;
        }
        while (gameStatus) {
            System.out.println("We have started our Hangman game!");
            break;
        }
        System.out.println("Thanks for playing Hangman");
    }
}
