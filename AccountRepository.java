package writingTester;
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
	
	public void printAccounts() {
		for (int i = 0; i < accounts.size(); ++i) accounts.get(i).printAccount();
	}
	
	public void loadAccount(String s) {
		String delimiter = "[;]+";
		String[] d = s.split(delimiter);
		Account tempAccount = new Account(d[0], d[1], d[2], d[3], d[4], d[5], d[6]);
		accounts.add(tempAccount);
	}
	
	public void saveDatabase() {
        
        File file = new File("database.txt");
        BufferedWriter bw = null;
        try {
        	bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < accounts.size(); ++i) bw.write(accounts.get(i).toString());
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) try { br.close(); } catch (IOException ignore) {}
        }
	}
	
}