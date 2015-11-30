package writingTester;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestMain {
	/*AccountRepository repo = new AccountRepository();
	
	String loadOption = "1";
	String saveOption = "2";
	String viewOption = "3";
	String addAccount = "4";
	String deleteAccount = "5";
	String signIn = "6";
	String signOut = "7";
	String exitOption = "8";
	
	String userOption = "";
	Scanner userInput = new Scanner(System.in);
	boolean isValid = false;
	int timesPrompted = 0;
	do {
		isValid = false;
		timesPrompted = 0;
		while (!isValid) {
			userOption = null;
			if (timesPrompted > 0) System.out.println("Incorrect option");
			if (repo.getSignedIn()) System.out.println("[You are signed in as " + repo.getActiveAccount().getUserName() + "]");
			System.out.println("Please enter an option\n"
				+ loadOption + " = Load database\n"
				+ saveOption + " = Save database\n" 
				+ viewOption +" = View Database\n"
				+ addAccount + " = Add Account\n" 
				+ deleteAccount +" = Delete Account\n"
				+ signIn +" = Sign In\n"
				+ signOut +" = Sign Out\n"
				+ exitOption + " = Exit");
			userOption = userInput.next();
			System.out.println(userOption);
			if (userOption.equals(loadOption)
					|| userOption.equals(saveOption)
					|| userOption.equals(viewOption)
					|| userOption.equals(exitOption)
					|| userOption.equals(addAccount)
					|| userOption.equals(deleteAccount)
					|| userOption.equals(signIn)
					|| userOption.equals(signOut))isValid = true;
			if (!isValid) ++timesPrompted;
		}
		if (userOption.equals(loadOption)) repo.loadDatabase();
		else if (userOption.equals(saveOption)) repo.saveDatabase();
		else if (userOption.equals(viewOption)) repo.printAccounts();
		else if (userOption.equals(addAccount)) {
			Account temp =  new Account("temp", "temp");
			Scanner AccountScanner = new Scanner(System.in);
			String AccountInput = "";
			boolean validInput = false;
			boolean emptyInput = false;
			boolean uniqueName = false;
			while (!validInput) {
				uniqueName = false;
				emptyInput = false;
				System.out.println("Please enter an account name: ");
				AccountInput = AccountScanner.next();
				if (AccountInput == "") emptyInput = true;
				uniqueName = repo.nameUnique(AccountInput);
				if (uniqueName && !emptyInput) validInput = true;
				if (!uniqueName) System.out.println("The account name '" + AccountInput + "' already exists, please choose another name");
			}
			temp.setUserName(AccountInput);
			AccountInput = "";
			validInput = false;
			while (!validInput) {
				emptyInput = false;
				System.out.println("Please enter an account password: ");
				AccountInput = AccountScanner.next();
				if (AccountInput == "") emptyInput = true;
				if (!emptyInput) validInput = true;
			}
			temp.setPassword(AccountInput);
			AccountInput = "";
			repo.addAccount(temp);
			System.out.println("Account created...");
		}
		else if (userOption.equals(deleteAccount)) {
			Scanner AccountScanner = new Scanner(System.in);
			String AccountInput = "";
			boolean validInput = false;
			boolean emptyInput = false;
			while (!validInput) {
				System.out.println("Please enter an account name: ");
				AccountInput = AccountScanner.next();
				if (AccountInput == "") emptyInput = true;
				if (!emptyInput) validInput = true;
			}
			boolean deleted = repo.removeAccount(AccountInput);
			if (deleted) System.out.println("Account deleted...");
			else System.out.println("Account could not be deleted...");
		}
		else if (userOption.equals(signIn)) {
			Scanner AccountScanner = new Scanner(System.in);
			String AccountInput = "";
			boolean validInput = false;
			boolean emptyInput = false;
			boolean uniqueName = false;
			while (!validInput) {
				System.out.println("Please enter an account name: ");
				AccountInput = AccountScanner.next();
				if (AccountInput == "") emptyInput = true;
				uniqueName = repo.nameUnique(AccountInput);
				if (!uniqueName && !emptyInput) validInput = true;
				if (uniqueName) System.out.println("The account name '" + AccountInput + "' does not exist, please choose another name");
			}
			String AccountName = AccountInput;
			AccountInput = "";
			validInput = false;
			boolean matchingPassword = false;
			while (!validInput) {
				emptyInput = false;
				System.out.println("Please enter an account password: ");
				AccountInput = AccountScanner.next();
				if (AccountInput == "") emptyInput = true;
				matchingPassword = repo.matchingPassword(AccountName, AccountInput);
				if (!emptyInput && matchingPassword) validInput = true;
				if (!matchingPassword) System.out.println("The password does not match for the specified user");
			}
			repo.SignIn(AccountName);
		}
		else if (userOption.equals(signOut)) {
			repo.SignOut();
			System.out.println("You have signed out...");
		}
	} while (!(userOption.equals(exitOption)));*/
}