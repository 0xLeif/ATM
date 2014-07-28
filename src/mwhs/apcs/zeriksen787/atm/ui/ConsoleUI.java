package mwhs.apcs.zeriksen787.atm.ui;

import java.util.Scanner;
import javax.swing.JOptionPane;

import mwhs.apcs.zeriksen787.atm.bank.AccountHolder;
import mwhs.apcs.zeriksen787.atm.bank.CheckingAccount;
import mwhs.apcs.zeriksen787.atm.bank.DataFile;

public class ConsoleUI {
	private static CheckingAccount[] accounts;
	private static CheckingAccount account;
	
	public static void main(String[] args) {
		// Read all bank data from file
		accounts = DataFile.read("data/data.csv");
		//Start the program
		open();
	}
	
	
	/**
	 * starts the program and asks two options what to do
	 * 1 log in lets the user log in to their account
	 * 2 create account lets the user create their account
	 */
	private static void open() {
		Scanner in = new Scanner(System.in);
		
		while(true){
			System.out.println("1\tLog in");
			System.out.print("2\tCreate an Account");
			System.out.println("\n");
			System.out.println("\n");
			System.out.print("Enter the number for your chosen option: ");
			String o = in.nextLine();
			int option = Integer.parseInt(o);
			System.out.println(option);
			if(option == 1){
				logIn();
				break;
			}else if(option == 2){
				createAccount();
				break;
			}else{
				System.out.println("\nNot a valid option, please try again...");
			}
		}
	}


	/**
	 * Where the user enters their account number and pin
	 */
	public static void logIn(){
		Scanner in = new Scanner(System.in);
		JOptionPane.showMessageDialog(null, "Welcome to Piggy Bank!", "ATM", JOptionPane.PLAIN_MESSAGE);
		System.out.println("Welcome to the Piggy Bank ATM!");
		System.out.print("Please enter your account number: ");
		String acc = in.nextLine();
		long ac = Integer.parseInt(acc);
		System.out.print("Please enter your PIN: ");
		String num = in.nextLine();
		int pin = Integer.parseInt(num);

		check(pin, ac);
	}
	
	/**
	 * Checks if the account number and pin are correct
	 * @param pin Pin number
	 * @param ac AccountNumber
	 */
	public static void check(int pin, long ac){
		Scanner in = new Scanner(System.in);
		findAccount(ac,pin);
	if(account != null){
		mainMenu();
	}else{
		while(true){
			if(account == null){
				System.out.println("We could not find your account, " +
						"most likely because your account number or PIN " +
						"was entered incorrectly.\n Please re-enter your account information.");
				System.out.println("\n");
				System.out.print("Please enter your account number: ");
				String acc = in.nextLine();
				long ac2 = Integer.parseInt(acc);
				System.out.print("Please enter your PIN: ");
				String num = in.nextLine();
				int pin2 = Integer.parseInt(num);
				findAccount(ac2,pin2);
					if(account != null){
						mainMenu();
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Checks if the account is a real account
	 * @param acctNum checks if this account number is valid 
	 * @param pin checks if this pin matches the valid account number
	 * @return the active account
	 */
	private static CheckingAccount findAccount(long acctNum, int pin){
		int i = 0;
			for(CheckingAccount a : accounts){
				if (a.getAccountNumber() == acctNum && a.getPin() == pin){
					account = accounts[i];
				}else{
					account = null;
					i++;
				}
			}
			return account;
	}
	
	
	/**
	 * Is the Main menu where the user has 4 options
	 */
	public static void mainMenu(){
		Scanner in = new Scanner(System.in);
		
		System.out.println("\n");
		System.out.println("\t\t MAIN MENU");
		System.out.println("Please choose from the following options: ");
		System.out.println("\n");
		System.out.println("1\tDeposit money");
		System.out.println("2\tWithdraw money");
		System.out.println("3\tView account summary");
		System.out.println("4\tExit");
		System.out.println("\n");
		System.out.print("Enter the number for your chosen option: ");
		String o = in.nextLine();
		int option = Integer.parseInt(o);
		System.out.println(option);
		
		if(option == 1){
			deposit();
		}else if(option == 2){
			withdraw();
		}else if(option == 3){
			accountSum();
		}else{
			exit(accounts);
		}
	}
	
	/**
	 * Asks for all the info to create the new user an account
	 * with their own pin and a random account number
	 */
	private static void createAccount() {
		Scanner in = new Scanner(System.in);
		
		long rN = CheckingAccount.getRandomAccountNumber();
		
		System.out.print("Please enter your first name: ");
		String fN = in.nextLine();
		System.out.println("\n");
		System.out.print("Please enter your last name: ");
		String lN = in.nextLine();
		System.out.println("\n");
		System.out.print("Please enter your phone number: ");
		String num = in.nextLine();
		long pN = Long.parseLong(num);
		System.out.println("\n");
		System.out.print("Please enter your pin: ");
		String p = in.nextLine();
		int pin = Integer.parseInt(p);
		System.out.println("\n");
		System.out.println("\n");
		
		CheckingAccount a = new CheckingAccount(new AccountHolder(fN,lN,pN),rN,pin,0);
		System.out.println("Your account number is: " + rN + ". And your pin is: " + pin);
		System.out.print("If this is correct please press 1, or press 2 if it is not: "); 
		String c = in.nextLine();
		int option = Integer.parseInt(c);
		
		if(option == 1){
			 CheckingAccount[] temp = new CheckingAccount[accounts.length + 1];
             for(int i = 0; i < accounts.length; i++){
                 temp[i] = accounts[i]; 
             }
         temp[temp.length - 1] = a;
         accounts = temp;
         
         DataFile.write(accounts);
         logIn();
         
		}else{
			createAccount();
		}
		
	}

	/**
	 * Will take the user back to the login screen/text
	 * and will also save all the changes made
	 */
	private static void exit(CheckingAccount[] account) {
		DataFile.write(accounts);
		System.out.println("Thank you for using Piggy Bank ATM!");
		System.out.println("\n");
		open();
	}
	
	/**
	 * Will tell the user their balance
	 */
	private static void accountSum() {
		System.out.println(account.toString());
		mainMenu();
	}
	
	/**
	 * Will let the user withdraw a valid amount of money then return them to the main menu
	 */
	private static void withdraw() {
		Scanner in = new Scanner(System.in);
		
		while(true){
			System.out.print(" How much would you like to withdraw: ");
			String w = in.nextLine();
			double withdraw = Double.parseDouble(w);
			if(withdraw > 0 && withdraw <= account.getBalance()){
				account.withdrawal(withdraw);
				System.out.println("Thank your for your withdrawal. Your current \naccount balance is \n \n \t" + account.getBalance());
				mainMenu();
				return;
			}else{
				System.out.print("\nYou entered a invaild amount.\nAmounts withdrawn must be numeric,\ncontain no symbols, and be greater that\nzero.");
			}
		}
	}
	
	/**
	 * Will let the user deposit a valid amount of money then return them to the main menu
	 */
	public static void deposit(){
		Scanner in = new Scanner(System.in);
		
		while(true){
			System.out.print(" How much would you like to deposit: ");
			String d = in.nextLine();
			double deposit = Double.parseDouble(d);
			if(deposit > 0){
				account.deposit(deposit);
				System.out.println("Thank your for your deposit. Your current \naccount balance is \n \n \t" + account.getBalance());
				mainMenu();
				return;
			}else{
				System.out.print("\nYou entered a invaild amount.\nAmounts deposited must be numeric,\ncontain no symbols, and be greater that\nzero.");
			}
		}
	}
}
