import java.util.ArrayList;

public class AccountRepository {
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
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
	
}
