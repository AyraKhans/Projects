package BankingApplications;

import java.util.Date;

public class Transaction {

	private double amount;

	private Date timestamp; //Time and Date of the transaction
	
	private String memo; //A memo for the transaction
	
	private Account inAccount; //Account in which the transaction was performed
	
	public Transaction(double amount, Account inAccount) {
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = "";
	}
	
	public Transaction(double amount, String memo, Account inAccount) {
		
		//call on the 2 arg constructor
		this(amount, inAccount);
		
		this.memo = memo;
		
	}
	
	//get the amount of the transaction
	public double getAmount() {
		return this.amount;
	}
	
	//summarize transaction
	public String getSummaryLine() {
		if (this.amount >= 0) {
			return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
		} else {
			return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);

		}
	}
	
}
