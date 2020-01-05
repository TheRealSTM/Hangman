import java.util.HashSet;
import java.util.Random;
import  java.util.Scanner;

class HangMan {
    public static void main(String[] args) {
        boolean gameStatus, discoveredWord = false;
        int remainingGuesses = 5, neededLetters;
        String guess, goal;
        char charGuess;
        String[] wordList = {"pickles", "carnival", "dance", "karaoke"};
        Random rand = new Random();
        goal = wordList[rand.nextInt(wordList.length)];
        neededLetters = goal.length();
        HashSet<Character> incorrectGuesses = new HashSet<>();
        HashSet<Character> correctGuesses = new HashSet<>();

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String username = input.nextLine();
        System.out.println("Welcome, " + username +
                " to the initial version of Hangman");
        gameStatus = wantsToPlayHangman();

        while (gameStatus) {
            System.out.println("We have started our Hangman game! ");

            while (remainingGuesses > 0 && !discoveredWord) {
                System.out.println("You have " + remainingGuesses +
                        " incorrect guesses remaining.");
                System.out.println("Please enter your next guess");
                guess = input.nextLine();
                if (guessValid(guess) && !correctGuesses.contains(guess.charAt(0)) &&
                    !incorrectGuesses.contains(guess.charAt(0))) {
                    charGuess = guess.charAt(0);
                    if (compareGuessToGoal(charGuess, goal)) {
                        neededLetters--;
                        correctGuesses.add(charGuess);
                        System.out.println("Hooray, " + guess +
                                " is in the mystery word!");
                    } else {
                        remainingGuesses--;
                        incorrectGuesses.add(charGuess);
                        System.out.println("Ugh, " + guess +
                                " is not in the mystery word! You have " +
                                remainingGuesses + "left!");
                    }
                    if (neededLetters == 0) {
                        discoveredWord = true;
                    }
                } else {
                    System.out.println("Invalid guess, only letters will be accepted!");
                }
            }  // end while
            if (discoveredWord) {
                System.out.println("Congrats, you won!");
            } else {
                System.out.println("Nice try, but you lost!");
            }
            gameStatus = wantsToPlayHangman();
        }  // end while
        System.out.println("Thanks for playing Hangman");
    }

    private static boolean wantsToPlayHangman() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to play Hangman");
        String playGame = input.nextLine().toLowerCase();
        while (!"yes".equals(playGame) && !"no".equals(playGame)) {
            System.out.println("Sorry, I don't understand. Do you want " +
                    " to play Hangman?");
            playGame = input.nextLine();
        }
        return "yes".equals(playGame);
    }

    private static boolean compareGuessToGoal(char guess, String goal) {
        boolean foundMatch = false;
        for (int i = 0; i < goal.length(); i++) {
            if (goal.charAt(i) == guess) {
                foundMatch = true;
                break;
            }
        }
        return foundMatch;
    }

    private static boolean guessValid(String guess) {
        guess = guess.toLowerCase();
        if (guess.length() != 1) {
            return false;
        }
        return guess.charAt(0) >= 'a' && guess.charAt(0) <= 'z';
    }
}
