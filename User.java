package BankingApplications;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
	private String firstName;
	private String lastName;
	private String uuid;
	
	// By using a byte array, the developer can store the PIN in an encoded format rather than its actual value. 
	// Using a hashed representation of the PIN also allows for comparison without exposing the original PIN. 
	// Whenever a user enters their PIN, it can be converted to a hash value and compared with the stored hash to verify correctness.
	private byte pinHash[]; 
	
	private ArrayList<Account> accounts;
	
	//Create a Constructor Class 
	public User(String firstName, String lastName, String pin, Bank theBank) {
		//set user's name
		this.firstName = firstName;
		this.lastName = lastName;
		
		//store the pin's MD5 hash, rather than the original value for security purposes 
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes()); //taking the Pin from the constructor and passing it through the digest algo and that returns a dif. array of bytes that we're going to store in pinHash
			
		} catch (NoSuchAlgorithmException e) {
			System.err.print("Error. NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		//get a new UUID for the user 
		this.uuid = theBank.getNewUserUUID();
		
		//create an empty list of accounts 
		this.accounts = new ArrayList<Account> ();
		
		//print message //%s is a placeholder character 
		System.out.printf("New user %s, %s with ID %s created \n", lastName, firstName, this.uuid);
			
	}
	//adds an account for the user 
	public void addAccount (Account anAcct) {
		this.accounts.add(anAcct);
	}
	//getter for uuid
	public String getUUID() {
		return this.uuid;
	}
	// check whether a given pin matches the true User Pin
	public boolean validatePin(String oPin) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(oPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.print("Error. NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	//get first name 
	public String getFirstName() {
		return this.firstName;
		
	}
	//print all accounts and balances for account 
	public void printAccountsSummary() {
		System.out.printf("\n%sUser Accounts Summary: ", this.firstName);
		for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a + 1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}
	
	//return account size
	public int numAccounts () {
		return this.accounts.size();
		
	}
	// print transaction history 
	public void printAcctTranHistory(int accIdx) {
		this.accounts.get(accIdx).printTransHistory();
	}
	
	public double getAcctBalance(int acctIndx) {
		return this.accounts.get(acctIndx).getBalance();
	}
	//get the UUID of a particular account 
	public String getAcctUUID(int acctIndx) {
		return this.accounts.get(acctIndx).getUUID();
		
	}
	
	public void addAcctTransaction(int acctIndx, double amount, String memo) {
		this.accounts.get(acctIndx).addTransaction(amount,memo);
	}
}

