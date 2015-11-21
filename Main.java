//package writingTester;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main{

	public static void main(String[] args) throws IOException{
		AccountRepository repo = new AccountRepository();
		
		String loadOption = "1";
		String saveOption = "2";
		String viewOption = "3";
		String addAccount = "4";
		String deleteAccount = "5";
		String exitOption = "6";
		
		String userOption = "";
		Scanner userInput = new Scanner(System.in);
		boolean isValid = false;
		int timesPrompted = 0;
		do {
			isValid = false;
			while (!isValid) {
				userOption = null;
				if (timesPrompted > 0) System.out.println("Incorrect option");
				System.out.println("Please enter an option\n" + loadOption + " = Load database\n"
																+ saveOption + " = Save database\n" 
																+ viewOption +" = View Database\n"
																+ addAccount + " = Add Account\n" 
																+ deleteAccount +" = Delete Account\n"
																+ exitOption + " = Exit");
				userOption = userInput.next();
				System.out.println(userOption);
				if (userOption.equals(loadOption)
						|| userOption.equals(saveOption)
						|| userOption.equals(viewOption)
						|| userOption.equals(exitOption)
						|| userOption.equals(addAccount)
						|| userOption.equals(deleteAccount)) isValid = true;
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
				System.out.println("Please enter the name of the Account to delete:");
				Scanner AccountScanner = new Scanner(System.in);
				String AccountInput = null;
				while (AccountInput != null) {
					System.out.println("Please enter an account name: ");
					AccountInput = AccountScanner.next();
				}

			}
		} while (!(userOption.equals(exitOption)));
		
		
/*		Account Chairman_Mao = new Account("Chairman_mao", "china");
		Account Vladimir_Putin = new Account("Vladimir_Putin", "russia");
		Account Bashful = new Account("Bashful", "dwarf");
		
		Chairman_Mao.setProfileText("blahblahblah");
		Chairman_Mao.setProfilePhotoPath("images/suffering.png");
		Chairman_Mao.printAccount();
		Twit maoTwit = new Twit("Labor camps rule");
		Chairman_Mao.postTwit(maoTwit);
		Chairman_Mao.subscribe(Vladimir_Putin);
		Chairman_Mao.subscribe(Bashful);
		Vladimir_Putin.subscribe(Chairman_Mao);
		Chairman_Mao.printAccount();
		Chairman_Mao.unSubscribe(Bashful);
		Chairman_Mao.printAccount();
		
		String loadMao = Chairman_Mao.toString();
		repo.loadAccount(loadMao);
		repo.printAccounts();
		repo.saveDatabase();*/
		
		/*repo.loadDatabase();
		repo.printAccounts();*/

	}

}
