package writingTester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class contains the main method for the program; it allows a user to
 * interact with the Tweeter social media program by selecting options from a
 * main menu and a series of submenus.
 */
public class Main{
	/**
         * Method to take a String's text and turn it into a header to be printed that is stored in another String.
         * @param s the String to be converted into a header and stored in a new String
         * @return the String containing the parameter String's text as a header
         */
	public static String printHeader(String s) {
		String header = "";
		String headerText = s;
		for (int i = 0; i < 100 - headerText.length(); ++i) {
			header += "*";
			if (i == (100 - headerText.length())/2) header += headerText;
		}
		return header;
	}
	/**
         * The main method for this program, where methods from and objects of other
         * classes in the program are called and created, respectively; this is also
         * where the user interacts with the menu and submenus.
         * @param args
         * @throws IOException 
         */
	public static void main(String[] args) throws IOException{
		AccountRepository repo = new AccountRepository(); // AccounRepository object is instantiated
		repo.loadEverything(); // calls the method to load all accounts and twits stored in the databases automatically when the program opens
		
		ASCII img = new ASCII(); // ASCII object is instantiated
		img.goTime("tweeter.png"); // calls method to print an ASCII image of the "tweeter.png" file
		
		String userOption = ""; // creates userOption String and sets it to ""
		int convertedOption = -1; // creates convertedOption int and sets it to -1
		Scanner userInput = new Scanner(System.in); // Scanner for user input
		boolean isValid = false; // creates isValid boolean and sets it to false
		int timesPrompted = 0; // creates timesPrompted int and sets it to 0
		ArrayList<String> menuOptions = new ArrayList<String>(); // creates an ArrayList of String objects called menuOptions
		// do statement that takes the user through the program's menus and funcitons
		do {
			menuOptions.clear();
			// array of Strings to represent the different menu options for a user that isn't signed in
			String SignedOutMenu[] = new String[]{"Sign in", 
					"Register", "View public twits", "View profiles",
					"Search", "Exit"};
			// array of Strings to represent the different menu options for a user that is signed in
			String SignedInMenu[] = new String[]{"Sign out", 
					"View all twits", "View subscribed to twits",
					"View profiles", "View subscribers", "view subscribed to", "Post twit", "Subscribe", "Unsubscribe", "Search",
					"Edit profile", "Exit"};
			// adds Strings representing menu options for a user that is signed in to the menuOptions ArrayList
			if (repo.getSignedIn()) for (int i = 0; i < SignedInMenu.length; ++i) menuOptions.add(SignedInMenu[i]);
			// adds Strings representing menu options for a user that is not signed in to the menuOptions ArrayList
			else for (int i = 0; i < SignedOutMenu.length; ++i) menuOptions.add(SignedOutMenu[i]);
			isValid = false;
			timesPrompted = 0;
			userOption = "";
			convertedOption = -1;
			// while loop that allows the user to choose which menu option they want
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
			// if statement that executes the code within if a user is not signed in
			if (!(repo.getSignedIn())) {
                                // if a user chooses to sign in, the system executes the signIn method
				if (convertedOption == 0) {
					img.goTime("signin.png");
					repo.signIn();
				}
                                // if a user chooses to register, the system executes the register method and saves the new account
				else if (convertedOption == 1) {
					img.goTime("register.png");
					repo.register();
					repo.saveEverything();
				}
                                // if a user chooses to view public twits, the system executes the viewPublicTwits2 method
				else if (convertedOption == 2) {
					img.goTime("publictwits.png");
					repo.viewPublicTwits2();
				}
                                // if a user chooses to view the profiles of registered users, the system executes the printAccountProfiles2 method
				else if (convertedOption == 3) {
					img.goTime("profiles.png");
					repo.printAccountProfiles2();
				}
                                // if a user chooses to search for twits, the system executes the search method
				else if (convertedOption == 4) {
					img.goTime("search.png");
					repo.search();
				}
			}
                        // if statement that executes the code within if a user is signed in
			else {
                                // if a user chooses to sign out, the system executes the signOut method
				if (convertedOption == 0) {
					img.goTime("signout.png");
					repo.signOut();
				}
                                // if a user chooses to view all twits, the system executes the viewAllTwits method
				else if (convertedOption == 1) {
					img.goTime("alltwits.png");
					repo.viewPublicTwits2();
				}
                                // if a user chooses to view the twits of users they are subscribed to, the system executes the viewSubscribedToTwits2 method
				else if (convertedOption == 2) {
					img.goTime("subscribertwits.png");
					repo.viewSubscribedToTwits2();
				}
                                // if a user chooses to view the profiles of other users, the system executes the printAccountProfiles2 method
				else if (convertedOption == 3) {
					img.goTime("profiles.png");
					repo.printAccountProfiles2();
				}
                                // if a user chooses to view the profiles of other users that are subscribed to them, the system executes the viewSubscribers2 method
				else if (convertedOption == 4) {
					img.goTime("subscribers.png");
					repo.viewSubscribers2();
				}
                                // if a user chooses to view the profiles of other users that they are subscribed to, the system executes the viewSubscribedTo2 method
				else if (convertedOption == 5) {
					img.goTime("subscribedto.png");
					repo.viewSubscribedTo2();
				}
                                // if a user chooses to post a twit, the system exectues the postTwit2 method through the ActiveAccount and then saves the new twit
				else if (convertedOption == 6) {
					img.goTime("posttwit.png");
					repo.getActiveAccount().postTwit2();
					repo.saveEverything();
				}
                                // if a user chooses to subscribe to another user, the system executes the subscribe method and then saves the information associated with the new subscription
				else if (convertedOption == 7) {
					img.goTime("subscribe.png");
					repo.subscribe();
					repo.saveEverything();
				}
                                // if a user chooses to unsubscribe from another user, the system executes the sunubscribe method and then saves the information associated with the change
				else if (convertedOption == 8) {
					img.goTime("unsubscribe.png");
					repo.unsubscribe();
					repo.saveEverything();
				}
                                // if a user chooses to search for twits, the system executes the search method
				else if (convertedOption == 9) {
					img.goTime("search.png");
					repo.search();
				}
                                // if a user chooses to edit their profile information, the system executes the editProfile2 method through the ActiveAccount and then saves the new profile changes
				else if (convertedOption == 10) {
					img.goTime("edit.png");
					repo.getActiveAccount().editProfile2();
					repo.saveEverything();
				}
			}
                        // if a user chooses to exit, the system executes the saveEverything method and saves all the infomraiton on twits and account to be reloaded the next time the program runs to teh appropriate databases
			if (convertedOption == menuOptions.size()-1) repo.saveEverything();
                // if a user chooses to exit, the system executes the code within the while statement
		} while (!(convertedOption == menuOptions.size()-1));
		img.goTime("exit.png"); // calls method to print an ASCII image of the "exit.png" file
	}
}
