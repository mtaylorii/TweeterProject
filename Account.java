import java.util.ArrayList;

public class Account {
	private String UserName;
	private String Password;
	private String ProfileText;
	private String ProfilePhotoPath;
	private ArrayList<Twit> Twits;
	private ArrayList<Account> SubscribedTo;
	private ArrayList<Account> Subscribers;
	
	public String getUserName() { return this.UserName; }
	public void setUserName(String s) { this.UserName = s; }

	public String getPassword() { return this.Password; }
	public void setPassword(String s) { this.Password = s; }

	public String getProfileText() { return this.ProfileText; }
	public void setProfileText(String s) { this.ProfileText = s; }

	public String getProfilePhotoPath() { return this.ProfilePhotoPath; }
	public void setProfilePhotoPath(String s) { this.ProfilePhotoPath = s; }
	
	public void postTwit(Twit t) {
		double newTwitIdentifier = Math.random();
		boolean uniqueIdentifier = false;
		while (!uniqueIdentifier) {
			boolean taken = false;
			newTwitIdentifier = Math.random();
			for (int i = 0; i < Twits.size() && !taken; ++i) {
				if (Twits.get(i).getTwitIdentifier() == newTwitIdentifier) taken = true;
			}
			if (!taken) uniqueIdentifier = true;
		}
		t.setTwitIdentifier(newTwitIdentifier);
		Twits.add(t);
	}
	
	public void deleteTwit(Twit t) {
		boolean found = false;
		for (int i = 0; i < this.Twits.size() && !found; ++i) {
			if (Twits.get(i).getTwitIdentifier() == t.getTwitIdentifier()) {
				Twits.remove(i);
				found = true;
			}
		}
	}
	public ArrayList<Twit> getTwits() { return this.Twits; }
	public ArrayList<Twit> getTwits(Account a) { return a.getTwits(); }
	public String printTwitIdentifiers() { 
		String result = "";
		for (int i = 0; i < this.Twits.size(); ++i) result += Twits.get(i).getTwitIdentifier() + ",";
		return result;
	}
	
	public ArrayList<Account> getSubscribedTo() { return this.SubscribedTo; }
	public String printSubscribedTo() { 
		String result = "";
		for (int i = 0; i < this.SubscribedTo.size(); ++i) result += SubscribedTo.get(i).getUserName() + ",";
		return result;
	}
	
	public ArrayList<Account> getSubscribers() { return this.Subscribers; }
	public String printSubscribers() { 
		String result = "";
		for (int i = 0; i < this.Subscribers.size(); ++i) result += Subscribers.get(i).getUserName() + ",";
		return result;
	}
	
	public void subscribe(Account a) { this.SubscribedTo.add(a); }
	public void unSubscribe(Account a) {
		boolean found = false;
		for (int i = 0; i < this.SubscribedTo.size() && !found; ++i) {
			if (this.SubscribedTo.get(i).getUserName() == a.getUserName()) {
				SubscribedTo.remove(i);
				found = true;
			}
		}
	}
	
	public void printAccount() {
		String result = this.getUserName() + ";";
		result += this.getPassword() + ";";
		result += this.getProfileText() + ";";
		result += this.getProfilePhotoPath() + ";";
		if (this.Twits != null) result += this.printTwitIdentifiers() + ";";
		else result += ";";
		if (this.SubscribedTo != null)result += this.printSubscribedTo() + ";";
		else result += ";";
		if (this.Subscribers != null) result += this.printSubscribers() + ";";
		else result += ";";
		System.out.print(result);
	}
	
	Account() {
		this.UserName = "default";
		this.Password = "default";
		this.ProfileText = "";
		this.ProfilePhotoPath = "";
	}
	
	Account(String u, String p) {
		this.UserName = u;
		this.Password = p;
		this.ProfileText = "";
		this.ProfilePhotoPath = "";
	}
}