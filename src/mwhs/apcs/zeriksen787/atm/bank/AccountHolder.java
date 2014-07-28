package mwhs.apcs.zeriksen787.atm.bank;

public class AccountHolder implements Comparable<AccountHolder>{
	//Instance fields
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String email;
	private char gender;


	//Constructors

	//Default constructor
	public AccountHolder(){
		firstName = "Zach";
		lastName = "Eriksen";
		phoneNumber = 40323123;
		email = "example@email.com";
		gender = 'M';
	}

	//Non-Default constructor
	public AccountHolder(String newFirstName, String newLastName, long newPhoneNumber){
		firstName = newFirstName;
		lastName = newLastName;
		phoneNumber = newPhoneNumber;
		email = "example@email.com";
		gender = 'n';

	}

	//Getters
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public char getGender() {
		return gender;
	}

	//Setters
	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}
	public void setLastName(String newLastName) {
		lastName = newLastName;
	}
	public void setPhoneNumber(long newPhoneNumber) {
		phoneNumber = newPhoneNumber;
	}
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	public void setGender(char newGender) {
		gender = newGender;
	}

	public String getName(){
		return lastName + ", " + firstName;
	}

	public String toString(){
		String summary = firstName + "," + lastName + "," + phoneNumber;
		return summary;

	}

	@Override
	public int compareTo(AccountHolder ah) {
		return lastName.compareTo(ah.getLastName());
	}
}
