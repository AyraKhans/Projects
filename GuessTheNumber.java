package GuessingGame;

import java.util.Random;
import java.util.Scanner;

//Description of the game is Computer will generate a random number from 1 to 100, and the user must guess the number. 
//The computer will inform the user whether the number is too high, too low, or correct. The user will have a set number of guesses. 
public class GuessTheNumber {

	public static void main(String[] args) {
		// Generate random number
		Random rand = new Random();
		int number = rand.nextInt(100) + 1; // 1 to 100
		
		
		System.out.println("I've generated a number from 1 to 100. "
							+ "\nCan you guess what number it is? Note: (You have 5 tries) ");
		
		//number of guesses 
			int K = 5;
			int i;
			for (i = 0; i < K; i++) {
					
			Scanner scan = new Scanner(System.in);
			System.out.println("\n\nYour guess: ");
			int playersguess = scan.nextInt();
		
			if (playersguess == number ) {
				System.out.println("Congrats! You got it");
				break;
			}
			else if (number > playersguess && i != K-1 ) {
				System.out.println("The number is greater than " + playersguess);
			}
			else if (number < playersguess && i != K-1) {
				System.out.println("The number is less than " + playersguess);
			}
		}
		if (i == K) {
			System.out.println("You're out of tries.");
		}
		System.out.println("The number I generated was: " + number);
		

	}

}
