//package writingTester;
import java.util.ArrayList;
import java.io.*;

public class AccountRepository {
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	AccountRepository() {  }
	
	public void addAccount(Account a) {
		this.accounts.add(a);
	}
	
	public void removeAccount(Account a) {
		boolean found = false;
		for (int i = 0; i < this.accounts.size() && !found; ++i) {
			if (accounts.get(i).getUserName() == a.getUserName()) {
				accounts.remove(i);
				found = true;
			}
		}
	}
	
	public boolean nameUnique(String s) {
		boolean isUnique = true;
		for (int i = 0; i < accounts.size(); ++i) {
			if (accounts.get(i).getUserName().equals(s)) isUnique = false;
		}
		return isUnique;
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
		System.out.println("Array length: " + d.length);
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
	
	//* how to reference the current user
	*public void searchHashtag(String hashtag) {
	*	private ArrayList<Twit> HashtagTwits = new ArrayList<Twit>();
	*	for (int i = 0; i < accounts.size(); i++) {
	*		if (accounts.get(i).searchSubscribers(the current user)) {
	*			for (int j = 0; j < Twits.size(); j++) {
	*				if (Twits.get(j).getTwitHashtag() == hashtag) {
	*					HashtagTwits.add(Twits.get(j));
	*				}
	*			}
	*		}
	*		else for (int k = 0; k < Twits.size(); k++) {
	*			if (Twits.get(k).getTwitViewers() == “anyone” &&  Twits.get(k).getTwitHashtag() == hashtag) {
	*				HashtagTwits.add(Twits.get(k));
	*			}
	*		}
	*	}
	*}
	*/
	
}
