import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import  java.util.Scanner;

class HangMan {
    public static void main(String[] args) {
        boolean gameStatus, discoveredWord;
        int remainingGuesses, neededLetters, letCnt;
        String guess, goal;
        StringBuilder attempt;
        char charGuess;
        String[] wordList = {"pickles", "carnival", "dance", "karaoke"};
        String[] fullWordList = getFullWordList();
        Random rand = new Random();

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

            // Initialize game state
            goal = fullWordList[rand.nextInt(fullWordList.length)];
            neededLetters = goal.length();
            remainingGuesses = 5;
            correctGuesses.clear();
            incorrectGuesses.clear();
            discoveredWord = false;
            attempt = new StringBuilder();
            initializeAttempt(attempt, neededLetters);


            while (remainingGuesses > 0 && !discoveredWord) {
                System.out.println("You have " + remainingGuesses +
                        " incorrect guesses remaining to guess the " + neededLetters
                        + " letter word.");
                System.out.println("Please enter your next guess");
                guess = input.nextLine();
                if (guessValid(guess) && !correctGuesses.contains(guess.charAt(0)) &&
                    !incorrectGuesses.contains(guess.charAt(0))) {
                    charGuess = guess.charAt(0);
                    if (compareGuessToGoal(charGuess, goal)) {
                        letCnt = getNumMatches(charGuess, goal, attempt);
                        neededLetters -= letCnt;
                        correctGuesses.add(charGuess);
                        System.out.println("Hooray, " + guess +
                                " is in the mystery word! The current word board is " + attempt);
                    } else {
                        remainingGuesses--;
                        incorrectGuesses.add(charGuess);
                        System.out.println("Ugh, " + guess +
                                " is not in the mystery word! You have " +
                                remainingGuesses + " guesses left! The current word board is " + attempt);
                    }
                    if (neededLetters == 0) {
                        discoveredWord = true;
                    }
                } else {
                    System.out.println("Invalid guess, only single, not previously guessed letters will be accepted!");
                }
            }  // end while
            if (discoveredWord) {
                System.out.println("Congrats, you won!");
            } else {
                System.out.println("Nice try, but you lost! The hidden word was " + goal);
            }

            gameStatus = wantsToPlayHangman();
        }  // end while
        System.out.println("Thanks for playing Hangman");
    }

    private static String[] getFullWordList() {
        File file = new File("C:\\Users\\stmon\\IdeaProjects\\HangMan\\scrabble_dict.txt");
        ArrayList<String> wordList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String word;
            while ((word = br.readLine()) != null) {
                wordList.add(word.toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the file: " + file);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("I/O Exception occurred!");
            System.exit(0);
        }

        String[] res = new String[wordList.size()];
        res = wordList.toArray(res);
        return res;
    }

    private static void initializeAttempt(StringBuilder attempt, int neededLetters) {
        for (int i = 0; i < neededLetters; i++) {
            attempt.append("_");
        }
    }

    private static int getNumMatches(char charGuess, String goal, StringBuilder attempt) {
        int cnt = 0;
        for (int i = 0; i < goal.length(); i++) {
            if (goal.charAt(i) == charGuess) {
                cnt++;
                attempt.replace(i, i + 1, charGuess + "");
            }
        }
        return cnt;
    }

    private static boolean wantsToPlayHangman() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to play Hangman? (yes/no)");
        String playGame = input.nextLine().toLowerCase();
        while (!"yes".equals(playGame) && !"no".equals(playGame)) {
            System.out.println("Sorry, I don't understand. Do you want " +
                    "to play Hangman? (yes/no)");
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
