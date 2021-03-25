//  Jagteshwar
/**
 * To find the best angle and speed to launch a trajectory for a specific distance entered by user.
 * 
 * @author Jagteshwar Singh
 * @version 02282021
 **/

import java.util.Scanner;
 
public class TrajectoryModeling {

	public static void main(String[] args) {
	   
	   // Intitializing the Scanner class 
      Scanner input = new Scanner (System.in); 
      
      // Extracting target distance from user
      System.out.print("Enter a distance to target: ");
      double dist = input.nextDouble();
      
      //Initializing variables
      double speed = 0;
      double angle = 0;
      double tempDist = 0;
      double bestSpeed = 0;
      double bestAngle = 0;
      double bestDist = 0;
      double diff = dist;
      
      // A loop to iterate through all possible speed values
      for ( speed = 1.0; speed <= 30.0; speed++) { 
         // A loop to iterate through all possible angles
         for ( angle = 25.0; angle <= 90.0; angle = angle + 5){ 
            
            // Performing Calculations
            double cosine  = Math.cos(Math.toRadians(angle)); 
            double sine = Math.sin(Math.toRadians(angle));
            tempDist = (2*Math.pow(speed,2)*cosine*sine)/9.8;
            
            
            if (Math.abs(dist - tempDist) < diff){ // Checks for the distance closest to the target distance
               diff = Math.abs(dist - tempDist);
               
               // Stores the best Distance, Speed and Angle.
               bestDist = tempDist;
               bestSpeed = speed;
               bestAngle = angle;
            }
         }
      }
      
      // Prints results
      System.out.println();
      System.out.printf("Best angle: %.2f%n", bestAngle);
      System.out.printf("Best speed: %.2f%n", bestSpeed);
      System.out.printf("Distance travelled: %.2f%n", bestDist);
      System.out.printf("Missed the target center by: %.2f%n", diff);
      input.close();
	}

}