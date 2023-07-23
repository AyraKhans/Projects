package BankingApplications;

import java.util.ArrayList;

public class Account {

	private String name;
	private String uuid;
	
	private User holder; //The User object that owns this account
	
	
	private ArrayList<Transaction> transactions;
	
	//Account constructor
	public Account (String name, User holder, Bank theBank) {
		//set account name and holder 
		this.name = name;
		this.holder = holder;
		
		//get new Account UUID 
		this.uuid = theBank.getNewAccountUUID();
		
		//intialize transaction to an empty lists 
		this.transactions = new ArrayList<Transaction>();
		
		
		
	}
	//get the account ID
	public String getUUID() {
		return this.uuid;
	}
	
	//get Summary of Account 
	public String getSummaryLine() {
		//get accounts balance 
		double balance = this.getBalance();
		
		//format the summary line depending on the whether the balance is negative (the account is overdrawn)
		if (balance >= 0) {
			return String.format("%s : $%.02f: %s", this.uuid, balance, this.name);
		} 						//uuid //a floating number with 2 digits of precision after the decimal point //name
		else {
			return String.format("%s : $(%.02f)  : %s", this.uuid, balance, this.name);
		}
	}
	public double getBalance() {
		double balance = 0;
		for (Transaction t : this.transactions) { //looping through all the transactions and then adding up the amount 
			balance += t.getAmount();
		} 
		return balance;
	}
	public void printTransHistory() {
		System.out.printf("\nTransaction History for account $s\n", this.uuid);
		for (int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.printf(this.transactions.get(t).getSummaryLine());
		}
	System.out.println();
	}
	
	//add transaction
	public void addTransaction(double amount, String memo) {
		//create a new transaction obj + add to our list
		Transaction newTrans = new Transaction (amount, memo, this);
		this.transactions.add(newTrans);
		
	}
}
