package writingTester;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public void postTwit(Twit t) { this.Twits.add(t); }
	
	public void postTwit(/*Twit t*/) {
		Twit t = new Twit();
		String userOption = "";
		int convertedOption = -1;
		Scanner userInput = new Scanner(System.in);
		boolean isValid = false;
		int timesPrompted = 0;
		ArrayList<String> menuOptions = new ArrayList<String>();
		String menu[] = new String[]{"Edit twit text", "Edit twit picture", "Edit twit visibility", "Post twit", "Back to main menu"};
		for (int i = 0; i < menu.length; ++i) menuOptions.add(menu[i]);
		
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
		
		String imageFileName = Double.toString(newTwitIdentifier);
		
		do {
			timesPrompted = 0;
			isValid = false;
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
			if (convertedOption == 0) {
				System.out.println(printHeader("Please enter the twit text:"));
				System.out.print(">>> ");
				userInput.nextLine();
				userOption = userInput.nextLine();
				t.setTwitText(userOption);
			}
			else if (convertedOption == 1) {
				System.out.println(printHeader("Please enter the twit picture file path:"));
				System.out.print(">>> ");
				userOption = userInput.next();
				t.setTwitPhotoPath(userOption);
			}
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
			else if (convertedOption == 3) {
				if (t.getTwitText().equals("~")) System.out.println(printHeader("Can not post an empty twit"));
				else {
					InputStream is = null;
			        OutputStream os = null;
			        String newFile = "";
			        String filePath = t.getTwitPhotoPath();
			        try {
			            is = new FileInputStream(new File(filePath));
			            String fileExtension = filePath.substring(filePath.length()-4);
			            System.out.println("FILE EXT: " + fileExtension);
			            System.out.println("FILE NAME: " + Double.toString(t.getTwitIdentifier()));
			            newFile = this.getUserName() + Double.toString(t.getTwitIdentifier()) + fileExtension;
			            t.setTwitPhotoPath(newFile);
			            os = new FileOutputStream(new File(newFile));
			            byte[] buffer = new byte[2048];
			            int length;
			            while ((length = is.read(buffer)) > 0) {
			                os.write(buffer, 0, length);
			            }
			            is.close();
			            os.close();
			        }
			          catch (IOException e) {
			        	  //System.out.println(e);
			        } finally {
			        }
					Twits.add(t);
				}
			}
		} while(!(convertedOption == menuOptions.size()-1 || convertedOption == menuOptions.size()-2));
	}
	
	public void postTwit2(/*Twit t*/) {
		Twit t = new Twit();
		String userOption = "";
		int convertedOption = -1;
		Scanner userInput = new Scanner(System.in);
		boolean isValid = false;
		int timesPrompted = 0;
		ArrayList<String> menuOptions = new ArrayList<String>();
		String menu[] = new String[]{"Edit twit text", "Edit twit picture", "Edit twit visibility", "Post twit", "Back to main menu"};
		for (int i = 0; i < menu.length; ++i) menuOptions.add(menu[i]);
		
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
		
		String imageFileName = Double.toString(newTwitIdentifier);
		
		do {
			timesPrompted = 0;
			isValid = false;
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
			if (convertedOption == 0) {
				System.out.println(printHeader("Please enter the twit text:"));
				System.out.print(">>> ");
				userInput.nextLine();
				userOption = userInput.nextLine();
				t.setTwitText(userOption);
			}
			else if (convertedOption == 1) {
				System.out.println(printHeader("Please enter the twit picture file path:"));
				System.out.print(">>> ");
				userOption = userInput.next();
				ASCII img = new ASCII();
				String asciiImg = img.returnString(userOption);
				if (asciiImg.equals("")) asciiImg = "~";
				t.setTwitPhotoPath(asciiImg);
			}
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
			else if (convertedOption == 3) {
				if (t.getTwitText().equals("~")) System.out.println(printHeader("Can not post an empty twit"));
				else Twits.add(t);
			}
		} while(!(convertedOption == menuOptions.size()-1 || convertedOption == menuOptions.size()-2));
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
	
	public void printAccount() {
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
		System.out.println(result);
	}
	
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
	
	public void processTwits(String s) {
		String delimiter = "[,]+";
		String[] delimited = s.split(delimiter);
		for (int i = 0; i < delimited.length; ++i) {
			Twit tempTwit = new Twit();
			double tempDouble = Double.parseDouble(delimited[i]);
			tempTwit.setTwitIdentifier(tempDouble);
			this.Twits.add(tempTwit);
		}
	}
	
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
	
	public void editProfile() {
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
				InputStream is = null;
		        OutputStream os = null;
		        String newFile = "";
		        try {
		            is = new FileInputStream(new File(userOption));
		            String fileExtension = userOption.substring(userOption.length()-4);
		            System.out.println("FILE EXT: " + fileExtension);
		            newFile = this.getUserName() + fileExtension;
		            os = new FileOutputStream(new File(newFile));
		            byte[] buffer = new byte[2048];
		            int length;
		            while ((length = is.read(buffer)) > 0) {
		                os.write(buffer, 0, length);
		            }
		            is.close();
		            os.close();
		        }
		          catch (IOException e) {
		        	  System.out.println(e);
		        } finally {
		        }
				this.setProfilePhotoPath(newFile);
			}
	}
	
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