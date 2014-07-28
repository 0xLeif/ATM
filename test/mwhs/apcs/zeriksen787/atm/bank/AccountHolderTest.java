package mwhs.apcs.zeriksen787.atm.bank;

public class AccountHolderTest {
	public static void main(String[] args) {
		System.out.println("Testing the AccountHolder class...");
		
		//Create an AccountHolder object
		AccountHolder a;
		a = new AccountHolder();
		
		//Test getFirstName
		String fn = a.getFirstName();
		System.out.println(fn);
		a.setFirstName("Zachariah");
		System.out.println("Setting...");
		System.out.println("New:	" + a.getFirstName());
		System.out.println("Done");

		//Test getLastName
		String ln = a.getLastName();
		System.out.println(ln);
		a.setLastName("Erik");
		System.out.println("Setting...");
		System.out.println("New:	" + a.getLastName());
		System.out.println("Done");
		
		//Test getGender
		char g = a.getGender();
		System.out.println(g);
		a.setGender('F');
		System.out.println("Setting...");
		System.out.println("New:	" + a.getGender());
		System.out.println("Done");
		
		System.out.println(a);
		
		//Test the Non-Default constructor
		AccountHolder b = new AccountHolder("Zach","Eriksen",4029787576L);
		System.out.println(b);
	}

}
