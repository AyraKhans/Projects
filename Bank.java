package BankingApplications;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
	
	private String name;
	
	private ArrayList<User> users;
	
	private ArrayList<Account> accounts;
	
	//bank constructor
	public Bank (String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
		
	}
	
	//generate a random new user id and make sure it doesn't already exist in the ArrayList<User>
	public String getNewUserUUID() {
		
		String uuid;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique; 
		
		//continue looping until we get a unique ID
		do {
			//generate the number 
			uuid = "";
			for (int i = 0; i < len; i++) {
				uuid += ((Integer)rng.nextInt(10)).toString(); //generates random numbers between 0 and 9 and appends them to the 'uuid' string
			}
			nonUnique = false;
			for (User u: this.users) {
				if (uuid.compareTo(u.getUUID()) == 0){
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		
		return uuid;
	}
	//generate a random new account id and make sure it doesn't already exist in the ArrayList<Accounts>
	
	public String getNewAccountUUID () {
		
		String uuid;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique; 
		
		//continue looping until we get a unique ID
		do {
			//generate the number 
			uuid = "";
			for (int i = 0; i < len; i++) {
				uuid += ((Integer)rng.nextInt(10)).toString(); //generates random numbers between 0 and 9 and appends them to the 'uuid' string
			}
			nonUnique = false;
			for (Account a: this.accounts) {
				if (uuid.compareTo(a.getUUID()) == 0){
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		
		return uuid;
		
	}
	//adds an account for the user 
	public void addAccount (Account anAcct) {
		this.accounts.add(anAcct);
	}

	//create a new user 
	public User addUser(String firstName, String lastName, String pin) {
		
		//create a new User Object and add it to our list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		//create a saving account 
		Account newAccount = new Account ("Savings", newUser, this);
		
		//add to user and bank lists 
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	// Get the user objects associated with a particular userID and pin, if they are valid
	public User userLogin (String userID, String pin) {
		// search through list of user to find the correct id 
		for (User u : this.users) {
			
			//check if user ID is correct
			if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
				
			}
		} //if we haven't found user or have an incorrect pin 
		return null;
		
	}
	
	//get name of bank
	public String getName() {
		return this.name;
		
	}
	
}
