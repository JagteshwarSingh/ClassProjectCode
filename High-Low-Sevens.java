// Jagteshwar 
/**
 * To make a bet via the user if the sum of the dices rolled is High, Low or Seven.
 * @author Jagteshwar Singh
 * @version 03222021
 *
 */
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HiLowSevens {

    /**
     * Prompts the user for an amount to bet. Ensures that the amount will be
     * between 0 and the maximum number of dollars they have available.
     *
     * @param in
     *            A Scanner to provide input
     * @param maxDollars
     *            the maximum number of dollars available
     * @return the amount to bet, guaranteed to be between 0 and maxDollars
     */
    public static int promptForAmount(Scanner in, int maxDollars) {
        int bet;
        // asks user for the bet amount
        System.out.print("Enter an amount to bet (0 to quit): ");
        bet = in.nextInt();
        in.nextLine();
        
        // Exception if the user enters a bet amount less than the max dollars or if the user enters a negative number
        while ((bet > maxDollars) || (bet < 0)){
           System.out.println("Your bet must be between 0 and " + maxDollars + " dollars.");
           System.out.println("You have " + maxDollars + " dollars.");
           System.out.print("Enter an amount to bet (0 to quit): ");
           bet = in.nextInt();
           in.nextLine();
        }
        
        // returns bet amount
        return bet;
    }

    /**
     * Prompts the user to enter a single character and ensures that the user
     * must input either an 'H', an 'L' or an 'S'. If they enter a correct
     * character in lowercase, converts it to uppercase.
     *
     * @param in
     *            A Scanner to provide input
     * @return a choice of 'H', 'L' or 'S' guaranteed to be in uppercase.
     */
    public static char promptForChoice(Scanner in) {
       // asks user his/her guess for the total. Accepts a string and converts it to Upper Case.
        System.out.print("High, low or sevens (H/L/S)?: ");
        String ch = in.nextLine();
        String chUpCase = ch.toUpperCase();
        
        // Exception if the user enters a character besides H, L or S.
        while ((!chUpCase.equals("H")) && (!chUpCase.equals("L")) && (!chUpCase.equals("S"))){
           System.out.println("You must enter only H, L or S.");
           System.out.print("High, low or sevens (H/L/S)?: ");
           ch = in.nextLine();
           chUpCase = ch.toUpperCase();
        }
        // converts String to a char type
        char finalCh = chUpCase.charAt(0);
        // returns the final character
        return finalCh;
    }

    /**
     * Returns an array of length numDice where each entry holds a random value
     * between 1 and 6 (representing the roll of a single die).
     *
     * @param rnd
     *            A Random number generator to use
     * @param numDice
     *            number of dice to roll
     * @return an array containing numDice values between 1 and 6
     */
    public static int[] rollDice(Random rnd, int numDice) {
       // Creates an empty array with elements equal to the number of dices.
        int[] roll = new int[numDice];
        // Fills the array with random number between 1 & 6.
        for (int i = 0; i < numDice; i++){
           roll[i] = rnd.nextInt(6)+1;
        }
        // Returns the array.
        return roll;
    }

    /**
     * Returns the sum of all of the values in the array dice. Note that this
     * method should be able to be called with an arbitrary number of dice so do
     * not hardcode it to only work with 2 dice.
     *
     * @param dice
     *            the values to be displayed
     * @return the sum of the values in the array dice
     */
    public static int totalDice(int[] dice) {
        int sum = 0;
        // A loop to calculate the total outcome of the number of dices rolled.
        for (int i = 0; i < dice.length; i++){
           sum = sum + dice[i];
        }
        // Returns total of the outcome.
        return sum;
    }

    /**
     * Returns the amount won or lost according to the choice made, the total on
     * the dice and the bet. If the choice is 'S' and the total is 7 this is 4
     * times the bet. If the choice is 'H' and the total is >=8 this is the same
     * as the bet. If the choice is 'L' and the total is <=6 - it is also the
     * same as the bet Otherwise, the player has lost and this function returns
     * the negative value of their bet.
     *
     * @param choice
     *            One of 'H', 'L', or 'S' - must be uppercase
     * @param total
     *            the total of the dice rolled
     * @param bet
     *            the dollar amount that has been bet
     * @return the correct amount won or lost according to the rules of the
     *         game.
     */
    public static int determineWinnings(char choice, int total, int bet) {
       int won = 0;
       // if-else block for all winning situations
        if ((choice == 'S') && (total == 7)){
           won = bet*4;
        }
        else if ((choice == 'H') && (total >= 8)){
           won = bet;
        }
        else if ((choice == 'L') && (total <= 6)){
           won = bet;
        }
        else { // Exception for any losses.
           won = -bet;
        }
           
           //Returns the result of the bet.
        return won;
    }

    public static void main(String[] args) {
        int maxDollars = 100; // Initiates the default number of dollars available to the user
        
        // Makes a Scanner object and asks for a random seed from the user for the random object
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a random seed: ");
        int seed = input.nextInt();
        
        // Makes a Random object 
        Random rnd = new Random(seed);
        input.nextLine();
        System.out.println("You have 100 dollars.");
        
        // calls promptForAmount method to get the bet amount from the user.
        int bet = promptForAmount(input, maxDollars);
        
        // A loop to repeat until user's bet equals 0
        while (bet != 0){
           
        // Calls promptForChoice method to ask for user's bet on the total of the dices.
        char choice = promptForChoice(input);
        int[] dice = rollDice(rnd, 2);
        
        // Prints the values on the dices and their total
        System.out.println("Your dice are showing: " + Arrays.toString(dice));
        int total = totalDice(dice);
        System.out.println("Your total is: " + total);
        
        // Calls the determineWinnings method to determine if the user won or lost the bet
        int won = determineWinnings(choice, total, bet);
        if (won > 0){
           System.out.println("You won " + won + " dollars!");
        }
        else { 
           System.out.println("You lost your bet!");
        }
        System.out.println();
        maxDollars = maxDollars + won;// Updates the amount of dollars the user has
        
        // asks the user again for the bet amount
        System.out.println("You have " + maxDollars + " dollars.");
        bet = promptForAmount(input, maxDollars);
        }
        System.out.println();
        
        // Statements to conclude the program.
        System.out.println("You ended the game with " + maxDollars + " dollars left.");
        System.out.println("Goodbye!");
    }

}