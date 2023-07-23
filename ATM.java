package BankingApplications;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Bank theBank = new Bank ("Bank of Ayra");
		
		//add a user , which also creates a savings acoount 
		User aUser = theBank.addUser("John", "Doe", "1234");
		
		//add a user, and create a checking account 
		Account newAccount = new Account ("Checking", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		//create a login prompt
		User newUser;
		while (true) {
			//loop till stopped
			newUser = ATM.mainMenuPrompt(theBank, scan);
			
			ATM.printUserMenu(newUser, scan);
			
		}
	}
	
	public static User mainMenuPrompt(Bank theBank, Scanner scan) {
		String userID;
		String pin;
		User authUser;
		
		//prompt user for user Id/pin combo until its correct
		do {
			System.out.printf("\nWelcome to %s\n", theBank.getName());
			System.out.println("Enter User ID: ");
			userID = scan.nextLine();
			System.out.print("Enter pin: ");
			pin = scan.nextLine();
			
			//make sure pin and ID match up
			authUser = theBank.userLogin(userID, pin);
			if (authUser == null) {
				System.out.println("Incorrect user ID/pin combination. Please try again");		
			} 
			
		} while(authUser == null); //continue looping until successfull login
		
		return authUser;
		
	}
	
	public static void printUserMenu (User theUser, Scanner scan) {
		
		//print a summary of the user's accounts 
		theUser.printAccountsSummary();
		
		int choice;
		
		//user menu
		do {
			System.out.printf("Welcome %s, what would you like to do? ", theUser.getFirstName());
			System.out.println(" 1. Show account history\n2. Withdraw\n3.Deposit\n4.Transfer\n5.Quit");
			System.out.println("\n\n\tEnter choice");
			choice = scan.nextInt();
			
			if (choice < 1 || choice > 5) {
				System.out.println("Error. Invalid choice. Please choose between 1 - 5.");
			}
		} while (choice < 1 || choice > 5);
		
		//process the choice 
		switch (choice) {
		
		case 1: 
				ATM.showTransHistory(theUser, scan);
				break;
		case 2: 
				ATM.withdrawalFunds(theUser, scan);
				break;
		case 3: 
				ATM.depositFunds(theUser, scan);
				break;
		case 4:
				ATM.transferFunds(theUser, scan);
				break;
		}
		
		//redisplay this menu unless user wants to quit
		if (choice != 5) {
			ATM.printUserMenu(theUser, scan); // using recursion
		}
}
	
	public static void showTransHistory(User theUser, Scanner scan) {
		int theAcct;
		//get specific account to look at transaction history 
		do {
			System.out.printf("Enter the number (1-%d) of the account\n" + 
							"whose transaction you want to see: ", theUser.numAccounts());
			theAcct = scan.nextInt()-1;
			if ( theAcct < 0 || theAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (theAcct < 0 || theAcct >= theUser.numAccounts());
		
		//print the transaction history 
		theUser.printAcctTranHistory(theAcct);
	}
	
	public static void transferFunds (User theUser, Scanner scan) {
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		//get the account to to transfer from 
		do {
			System.out.println("Enter the number (1- %d) of the account\n" + "to transfer from: ");
			fromAcct = scan.nextInt()-1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.print("\nInvalid Account. Try again");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);
		
		//get account to transfer to 
		do {
			System.out.println("Enter the number (1- %d) of the account\n" + "to transfer to: ");
			toAcct = scan.nextInt()-1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.print("\nInvalid Account. Try again");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(toAcct);
		
		//get the amount to transfer 
		do {
			System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
			amount = scan.nextDouble();
			if (amount < 0 ) {
				System.out.println("Amount must be greater than zero");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be grater than \n" + "balance of $%0.2f\n", acctBal);
				
			}
		} while (amount < 0 || amount > acctBal);
		
		//do the transfer
		theUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account $s", theUser.getAcctUUID(toAcct)));
		
		theUser.addAcctTransaction(toAcct, amount, String.format("Transfer to account %s", theUser.getAcctUUID(fromAcct)));
		
	}
	//process a fund withdraw from the account 
	public static void withdrawalFunds (User theUser, Scanner scan) {
		int fromAcct;
		double amount;
		double acctBal;
		String memo;
		
		
		//get the account to to transfer from 
		do {
			System.out.println("Enter the number (1- %d) of the account\n" + "to transfer from: ");
			fromAcct = scan.nextInt()-1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.print("\nInvalid Account. Try again");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);
	
	//get the amount to transfer 
			do {
				System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
				amount = scan.nextDouble();
				if (amount < 0 ) {
					System.out.println("Amount must be greater than zero");
				} else if (amount > acctBal) {
					System.out.printf("Amount must not be grater than \n" + "balance of $%0.2f\n", acctBal);
					
				}
			} while (amount < 0 || amount > acctBal);
	
	scan.nextLine();
	
	//get a memo
	System.out.println("Enter a memo");
	memo = scan.nextLine();
	theUser.addAcctTransaction(fromAcct, -1*amount, memo);
}
	//deposit funds 
	public static void depositFunds(User theUser, Scanner scan) {
		int toAcct;
		double amount;
		double acctBal;
		String memo;
		
		
		//get the account to to transfer to 
		do {
			System.out.println("Enter the number (1- %d) of the account\n" + "to transfer from: ");
			toAcct = scan.nextInt()-1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.print("\nInvalid Account. Try again");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(toAcct);
	
	//get the amount to transfer 
			do {
				System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
				amount = scan.nextDouble();
				if (amount < 0 ) {
					System.out.println("Amount must be greater than zero");
				} else if (amount > acctBal) {
					System.out.printf("Amount must not be grater than \n" + "balance of $%0.2f\n", acctBal);
					
				}
			} while (amount < 0 || amount > acctBal);
	
	scan.nextLine();
	
	//get a memo
	System.out.println("Enter a memo");
	memo = scan.nextLine();
	theUser.addAcctTransaction(toAcct, amount, memo);
}

}
