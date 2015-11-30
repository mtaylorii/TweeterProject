package writingTester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	
	public static String printHeader(String s) {
		String header = "";
		String headerText = s;
		for (int i = 0; i < 100 - headerText.length(); ++i) {
			header += "*";
			if (i == (100 - headerText.length())/2) header += headerText;
		}
		return header;
	}

	public static void main(String[] args) throws IOException{
		AccountRepository repo = new AccountRepository();
		repo.loadEverything();
		
		ASCII img = new ASCII();
		img.goTime("tweeter.png");
		
		String userOption = "";
		int convertedOption = -1;
		Scanner userInput = new Scanner(System.in);
		boolean isValid = false;
		int timesPrompted = 0;
		ArrayList<String> menuOptions = new ArrayList<String>();
		do {
			menuOptions.clear();
			String SignedOutMenu[] = new String[]{"Sign in", 
					"Register", "View public twits", "View profiles",
					"Search", "Exit"};
			String SignedInMenu[] = new String[]{"Sign out", 
					"View all twits", "View subscribed to twits",
					"View profiles", "View subscribers", "view subscribed to", "Post twit", "Subscribe", "Unsubscribe", "Search",
					"Edit profile", "Exit"};
			if (repo.getSignedIn()) for (int i = 0; i < SignedInMenu.length; ++i) menuOptions.add(SignedInMenu[i]);
			else for (int i = 0; i < SignedOutMenu.length; ++i) menuOptions.add(SignedOutMenu[i]);
			isValid = false;
			timesPrompted = 0;
			userOption = "";
			convertedOption = -1;
			while (!isValid) {
				userOption = "";
				convertedOption = -1;
				if (timesPrompted > 0) System.out.println(printHeader("Incorrect option"));
				String header = "";
				String headerText = " Please enter an option: ";
				for (int i = 0; i < 100 - headerText.length(); ++i) {
					header += "*";
					if (i == (100 - headerText.length())/2) header += headerText;
				}
				System.out.println(printHeader(" Please enter an option: "));
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
			if (!(repo.getSignedIn())) {
				if (convertedOption == 0) {
					img.goTime("signin.png");
					repo.signIn();
				}
				else if (convertedOption == 1) {
					img.goTime("register.png");
					repo.register();
					repo.saveEverything();
				}
				else if (convertedOption == 2) {
					img.goTime("publictwits.png");
					repo.viewPublicTwits2();
				}
				else if (convertedOption == 3) {
					img.goTime("profiles.png");
					repo.printAccountProfiles2();
				}
				else if (convertedOption == 4) {
					img.goTime("search.png");
					repo.search();
				}
			}
			else {
				if (convertedOption == 0) {
					img.goTime("signout.png");
					repo.signOut();
				}
				else if (convertedOption == 1) {
					img.goTime("alltwits.png");
					repo.viewAllTwits2();
				}
				else if (convertedOption == 2) {
					img.goTime("subscribertwits.png");
					repo.viewSubscribedToTwits2();
				}
				else if (convertedOption == 3) {
					img.goTime("profiles.png");
					repo.printAccountProfiles2();
				}
				else if (convertedOption == 4) {
					img.goTime("subscribers.png");
					repo.viewSubscribers2();
				}
				else if (convertedOption == 5) {
					img.goTime("subscribedto.png");
					repo.viewSubscribedTo2();
				}
				else if (convertedOption == 6) {
					img.goTime("posttwit.png");
					repo.getActiveAccount().postTwit2();
					repo.saveEverything();
				}
				else if (convertedOption == 7) {
					img.goTime("subscribe.png");
					repo.subscribe();
					repo.saveEverything();
				}
				else if (convertedOption == 8) {
					img.goTime("unsubscribe.png");
					repo.unsubscribe();
					repo.saveEverything();
				}
				else if (convertedOption == 9) {
					img.goTime("search.png");
					repo.search();
				}
				else if (convertedOption == 10) {
					img.goTime("edit.png");
					repo.getActiveAccount().editProfile2();
					repo.saveEverything();
				}
			}
			if (convertedOption == menuOptions.size()-1) repo.saveEverything();
		} while (!(convertedOption == menuOptions.size()-1));
		img.goTime("exit.png");
	}
}