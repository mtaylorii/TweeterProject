//package writingTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

/**
 * Class AccountRepository maintains accounts.
 */

public class AccountRepository {
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	private boolean SignedIn = false; //no user is currently signed in
	private Account ActiveAccount; //the account currently signed in to
	
	/**
	 * Default constructor for an AccountRepository.
	 */
	AccountRepository() {  }
	
	/**
	 * Adds an account to the Account ArrayList.
	 * @param a the account to be added
	 */
	public void addAccount(Account a) { this.accounts.add(a); }
	
	/**
	 * Accessor method for ActiveAccount.
	 * @return the active account
	 */
	public Account getActiveAccount() { return this.ActiveAccount; }
	
	/**
	 * Accessor method for SignedIn.
	 * @return true if a user is currently signed in, false if not
	 */
	public boolean getSignedIn() { return this.SignedIn; }
	
	/**
	 * Prompts a user to sign into an account.
	 * @return if the database is empty
	 */
	public void signIn() {
		if (this.accounts.size() == 0) {
			System.out.println(printHeader(" Database is empty. Please register first "));
			return;
		}
		Scanner AccountScanner = new Scanner(System.in);
		String AccountInput = "";
		boolean validInput = false;
		boolean emptyInput = false;
		boolean uniqueName = false;
		while (!validInput) {
			System.out.println(printHeader(" Please enter an account name (~ to return to menu): "));
			System.out.print(">>> ");
			AccountInput = AccountScanner.next();
			if (AccountInput.equals("~")) return;
			if (AccountInput == "") emptyInput = true;
			uniqueName = this.nameUnique(AccountInput);
			if (!uniqueName && !emptyInput) validInput = true;
			if (uniqueName) System.out.println(printHeader("The account name '" + AccountInput + "' does not exist, please choose another name"));
		}
		String AccountName = AccountInput;
		AccountInput = "";
		validInput = false;
		boolean matchingPassword = false;
		while (!validInput) {
			emptyInput = false;
			System.out.println(printHeader("Please enter an account password (~ to return to menu):"));
			System.out.print(">>> ");
			AccountInput = AccountScanner.next();
			if (AccountInput.equals("~")) return;
			if (AccountInput == "") emptyInput = true;
			matchingPassword = this.matchingPassword(AccountName, AccountInput);
			if (!emptyInput && matchingPassword) validInput = true;
			if (!matchingPassword) System.out.println(printHeader("The password does not match for the specified user"));
		}
		this.signIn(AccountName);
	}

	/**
	 * Signs a user into an account given the username of an Account object.
	 * @param s the username of an Account to be signed into
	 */
	public void signIn(String s) {
		this.SignedIn = true;
		for (int i = 0; i < this.accounts.size(); ++i) {
			if (accounts.get(i).getUserName().equals(s)) {
				this.ActiveAccount = accounts.get(i);
			}
		}
		System.out.println(printHeader("[You are signed in as " + this.getActiveAccount().getUserName() + "]"));
	}
	
	/**
	 * Signs the user out of the account currently signed into
	 */
	public void signOut() {
		this.SignedIn = false;
		System.out.println(printHeader("[" + this.getActiveAccount().getUserName() + " has been signed out]"));
		this.ActiveAccount = new Account();
	}

	/**
	 * Makes sure the userName is not a duplicate of another.
	 * @param s the username of the account.
	 * @return if the name is unique to the user.
     */
	public boolean nameUnique(String s) {
		boolean isUnique = true;
		for (int i = 0; i < accounts.size(); ++i) {
			if (accounts.get(i).getUserName().equals(s)) isUnique = false;
		}
		return isUnique;
	}

	/**
	 * Makes sure the password entered corresponds to the correct user.
	 * @param s the username of the account.
	 * @param p the password of the account.
     * @return if the password matches the user's account password.
     */
	public boolean matchingPassword(String s, String p) {
		boolean match = false;
		for (int i = 0; i < accounts.size(); ++i) {
			if (accounts.get(i).getUserName().equals(s) && accounts.get(i).getPassword().equals(p)) match = true;
		}
		return match;
	}

	/**
	 *  This method prompts the user to search for another user that
	 *  they wish to subscribe to, identifies whether or not that user
	 *  exists, and then links the user account to the subscribed account.
	 */
	public void subscribe() {
		Scanner AccountScanner = new Scanner(System.in);
		String AccountInput = "";
		boolean validInput = false;
		boolean emptyInput = false;
		boolean uniqueName = false;
		while (!validInput) {
			System.out.println(printHeader(" Please enter an account name (~ to return to menu): "));
			System.out.print(">>> ");
			AccountInput = AccountScanner.next();
			if (AccountInput.equals("~")) return;
			if (AccountInput == "") emptyInput = true;
			uniqueName = this.nameUnique(AccountInput);
			if (!uniqueName && !emptyInput) validInput = true;
			if (uniqueName) System.out.println(printHeader("The account name '" + AccountInput + "' does not exist, please choose another name"));
		}	
		boolean found = false;
		for (int i = 0; i < this.accounts.size() && !found; ++i) {
			if (accounts.get(i).getUserName().equals(AccountInput)) {
				this.getActiveAccount().subscribe(accounts.get(i));
				found = true;
				System.out.println(printHeader(" Subscribed to " + accounts.get(i).getUserName() + "..."));
			}
		}
	}

	/**
	 *  This method prompts the user to search for another user that
	 *  they wish to unsubscribe to, identifies whether or not that user
	 *  exists within their subscribers, and then deletes the links that
	 *  connects the two users.
	 */
	public void unsubscribe() {
		Scanner AccountScanner = new Scanner(System.in);
		String AccountInput = "";
		boolean validInput = false;
		boolean emptyInput = false;
		boolean uniqueName = false;
		while (!validInput) {
			System.out.println(printHeader(" Please enter an account name (~ to return to menu): "));
			System.out.print(">>> ");
			AccountInput = AccountScanner.next();
			if (AccountInput.equals("~")) return;
			if (AccountInput == "") emptyInput = true;
			uniqueName = this.nameUnique(AccountInput);
			if (!uniqueName && !emptyInput) validInput = true;
			if (uniqueName) System.out.println(printHeader("The account name '" + AccountInput + "' does not exist, please choose another name"));
		}	
		boolean found = false;
		for (int i = 0; i < this.accounts.size() && !found; ++i) {
			if (accounts.get(i).getUserName().equals(AccountInput)) {
				this.getActiveAccount().unsubscribe(accounts.get(i));
				found = true;
				System.out.println(printHeader(" Unsubscribed from " + accounts.get(i).getUserName() + "..."));
			}
		}
	}

	/**
	 *  This method prints the profiles that contained within the database;
	 *  each profile will be displayed with a name, bio, number of subscribers,
	 *  and who they are subscribed to. Otherwise it is emptied.
	 */
	public void printAccountProfiles2() {
		if (accounts.size() == 0) {
			System.out.println(printHeader("Database is empty"));
		}
		else {
			for (int i = 0; i < accounts.size(); ++i) {
				String header = "";
				String headerText = " Profile: ";
				for (int j = 0; j < 100 - headerText.length(); ++j) {
					header += "*";
					if (j == (100 - headerText.length())/2) header += headerText;
				}
				System.out.println(header);
				System.out.println("NAME: " + accounts.get(i).getUserName());
				System.out.println("BIO: " + accounts.get(i).getProfileText());
				System.out.println("# SUBSCRIBED TO: " + accounts.get(i).getSubscribedTo().size());
				System.out.println("# SUBSCRIBERS: " + accounts.get(i).getSubscribers().size());
				System.out.println(accounts.get(i).getProfilePhotoPath());
				for (int j = 0; j < 100; ++j) System.out.print("*");
				System.out.println();
			}
		}
	}

	/**
	 *  Saves the data after every instance.
	 */
	public void saveEverything() {
		saveAccounts2();
		saveTwits2();
	}

	/**
	 *  Loads the data after every instance.
	 */
	public void loadEverything() {
		loadAccounts2();
		loadTwits2();
	}

	/**
	 * Loads the user's account and sets the profile photo.
	 * @param s the user's account.
	 * @param img the photo of the user's account.
     */
	public void loadAccount2(String s, String img) {
		String delimiter = "[;]+";
		String[] d = s.split(delimiter);
		Account tempAccount = new Account(d[0], d[1]);
		//System.out.println("Array length: " + d.length);
		if (!(d[2].equals("~"))) tempAccount.setProfileText(d[2]);
		//if (!(d[3].equals("~"))) tempAccount.setProfilePhotoPath(d[3]);
		//if (!(d[4].equals("~"))) tempAccount.processTwits(d[4]);
		if (!(d[4].equals("~"))) tempAccount.processSubscribedTo(d[4]);
		if (!(d[5].equals("~"))) tempAccount.processSubscribers(d[5]);
		tempAccount.setProfilePhotoPath(img);
		accounts.add(tempAccount);
	}

	/**
	 * Loads the intended twit from a user.
	 * @param author author of the intended twit.
	 * @param info the info stored within the twit.
	 * @param ascii the actual data.
     */
	public void loadTwit2(String author, String info, String ascii) {
		String delimiter = "[;]+";
		String[] d = info.split(delimiter);
		Twit tempTwit = new Twit();
		tempTwit.setTwitAuthor(author);
		tempTwit.setTwitIdentifier(Double.parseDouble(d[0]));
		tempTwit.setTwitText(d[1]);
		tempTwit.setTwitVisibility(Boolean.parseBoolean(d[2]));
		tempTwit.setTwitPhotoPath(ascii);
		boolean found = false;
		for (int i = 0; i < this.accounts.size() && !found; ++i) {
			if (accounts.get(i).getUserName().equals(author)) {
				accounts.get(i).postTwit(tempTwit);
				found = true;
				//System.out.println("ADDED");
			}
		}
	}
	/**
         * Method that writes the information on Account objects into a "accounts2.txt" file, which will serve as a database for the accounts; the information can then be put back into Account objects with the loadAccounts method.
         */
	public void saveAccounts2() {
        File file = new File("accounts2.txt");
        BufferedWriter bw = null;
        try {
        	bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < accounts.size(); ++i) bw.write(accounts.get(i).toString2());
            //System.out.println("Accounts saved...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) try { bw.close(); } catch (IOException ignore) {}
        }
	}
	/**
         * Method that reads through the "accounts2.txt" file, which will serve as a database for the accounts, and calls the loadAccount method to put the information associated with the accounts into Account objects.
         */
	public void loadAccounts2() {
		String line = "";
        BufferedReader br = null;
        boolean atAccount = false;
        String accountStr = "ACCOUNT:";
        String accountAscii = "";
        String accountInfo = "";
        try {
        	br = new BufferedReader(new FileReader("accounts2.txt"));
        	while((line = br.readLine()) != null) {
        		atAccount = false;
        		if (line.length() >= accountStr.length()) {
        			if (line.substring(0, accountStr.length()).equals(accountStr)) {
            			accountInfo = line.substring(accountStr.length());
            			accountAscii = "";
            			atAccount = true;
            		}
        		}
        		if (!atAccount) {
        			if (!(line.contains(";"))) accountAscii += line + "\n";
        			else loadAccount2(accountInfo, accountAscii);
        		}
        	}
        	for (int i = 0; i < this.accounts.size(); ++i) {
        		for (int j = 0; j < accounts.get(i).getSubscribers().size(); ++j) {
        			for (int k = 0; k < this.accounts.size(); ++k) {
        				if (accounts.get(i).getSubscribers().get(j).getUserName().equals(this.accounts.get(k).getUserName())) {
        					this.accounts.get(i).getSubscribers().set(j, this.accounts.get(k));
        				}
        			}
        		}
        	}
        	for (int i = 0; i < this.accounts.size(); ++i) {
        		for (int j = 0; j < accounts.get(i).getSubscribedTo().size(); ++j) {
        			for (int k = 0; k < this.accounts.size(); ++k) {
        				if (accounts.get(i).getSubscribedTo().get(j).getUserName().equals(this.accounts.get(k).getUserName())) {
        					this.accounts.get(i).getSubscribedTo().set(j, this.accounts.get(k));
        				}
        			}
        		}
        	}
        	//System.out.println("Accounts loaded...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) try { br.close(); } catch (IOException ignore) {}
        }
	}
	/**
         * Method that writes the information on Twit objects into a "twits2.txt" file, which will serve as a database for the twits; the information can then be put back into Twit objects with the loadTwits method.
         */
	public void saveTwits2() {
        File file = new File("twits2.txt");
        BufferedWriter bw = null;
        try {
        	bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < accounts.size(); ++i) {
            	 bw.write("ACCOUNT:" + accounts.get(i).getUserName() + "\n");
            	for (int j = 0; j < accounts.get(i).getTwits().size(); ++j) bw.write(accounts.get(i).getTwits().get(j).toString2() + "\n");
            }
            //System.out.println("Twits saved...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) try { bw.close(); } catch (IOException ignore) {}
        }
	}
	/**
         * Method that reads through the "twits2.txt" file, which will serve as a database for the twits, and calls the loadTwit method to put the information associated with the twits into Twit objects.
         */
	public void loadTwits2() {
		String line = "";
		String twitAuthor = "";
		String twitInfo = "";
		String twitAscii = "";
		boolean readAuthor = false;
		boolean atAccount = false;
		boolean atInfo = false;
        BufferedReader br = null;
        String accountStr = "ACCOUNT:";
        String infoStr = "INFO:";
        try {
        	br = new BufferedReader(new FileReader("twits2.txt"));
        	while((line = br.readLine()) != null) {
        		atAccount = false;
        		atInfo = false;
        		if (line.length() >= accountStr.length()) {
        			if (line.substring(0, accountStr.length()).equals(accountStr)) {
            			twitAuthor = line.substring(accountStr.length());
            			twitAscii = "";
            			atAccount = true;
            		}
        		}
        		if (!atAccount) {
        			if (line.length() >= infoStr.length()) {
            			if (line.substring(0, infoStr.length()).equals(infoStr)) {
            				twitInfo = line.substring(infoStr.length());
            				atInfo = true;
            			}
        			}
        		}
        		if (!atAccount && !atInfo) {
        			if (!(line.contains(";"))) twitAscii += line + "\n";
        			else loadTwit2(twitAuthor, twitInfo, twitAscii);
        		}
        	}
        	//System.out.println("Twits loaded...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) try { br.close(); } catch (IOException ignore) {}
        }
	}
	/**
         * Method that allows a user to register an account.
         */
	public void register() {
		Account temp =  new Account("temp", "temp");
		Scanner AccountScanner = new Scanner(System.in);
		String AccountInput = "";
		boolean validInput = false;
		boolean emptyInput = false;
		boolean uniqueName = false;
		while (!validInput) {
			uniqueName = false;
			emptyInput = false;
			System.out.println(printHeader(" Please enter an account name (~ to return to menu): "));
			System.out.print(">>> ");
			AccountInput = AccountScanner.next();
			if (AccountInput.equals("~")) return;
			if (AccountInput == "") emptyInput = true;
			uniqueName = this.nameUnique(AccountInput);
			if (uniqueName && !emptyInput) validInput = true;
			if (!uniqueName) System.out.println(printHeader("The account name '" + AccountInput + "' already exists, please choose another name"));
		}
		temp.setUserName(AccountInput);
		AccountInput = "";
		validInput = false;
		while (!validInput) {
			emptyInput = false;
			System.out.println(printHeader("Please enter an account password (~ to return to menu):"));
			System.out.print(">>> ");
			AccountInput = AccountScanner.next();
			if (AccountInput.equals("~")) return;
			if (AccountInput == "") emptyInput = true;
			if (!emptyInput) validInput = true;
		}
		temp.setPassword(AccountInput);
		AccountInput = "";
		this.addAccount(temp);
		System.out.println("Account created...");
	}
	/**
         * Method to print all the information associated with any twits that are publicly viewable.
         */
	public void viewPublicTwits2() {
		for (int i = 0; i < accounts.size(); ++i) {
			System.out.println(printHeader(" " + accounts.get(i).getUserName() + "'s public twits: "));
			for (int j = 0; j < accounts.get(i).getTwits().size(); ++j){
				if (accounts.get(i).getTwits().get(j).getTwitVisibility() == true) {
					accounts.get(i).getTwits().get(j).printTwit2();
					System.out.println();
				}
			}
			for (int j = 0; j < 100; ++j) System.out.print("*");
			System.out.println();
		}
	}
	/**
         * Method to print all the information associated with all the accounts that are subscribed to the user.
         */
	public void viewSubscribers2() {
		if (this.getActiveAccount().getSubscribers().size() == 0) {
			System.out.println(printHeader("No Subscribers"));
		}
		else {
			for (int i = 0; i < this.getActiveAccount().getSubscribers().size(); ++i) {
				String header = "";
				String headerText = " Profile: ";
				for (int j = 0; j < 100 - headerText.length(); ++j) {
					header += "*";
					if (j == (100 - headerText.length())/2) header += headerText;
				}
				System.out.println(header);
				System.out.println("NAME: " + this.getActiveAccount().getSubscribers().get(i).getUserName());
				System.out.println("BIO: " + this.getActiveAccount().getSubscribers().get(i).getProfileText());
				System.out.println(this.getActiveAccount().getSubscribers().get(i).getProfilePhotoPath());
				for (int j = 0; j < 100; ++j) System.out.print("*");
				System.out.println();
			}
		}
	}
	/**
         * Method to print all the information associated with all the accounts the user if subscribed to.
         */
	public void viewSubscribedTo2() {
		if (this.getActiveAccount().getSubscribedTo().size() == 0) {
			System.out.println(printHeader("No accounts subscribed to"));
		}
		else {
			for (int i = 0; i < this.getActiveAccount().getSubscribedTo().size(); ++i) {
				String header = "";
				String headerText = " Profile: ";
				for (int j = 0; j < 100 - headerText.length(); ++j) {
					header += "*";
					if (j == (100 - headerText.length())/2) header += headerText;
				}
				System.out.println(header);
				System.out.println("NAME: " + this.getActiveAccount().getSubscribedTo().get(i).getUserName());
				System.out.println("BIO: " + this.getActiveAccount().getSubscribedTo().get(i).getProfileText());
				System.out.println("# SUBSCRIBED TO: " + this.getActiveAccount().getSubscribedTo().get(i).getSubscribedTo().size());
				System.out.println("# SUBSCRIBERS: " + this.getActiveAccount().getSubscribedTo().get(i).getSubscribers().size());
				System.out.println(this.getActiveAccount().getSubscribedTo().get(i).getProfilePhotoPath());
				for (int j = 0; j < 100; ++j) System.out.print("*");
				System.out.println();
			}
		}
	}
	/**
         * Method to print all the information associated with all the twits for each of the accounts that the user is subscribed to.
         */
	public void viewSubscribedToTwits2() {
		if (this.getActiveAccount().getSubscribedTo().size() == 0) {
			System.out.println(printHeader("No accounts subscribed to"));
		}
		else {
			for (int i = 0; i < this.getActiveAccount().getSubscribedTo().size(); ++i) {
				System.out.println(printHeader(" " + this.getActiveAccount().getSubscribedTo().get(i).getUserName() + "'s twits: "));
				for (int j = 0; j < this.getActiveAccount().getSubscribedTo().get(i).getTwits().size(); ++j) {
					this.getActiveAccount().getSubscribedTo().get(i).getTwits().get(j).printTwit2();
					System.out.println();
				}
				for (int j = 0; j < 100; ++j) System.out.print("*");
				System.out.println();
			}
		}
	}
	/**
	 * Method that allows a user to enter something they would like to search for in twits, whether it be which account posted the twit or what hashtag the twit was posted with.
	 */
	public void search() {
		Scanner AccountScanner = new Scanner(System.in);
		String AccountInput = "";
		boolean validInput = false;
		boolean emptyInput = false;
		boolean uniqueName = false;
		while (!validInput) {
			System.out.println(printHeader(" Please enter a search term (# for twits, @ for accounts, ~ to return to menu): "));
			System.out.print(">>> ");
			AccountInput = AccountScanner.next();
			if (AccountInput.equals("~")) return;
			if (AccountInput == "") emptyInput = true;
			uniqueName = this.nameUnique(AccountInput.substring(1));
			if (AccountInput.substring(0, 1).equals("#")) uniqueName = false;
			if (!uniqueName && !emptyInput) validInput = true;
			if (!(AccountInput.substring(0, 1).equals("#")) && !(AccountInput.substring(0, 1).equals("@"))) {
				System.out.println(printHeader("Please prefix your search term with either # or @"));
				validInput = false;
			}
			if (uniqueName) {
				System.out.println(printHeader("The account name '" + AccountInput + "' does not exist"));
				return;
			}
		}	
		if (AccountInput.substring(0, 1).equals("@")){
			boolean found = false;
			for (int i = 0; i < this.accounts.size() && !found; ++i) {
				if (accounts.get(i).getUserName().equals(AccountInput.substring(1))) {
					String header = "";
					String headerText = " Profile: ";
					for (int j = 0; j < 100 - headerText.length(); ++j) {
						header += "*";
						if (j == (100 - headerText.length())/2) header += headerText;
					}
					System.out.println(header);
					System.out.println("NAME: " + this.accounts.get(i).getUserName());
					System.out.println("BIO: " + this.accounts.get(i).getProfileText());
					ASCII img = new ASCII();
					img.goTime(this.accounts.get(i).getProfilePhotoPath());
					for (int j = 0; j < 100; ++j) System.out.print("*");
					System.out.println();
					found = true;
					System.out.println(printHeader(" " + accounts.get(i).getUserName() + "'s public twits: "));
					for (int j = 0; j < accounts.get(i).getTwits().size(); ++j){
						if (accounts.get(i).getTwits().get(j).getTwitVisibility() == true) {
							accounts.get(i).getTwits().get(j).printTwit();
							System.out.println();
						}
					}
					for (int j = 0; j < 100; ++j) System.out.print("*");
					System.out.println();
				}
			}
		}
		else if (AccountInput.substring(0, 1).equals("#")){
			boolean found = false;
			for (int i = 0; i < accounts.size(); ++i) {
				for (int j = 0; j < accounts.get(i).getTwits().size(); ++j) {
					if (accounts.get(i).getTwits().get(j).hasTerm(AccountInput)) {
						System.out.println(printHeader(accounts.get(i).getTwits().get(j).getTwitAuthor() + ":"));
						accounts.get(i).getTwits().get(j).printTwit();
						for (int k = 0; k < 100; ++k) System.out.print("*");
						System.out.println();
						found = true;
					}
				}
			}
			if (!found) System.out.println(printHeader("Could not find any twits containing '" + AccountInput +"' "));
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
