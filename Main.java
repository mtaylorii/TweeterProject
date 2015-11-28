package writingTester;

import java.awt.Menu;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main{
	
	public static String printHeader(String s) {
		String header = "";
		String headerText = s;
		for (int i = 0; i < 100 - headerText.length(); ++i) {
			header += "*";
			if (i == (100 - headerText.length())/2) header += headerText;
		}
		return header;
	}

	public static void main(String[] args) throws IOException{
		AccountRepository repo = new AccountRepository();
		repo.loadEverything();
		
		//System.out.println("******* TWEETER ********");
		
		ASCII img = new ASCII();
		img.goTime("tweeter.png");
		
		String userOption = "";
		int convertedOption = -1;
		Scanner userInput = new Scanner(System.in);
		boolean isValid = false;
		int timesPrompted = 0;
		ArrayList<String> menuOptions = new ArrayList<String>();
		do {
			menuOptions.clear();
			String SignedOutMenu[] = new String[]{"Sign in", 
					"Register", "View public twits", "View profiles",
					"Search", "Exit"};
			String SignedInMenu[] = new String[]{"Sign out", 
					"View all twits", "View subscribed to twits",
					"View profiles", "View subscribers", "view subscribed to", "Post twit", "Subscribe", "Unsubscribe", "Search",
					"Edit profile", "Exit"};
			if (repo.getSignedIn()) for (int i = 0; i < SignedInMenu.length; ++i) menuOptions.add(SignedInMenu[i]);
			else for (int i = 0; i < SignedOutMenu.length; ++i) menuOptions.add(SignedOutMenu[i]);
			isValid = false;
			timesPrompted = 0;
			userOption = "";
			convertedOption = -1;
			while (!isValid) {
				userOption = "";
				convertedOption = -1;
				if (timesPrompted > 0) System.out.println(printHeader("Incorrect option"));
				String header = "";
				String headerText = " Please enter an option: ";
				for (int i = 0; i < 100 - headerText.length(); ++i) {
					header += "*";
					if (i == (100 - headerText.length())/2) header += headerText;
				}
				System.out.println(printHeader(" Please enter an option: "));
				for (int i = 0; i < menuOptions.size(); ++i) System.out.println(i + " " + menuOptions.get(i));
				System.out.print(">>> ");
				userOption = userInput.next();
				try {
					convertedOption = Integer.parseInt(userOption);
					if (convertedOption <= menuOptions.size()-1 && convertedOption > -1 ) isValid = true;
				}
				catch (NumberFormatException e) {
					System.out.println(printHeader("Please enter a valid integer"));
				}
				if (!isValid) ++timesPrompted;
			}
			if (!(repo.getSignedIn())) {
				if (convertedOption == 0) {
					img.goTime("signin.png");
					repo.signIn();
				}
				else if (convertedOption == 1) {
					img.goTime("register.png");
					repo.register();
					repo.saveEverything();
				}
				else if (convertedOption == 2) {
					img.goTime("publictwits.png");
					repo.viewPublicTwits();
				}
				else if (convertedOption == 3) {
					img.goTime("profiles.png");
					repo.printAccountProfiles();
				}
				else if (convertedOption == 4) {
					img.goTime("search.png");
					repo.search();
				}
			}
			else {
				if (convertedOption == 0) {
					img.goTime("signout.png");
					repo.signOut();
				}
				else if (convertedOption == 1) {
					img.goTime("alltwits.png");
					repo.viewAllTwits();
				}
				else if (convertedOption == 2) {
					img.goTime("subscribertwits.png");
					repo.viewSubscribedToTwits();
				}
				else if (convertedOption == 3) {
					img.goTime("profiles.png");
					repo.printAccountProfiles();
				}
				else if (convertedOption == 4) {
					img.goTime("subscribers.png");
					repo.viewSubscribers();
				}
				else if (convertedOption == 5) {
					img.goTime("subscribedto.png");
					repo.viewSubscribedTo();
				}
				else if (convertedOption == 6) {
					img.goTime("posttwit.png");
					repo.getActiveAccount().postTwit();
					repo.saveEverything();
				}
				else if (convertedOption == 7) {
					img.goTime("subscribe.png");
					repo.subscribe();
					repo.saveEverything();
				}
				else if (convertedOption == 8) {
					img.goTime("unsubscribe.png");
					repo.unsubscribe();
					repo.saveEverything();
				}
				else if (convertedOption == 9) {
					img.goTime("search.png");
					repo.search();
				}
				else if (convertedOption == 10) {
					img.goTime("edit.png");
					repo.getActiveAccount().editProfile();
					repo.saveEverything();
				}
			}
			if (convertedOption == menuOptions.size()-1) repo.saveEverything();
		} while (!(convertedOption == menuOptions.size()-1));
		img.goTime("exit.png");
		
		
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
}