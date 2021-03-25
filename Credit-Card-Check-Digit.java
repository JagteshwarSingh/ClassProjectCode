// Jagteshwar
/**
 * To check the validity of a credit card by performing Luhn's algorithm.
 *
 * @author Jagteshwar Singh
 * @version 02212021
 */
import java.util.Scanner;

public class LuhnAlgorithm {

    public static void main(String[] args) {
        // Initiate Scanner class and ask inout from user
        Scanner input = new Scanner (System.in); 
        int sum = 0;
        System.out.print("Enter a credit card number (enter a blank line to quit): ");
        String cardNum = input.nextLine();
        
        // Calculate credit card length and check for digits to perform operations
        int cardLength = cardNum.length();
           while ( cardLength != 0 ){
            if ( cardLength == 16 ){
               
               // Loop until the last element to extract digits and perform Luhn's algorithm
              for (int i = 0; i < 15; i++){
                 char c = cardNum.charAt(i);
                 int val = Character.getNumericValue(c);
                 if (i%2 == 0) {
                    val = val*2;
                    if (val > 9){
                       val = val - 9;
                    }
                 }
                 sum = sum + val;
              }
              if ((sum%10) > 0){
                 sum = 10 - (sum%10);
              }
              else {
                 sum = 0;
              }
              
              // Check if the credit card number entered is valid
              System.out.println("Check digit should be: " + (sum));
              System.out.println("Check digit is: " + Character.getNumericValue(cardNum.charAt(15)));
              if ( sum == Character.getNumericValue(cardNum.charAt(15))){
                 System.out.println("Number is valid.");
                 System.out.println();
              }
              else{
                 System.out.println("Number is not valid.");
                 System.out.println();
              }
            }
            
            // Exception if the credit card number isn't exactly 16 digits.
            else{
              System.out.println("ERROR! Number MUST have exactly 16 digits.");
              System.out.println();
            }
            
            // Ask user for another input
            System.out.print("Enter a credit card number (enter a blank line to quit): ");
            cardNum = input.nextLine();
            cardLength = cardNum.length();
            sum = 0;
             }
             
             // Exception if blank line is entered
          System.out.println("Goodbye!");
          
          // Close Scanner object
          input.close();
    }
           
              
              
}
