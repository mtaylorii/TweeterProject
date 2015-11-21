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
		for (int i = 0; i < accounts.size(); ++i) { accounts.get(i).printAccount(); }
	}
	
	public void loadAccount(String s) {
		String delimiter = "[;]+";
		String[] d = s.split(delimiter);
		Account tempAccount = new Account(d[0], d[1], d[2], d[3], d[4], d[5], d[6]);
		accounts.add(tempAccount);
	}
	
	public void saveDatabase() {
        String fileName = "database.txt";
        try {
        	BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < accounts.size(); ++i) {
            	bw.write(accounts.get(i).toString());
            	}
            bw.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
            ex.printStackTrace();
        }
        finally {
        	
        }
	}
	
	public void loadDatabase() {
        String fileName = "database.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                loadAccount(line);
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println( "Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
	}
	
}
