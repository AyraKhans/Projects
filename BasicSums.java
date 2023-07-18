import java.util.Scanner;

// Basic Intro/ Refresher for Sums using User Input
public class BasicSums {
	
	public static void main(String[] args) {
		
		// User input 
		Scanner input = new Scanner (System.in);
		System.out.println("Enter the first number: ");
		//Using Float to consider possibility of decimal values 
		float first = input.nextFloat();
		System.out.println("Enter the second number: ");
		float second = input.nextFloat();	
		System.out.println("Your answer is: " + Float.sum(first, second));	
		
		
		}
}
