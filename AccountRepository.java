//package writingTester;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class AccountRepository {
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private boolean SignedIn = false;
	private Account ActiveAccount;
	
	AccountRepository() {  }
	
	public void addAccount(Account a) {
		this.accounts.add(a);
	}
	
	public Account getActiveAccount() {
		return this.ActiveAccount;
	}
	
	public boolean getSignedIn() { return this.SignedIn; }
	
	public void SignIn(Account a) {
		this.SignedIn = true;
		this.ActiveAccount = a;
	}
	
	public void SignIn(String s) {
		this.SignedIn = true;
		for (int i = 0; i < this.accounts.size(); ++i) {
			if (accounts.get(i).getUserName().equals(s)) {
				this.ActiveAccount = accounts.get(i);
			}
		}
	}
	
	public void SignOut() {
		this.SignedIn = false;
		this.ActiveAccount = new Account();
	}
	
	public boolean removeAccount(Account a) {
		boolean found = false;
		boolean result = false;
		for (int i = 0; i < this.accounts.size() && !found; ++i) {
			if (accounts.get(i).getUserName().equals(a.getUserName())) {
				accounts.remove(i);
				found = true;
				result = true;
			}
		}
		return result;
	}
	
	public boolean removeAccount(String s) {
		boolean found = false;
		boolean result = false;
		for (int i = 0; i < this.accounts.size() && !found; ++i) {
			if (accounts.get(i).getUserName().equals(s)) {
				accounts.remove(accounts.get(i));
				found = true;
				result = true;
			}
		}
		return result;
	}
	
	public boolean nameUnique(String s) {
		boolean isUnique = true;
		for (int i = 0; i < accounts.size(); ++i) {
			if (accounts.get(i).getUserName().equals(s)) isUnique = false;
		}
		return isUnique;
	}
	
	public boolean matchingPassword(String s, String p) {
		boolean match = false;
		for (int i = 0; i < accounts.size(); ++i) {
			if (accounts.get(i).getUserName().equals(s) && accounts.get(i).getPassword().equals(p)) match = true;
		}
		return match;
	}
	
	public void printAccounts() {
		if (accounts.size() == 0) {
			System.out.println("Database is empty");
		}
		else {
			for (int i = 0; i < accounts.size(); ++i) accounts.get(i).printAccount();
		}
		return;
	}
	
	public void loadAccount(String s) {
		String delimiter = "[;]+";
		String[] d = s.split(delimiter);
		Account tempAccount = new Account(d[0], d[1]);
		//System.out.println("Array length: " + d.length);
		if (!(d[2].equals("~"))) tempAccount.setProfileText(d[2]);
		if (!(d[3].equals("~"))) tempAccount.setProfilePhotoPath(d[3]);
		if (!(d[4].equals("~"))) tempAccount.processTwits(d[4]);
		if (!(d[5].equals("~"))) tempAccount.processSubscribedTo(d[5]);
		if (!(d[6].equals("~"))) tempAccount.processSubscribers(d[6]);
		accounts.add(tempAccount);
	}
	
	public void saveDatabase() {
        
        File file = new File("database.txt");
        BufferedWriter bw = null;
        try {
        	bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < accounts.size(); ++i) bw.write(accounts.get(i).toString());
            System.out.println("Database saved...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) try { bw.close(); } catch (IOException ignore) {}
        }
	}
	
	public void loadDatabase() {
		String line = "";
        BufferedReader br = null;
        try {
        	br = new BufferedReader(new FileReader("database.txt"));
        	while((line = br.readLine()) != null) {
        		loadAccount(line);
        	}
        	System.out.println("Database loaded...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) try { br.close(); } catch (IOException ignore) {}
        }
	}

	public void searchOption(){
		Scanner input = new Scanner(System.in);
		System.out.println("What would you like to search for?");
		String search = input.next();
		if (search.contains("@")){
			String str = search.replace('@', ' ');
			if (accounts.contains(str)) {    // I can't figure out how to search through the database.
				System.out.println("Searching kinda works.");
			}
			else {
				System.out.println("That user does not exist.");
			}
		}
		else if (search.contains("#")){
			System.out.println("Hashtags are dumb.");
		}
	}
	
	public ArrayList searchHashtag(String hashtag) {
                String h = hashtag;
		private ArrayList<Twit> hashtagTwits = new ArrayList<Twit>();
		for (int i = 0; i < accounts.size(); i++) {
			if (this.searchSubscribers(accounts.get(i).getUserName)) {
				for (int j = 0; j < Twits.size(); j++) {
					if (Twits.get(j).getTwitHashtag() == h) {
						hashtagTwits.add(Twits.get(j));
					}
				}
			}
			else for (int k = 0; k < Twits.size(); k++) {
				if (Twits.get(k).getTwitPubliclyViewable() == true && Twits.get(k).getTwitHashtag() == h) {
					hashtagTwits.add(Twits.get(k));
				}
			}
		}
		return hashtagTwits;
	}
}
