import java.util.ArrayList;

public class Account {
	private String UserName;
	private String Password;
	private String ProfileText;
	private String ProfilePhotoPath;
	private ArrayList<Twit> Twits = new ArrayList<Twit>();
	private ArrayList<Account> SubscribedTo = new ArrayList<Account>();
	private ArrayList<Account> Subscribers = new ArrayList<Account>();
	
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
		for (int i = 0; i < this.Twits.size(); ++i) {
			if (i > 0) result += ",";
			result += Twits.get(i).getTwitIdentifier();
		}
		return result;
	}
	
	public ArrayList<Account> getSubscribedTo() { return this.SubscribedTo; }
	public String printSubscribedTo() { 
		String result = "";
		for (int i = 0; i < this.SubscribedTo.size(); ++i) {
			if (i > 0) result += ",";
			result += SubscribedTo.get(i).getUserName();
		}
		return result;
	}
	
	public ArrayList<Account> getSubscribers() { return this.Subscribers; }
	public String printSubscribers() { 
		String result = "";
		for (int i = 0; i < this.Subscribers.size(); ++i) {
			if (i > 0) result += ",";
			result += Subscribers.get(i).getUserName();
		}
		return result;
	}
	
	public void subscribe(Account a) { 
		this.SubscribedTo.add(a);
		a.Subscribers.add(this);
	}
	
	public void unSubscribe(Account a) {
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
		result += "\n";
		System.out.print(result);
	}
	
	public String toString() {
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
		result += "\n";
		return result;
	}
	
	Account() {
		this.UserName = "default";
		this.Password = "default";
		this.ProfileText = "";
		this.ProfilePhotoPath = "";
	}
	
	Account(String u) {
		this.UserName = u;
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
	
	Account(String u, String p, String pt, String ppp, String t, String pst, String ps) {
		this.UserName = u;
		this.Password = p;
		this.ProfileText = pt;
		this.ProfilePhotoPath = ppp;
		String delimiter = "[,]+";
		String[] delimited = t.split(delimiter);
		for (int i = 0; i < delimited.length; ++i) {
			Twit tempTwit = new Twit();
			double tempDouble = Double.parseDouble(delimited[i]);
			tempTwit.setTwitIdentifier(tempDouble);
			this.Twits.add(tempTwit);
		}
		delimiter = "[,]+";
		delimited = null;
		delimited = pst.split(delimiter);
		for (int i = 0; i < delimited.length; ++i) {
			Account tempAccount = new Account();
			String tempString = delimited[i];
			tempAccount.setUserName(tempString);
			this.SubscribedTo.add(tempAccount);
		}
		delimiter = "[,]+";
		delimited = null;
		delimited = ps.split(delimiter);
		for (int i = 0; i < delimited.length; ++i) {
			Account tempAccount = new Account();
			String tempString = delimited[i];
			tempAccount.setUserName(tempString);
			this.Subscribers.add(tempAccount);
		}
	}
}