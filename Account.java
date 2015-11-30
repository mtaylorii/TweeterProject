//package writingTester;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Account class contains constructors to create Account objects, methods to
 * post and delete Twits, getter and setter methods for all the data members
 * associated with Account objects, methods to edit an Account's profile
 * information, and prepare the text of Strings to be printed as headers.
 */
public class Account {
	private String UserName; // the user name that is associated with an acount
	private String Password; // the password the user needs to log into Tweeter
	private String ProfileText; // the personal information associated with the user's account
	private String ProfilePhotoPath; // the file path of the profile photo associated with the user's account
	private ArrayList<Twit> Twits = new ArrayList<Twit>(); // ArrayList to store all the twits posted by an account
	private ArrayList<Account> SubscribedTo = new ArrayList<Account>(); // ArrayList to store all the accounts that this account is subscribed to
	private ArrayList<Account> Subscribers = new ArrayList<Account>(); // ArrayList to store all the accounts that are subscribed to this account
	/**
        * Accessor method for user name.
        * @return the user name of the account
        */
	public String getUserName() { return this.UserName; }
	/**
        * Mutator method to change the user name of an account.
        * @param s the updated user name of an account
        */
	public void setUserName(String s) { this.UserName = s; }
	/**
        * Accessor method for password.
        * @return the password of the account
        */
	public String getPassword() { return this.Password; }
	/**
        * Mutator method to change the password of an account.
        * @param s the updated password of an account
        */
	public void setPassword(String s) { this.Password = s; }
	/**
        * Accessor method for the personal information on a profile.
        * @return the personal information on a profile
        */
	public String getProfileText() { return this.ProfileText; }
	/**
        * Mutator method to change the personal information on a profile.
        * @param s the updated personal information on a profile
        */
	public void setProfileText(String s) { this.ProfileText = s; }
	/**
        * Accessor method for the file path of the profile photo associated with a user's account.
        * @return the file path of the profile photo associated with a user's account
        */
	public String getProfilePhotoPath() { return this.ProfilePhotoPath; }
	/**
        * Mutator method to change the file path of the profile photo associated with a user's account.
        * @param s the updated file path of the profile photo associated with a user's account
        */
	public void setProfilePhotoPath(String s) { this.ProfilePhotoPath = s; }
	/**
         * Method to post a twit by adding it to the Twits ArrayList associated with this account.
         * @param t the twit to be posted
         */
	public void postTwit(Twit t) { this.Twits.add(t); }
	/**
         * Method to create a new twit and post it by adding it to the Twits ArrayList associated with this account.
         */
	public void postTwit2() {
		Twit t = new Twit();
		String userOption = "";
		int convertedOption = -1;
		Scanner userInput = new Scanner(System.in);
		boolean isValid = false;
		int timesPrompted = 0;
		// creates an ArrayList of String objects to store the options on the program's menu
		ArrayList<String> menuOptions = new ArrayList<String>();
		// creates an Array of Strings that contains options related to posting a twit for a menu
		String menu[] = new String[]{"Edit twit text", "Edit twit picture", "Edit twit visibility", "Post twit", "Back to main menu"};
		// for loop that iterates throught the Strings in the menu Array, and adds each String to the menuOptions ArrayList
		for (int i = 0; i < menu.length; ++i) menuOptions.add(menu[i]);
		
		double newTwitIdentifier = Math.random();
		boolean uniqueIdentifier = false;
		// while loop that checks to see if the new Twit's TwitIdentifier is already taken, and gives it a new identifier if it is
		while (!uniqueIdentifier) {
			boolean taken = false;
			newTwitIdentifier = Math.random();
			// for loop that iterates through the Twits ArrayList as long as !taken returns true
			for (int i = 0; i < Twits.size() && !taken; ++i) {
				if (Twits.get(i).getTwitIdentifier() == newTwitIdentifier) taken = true;
			}
			if (!taken) uniqueIdentifier = true;
		}
		t.setTwitIdentifier(newTwitIdentifier);
		// turns the new TwitIdentifier into a String that is stored in imageFileName
		String imageFileName = Double.toString(newTwitIdentifier);
		// do statement that takes a user through the steps of posting their twit
		do {
			timesPrompted = 0;
			isValid = false;
			// while loop that allows the user to choose a piece of information to be associated with the twit that they'd like to enter
			while (!isValid) {
				isValid = false;
				userOption = "";
				convertedOption = -1;
				if (timesPrompted > 0) System.out.println(printHeader("Incorrect option"));
				System.out.println(printHeader("Please enter an option:"));
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
			// option to enter the text of the twit
			if (convertedOption == 0) {
				System.out.println(printHeader("Please enter the twit text:"));
				System.out.print(">>> ");
				userInput.nextLine();
				userOption = userInput.nextLine();
				t.setTwitText(userOption);
			}
			// option to enter the photo file path of the twit
			else if (convertedOption == 1) {
				System.out.println(printHeader("Please enter the twit picture file path:"));
				System.out.print(">>> ");
				userOption = userInput.next();
				ASCII img = new ASCII();
				String asciiImg = img.returnString(userOption);
				if (asciiImg.equals("")) asciiImg = "~";
				t.setTwitPhotoPath(asciiImg);
			}
			// option to choose the visibility of the twit
			else if (convertedOption == 2) {
				ArrayList<String> menuOptionsVisibility = new ArrayList<String>();
				menu = null;
				menu = new String[]{"Public (default)", "Subscribers only"};
				for (int i = 0; i < menu.length; ++i) menuOptionsVisibility.add(menu[i]);
				isValid = false;
				timesPrompted = 0;
				int convertedOption2 = -1;
				while (!isValid) {
					userOption = "";
					convertedOption2 = -1;
					if (timesPrompted > 0) System.out.println(printHeader("Incorrect option"));
					System.out.println(printHeader("Please enter an option:"));
					for (int i = 0; i < menuOptionsVisibility.size(); ++i) System.out.println(i + " " + menuOptionsVisibility.get(i));
					System.out.print(">>> ");
					userOption = userInput.next();
					try {
						convertedOption2 = Integer.parseInt(userOption);
						if (convertedOption2 <= menuOptionsVisibility.size()-1 && convertedOption2 > -1 ) isValid = true;
					}
					catch (NumberFormatException e) {
						System.out.println(printHeader("Please enter a valid integer"));
					}
					if (!isValid) ++timesPrompted;
				}
				if (convertedOption2 == 0) t.setTwitVisibility(true);
				else if (convertedOption2 == 1) t.setTwitVisibility(false);
			}
			// option to post the twit with the information entered
			else if (convertedOption == 3) {
				if (t.getTwitText().equals("~")) System.out.println(printHeader("Can not post an empty twit"));
				else Twits.add(t);
			}
		// exits the do statement if the user posts the twit or chooses to go back to the main menu
		} while(!(convertedOption == menuOptions.size()-1 || convertedOption == menuOptions.size()-2));
	}
	/**
        * Accessor method for ArrayList of Twits associated with a user's account.
        * @return the ArrayList of Twits associated with a user's account
        */
	public ArrayList<Twit> getTwits() { return this.Twits; }
	/**
        * Accessor method for ArrayList of Twits associated with a specified user's account.
        * @return the ArrayList of Twits associated with the specified user's account
        */
	public ArrayList<Twit> getTwits(Account a) { return a.getTwits(); }
	/**
         * Method to gather the TwitIdentifiers for all the Twits in an account and store them in a String.
         * @return the String of the TwitIdentifiers
         */
	public String printTwitIdentifiers() { 
		String result = "";
		for (int i = 0; i < this.Twits.size(); ++i) {
			if (i > 0) result += ",";
			result += Twits.get(i).getTwitIdentifier();
		}
		return result;
	}
	/**
        * Accessor method for ArrayList of users that a user is subscribed to.
        * @return the ArrayList of SubscribedTo users associated with a user's account
        */
	public ArrayList<Account> getSubscribedTo() { return this.SubscribedTo; }
	/**
         * Method to gather the UserNames for all the users that a user is subscribed to and store them in a String.
         * @return the String of the Usernames
         */
	public String printSubscribedTo() { 
		String result = "";
		for (int i = 0; i < this.SubscribedTo.size(); ++i) {
			if (i > 0) result += ",";
			result += SubscribedTo.get(i).getUserName();
		}
		return result;
	}
	/**
        * Accessor method for ArrayList of users that are subscribed to a user's account.
        * @return the ArrayList of SubscribedTo users associated with a user's account
        */
	public ArrayList<Account> getSubscribers() { return this.Subscribers; }
	/**
         * Method to gather the UserNames for all the users that are subscribed to a user's account and store them in a String.
         * @return the String of the Usernames
         */
	public String printSubscribers() { 
		String result = "";
		for (int i = 0; i < this.Subscribers.size(); ++i) {
			if (i > 0) result += ",";
			result += Subscribers.get(i).getUserName();
		}
		return result;
	}
	/**
         * Method to subscribe to another user's account.
         * @param a the Account to be subscribed to
         */
	public void subscribe(Account a) { 
		this.SubscribedTo.add(a);
		a.Subscribers.add(this);
	}
	/**
         * Method to unsubscribe from another user's account.
         * @param a the Account to unsubscribe from
         */
	public void unsubscribe(Account a) {
		boolean found = false;
		for (int i = 0; i < this.SubscribedTo.size() && !found; ++i) {
			if (this.SubscribedTo.get(i).getUserName() == a.getUserName()) {
				this.SubscribedTo.remove(i);
				found = true;
			}
		}
		found = false;
		for (int i = 0; i < a.Subscribers.size() && !found; ++i) {
			if (a.Subscribers.get(i).getUserName() == this.getUserName()) {
				a.Subscribers.remove(i);
				found = true;
			}
		}
	}
	/**
         * Method to create a String of all the information associated with a certain Account object.
         * @return the String of the information associated with an Account object
         */
	public String toString() {
		String result = this.getUserName() + ";";
		result += this.getPassword() + ";";
		if (!(this.getProfileText().equals(""))) result += this.getProfileText() + ";";
		else result += "~;";
		if (!(this.getProfilePhotoPath().equals(""))) result += this.getProfilePhotoPath() + ";";
		else result += "~;";
		if (!(this.Twits.size() == 0)) result += this.printTwitIdentifiers() + ";";
		else result += "~;";
		if (!(this.SubscribedTo.size() == 0))result += this.printSubscribedTo() + ";";
		else result += "~;";
		if (!(this.Subscribers.size() == 0)) result += this.printSubscribers() + ";";
		else result += "~;";
		result += "\n";
		return result;
	}
	/**
         * Method to create a String of all the information associated with a certain Account object, with "ACCOUNT:" at the beginning.
         * @return the String of the information associated with an Account object
         */
	public String toString2() {
		String result = "ACCOUNT:";
		result += this.getUserName() + ";";
		result += this.getPassword() + ";";
		if (!(this.getProfileText().equals(""))) result += this.getProfileText() + ";";
		else result += "~;";
		if (!(this.Twits.size() == 0)) result += this.printTwitIdentifiers() + ";";
		else result += "~;";
		if (!(this.SubscribedTo.size() == 0))result += this.printSubscribedTo() + ";";
		else result += "~;";
		if (!(this.Subscribers.size() == 0)) result += this.printSubscribers() + ";";
		else result += "~;";
		result += "\n";
		if (!(this.getProfilePhotoPath().equals(""))) result += this.getProfilePhotoPath() + "\n;";
		else result += "~\n;";
		result += "\n";
		return result;
	}
	/**
         * Default constructor to initialize an Account object without giving any parameters.
         */
	Account() {
		this.UserName = "default";
		this.Password = "default";
		this.ProfileText = "";
		this.ProfilePhotoPath = "";
	}
	/**
         * Nondefault constructor to initialize an Account object with a specified UserName.
         * @param u the UserName associated with the Account
         */
	Account(String u) {
		this.UserName = u;
		this.Password = "default";
		this.ProfileText = "";
		this.ProfilePhotoPath = "";
	}
	/**
         * Nondefault constructor to initialize an Account object with a specified UserName and Password.
         * @param u the UserName associated with the Account
         * @param p the Password associated with the Account
         */
	Account(String u, String p) {
		this.UserName = u;
		this.Password = p;
		this.ProfileText = "";
		this.ProfilePhotoPath = "";
	}
	/**
         * Method to take the information on the accounts that are subscribed to an account and add them to the account's SubscribedTo ArrayList
         * @param s String to br processed
         */
	public void processSubscribedTo(String s) {
		String delimiter = "[,]+";
		String[] delimited = s.split(delimiter);
		for (int i = 0; i < delimited.length; ++i) {
			Account tempAccount = new Account();
			String tempString = delimited[i];
			tempAccount.setUserName(tempString);
			this.SubscribedTo.add(tempAccount);
		}
	}
	/**
         * Method to take the information on the accounts that an account is subscribed to and add them to the account's Subscribers ArrayList
         * @param s String to be processed
         */
	public void processSubscribers(String s) {
		String delimiter = "[,]+";
		String[] delimited = s.split(delimiter);
		for (int i = 0; i < delimited.length; ++i) {
			Account tempAccount = new Account();
			String tempString = delimited[i];
			tempAccount.setUserName(tempString);
			this.Subscribers.add(tempAccount);
		}
	}
	/**
         * Method to edit the profile information associated with a user's account.
         */
	public void editProfile2() {
		String userOption = "";
		int convertedOption = -1;
		Scanner userInput = new Scanner(System.in);
		boolean isValid = false;
		int timesPrompted = 0;
		ArrayList<String> menuOptions = new ArrayList<String>();
		String menu[] = new String[]{"Edit bio", "Edit profile picture", "Back to main menu"};
		for (int i = 0; i < menu.length; ++i) menuOptions.add(menu[i]);
			while (!isValid) {
				userOption = "";
				convertedOption = -1;
				if (timesPrompted > 0) System.out.println(printHeader("Incorrect option"));
				System.out.println(printHeader("Please enter an option:"));
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
			if (convertedOption == 0) {
				System.out.println(printHeader("Please enter a new profile biography:"));
				System.out.print(">>> ");
				userInput.nextLine();
				userOption = userInput.nextLine();
				this.setProfileText(userOption);
			}
			if (convertedOption == 1) {
				System.out.println(printHeader("Please enter the profile picture file path:"));
				System.out.print(">>> ");
				userOption = userInput.next();
				ASCII img = new ASCII();
				String newFile = img.returnString(userOption);
				this.setProfilePhotoPath(newFile);
			}
	}
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
}
