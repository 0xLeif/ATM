package mwhs.apcs.zeriksen787.atm.bank;

import java.util.Random;

public class CheckingAccount {
	private long accountNumber;
	private int pin;
	private double balance;
	private AccountHolder accountHolder;
	
	private static final int NUM_DIGITS = 7;
	
	/**
	 * The constructor for this class
	 * @param newAccountHolder The account holder
	 */
	public CheckingAccount(AccountHolder newAccountHolder, long newAccountNumber, int newPin, double newBalance){
		accountNumber = newAccountNumber;
		accountHolder = newAccountHolder ;
		pin = newPin;
		balance = newBalance;
	}
	/**
	 * Adds a deposit to your balance
	 * @param amount Amount to deposit
	 * @return True upon success, false otherwise
	 */
	public void deposit(double amount){
			balance += amount;
	}
	/**
	 * Processes a withdrawal from the CheckingAccount 
	 * @param amount Amount to withdrawal
	 * @return True upon successful withdrawal, false otherwise
	 */
	public boolean withdrawal(double amount){
		if(amount > balance){ //Overdraft
			return false;
		}else if(amount > 0){ //Valid amount
			balance -= amount;
			return true;
		}else{				  //Negative amount
			return false;
		}
	}
	
	/**
	 * Processes the toString
	 */
	public String toString(){
		String summary = "Account Summary\n";
				summary += accountHolder.getName();
				summary += "\n";
				summary += "Balance: " + balance;
		return summary;
	}
	
	/**
	 * Generates a random 7-digit account number
	 * @return Random account number
	 */
	public static long getRandomAccountNumber(){
		Random r = new Random();
		String acctString = "";
		for(int i = 0; i < NUM_DIGITS; i++){
			int num;
			if(i == 0){
				num = r.nextInt(9) + 1;
			}else{
				num = r.nextInt(10);
			}
			acctString += num;
		}
		return Long.parseLong(acctString);
	}
	
	//Getters
	public long getAccountNumber(){
		return accountNumber;
	}
	public int getPin(){
		return pin;
	}
	public double getBalance(){
		return balance;
	}
	public AccountHolder getAccountHolder(){
		return accountHolder;
	}
	
	//Setters
	public void setAccountNumber(long newAccountNumber){
		accountNumber = newAccountNumber;
	}
	public void setAccountHolder(AccountHolder newAccountHolder){
		accountHolder = newAccountHolder;
	}
	public void setPin(int newPin){
		pin = newPin;
	}
	public void setBalance(double newBalance){
		balance = newBalance;
	}
}
