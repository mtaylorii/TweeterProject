import java.sql.Timestamp;
import java.util.Date;

public class Twit {
	private String TwitText;
	private String TwitPhotoPath;
	private double TwitIdentifier;
	private java.util.Date TwitDate;
        private String TwitPoster;
        private String TwitHashtag;
        private String TwitRecipient;
        // set equal to "anyone" if viewable by anyone, "subscribers" if viewable by subs
        private String TwitViewers;
        
	public String getTwitText() { return this.TwitText; }
	public void setTwitText(String s) { this.TwitText = s; }
	
	public String getTwitPhotoPath() { return this.TwitPhotoPath; }
	public void setTwitPhotoPath(String s) { this.TwitPhotoPath = s; }
	
	public double getTwitIdentifier() { return this.TwitIdentifier; }
	public void setTwitIdentifier(double d) { this.TwitIdentifier = d; }

	public Timestamp getTwitDate() { return new Timestamp(this.TwitDate.getTime()); }
	// need some sort of setter for loading the TwitDates of twits from the twit database
	public void setTwitDate(Timestamp td) { this.TwitDate = td; }
		
        public String getTwitPoster() { return this.TwitPoster; }
        public void setTwitPoster(String p) { this.TwitPoster = p; }

        public String getTwitHashtag() { return this.TwitHashtag; }
        public void setTwitHashtag(String h) { this.TwitHashtag = h; }

        public String getTwitRecipient() { return this.TwitRecipient; }
        public void setTwitRecipient(String r) { this.TwitRecipient = r; }
        
        public String getTwitViewers() { return this.TwitViewers; }
        public void setTwitViewers(String v) { this.TwitViewers = v; }
        
	Twit() {
		this.TwitText = "default";
		this.TwitPhotoPath = " ";
		this.TwitIdentifier = 0.0;
		this.TwitDate = new java.util.Date();
	}
	
	Twit(String t) {
		this.TwitText = t;
		this.TwitPhotoPath = " ";
		this.TwitIdentifier = 0.0;
		this.TwitDate = new java.util.Date();
	}
	
	Twit(String t, String p) {
		this.TwitText = t;
		this.TwitPhotoPath = p;
		this.TwitIdentifier = 0.0;
		this.TwitDate = new java.util.Date();
	}
        
        /**
         * Working idea for a constructor to be used for most all twits posted
         * by users that takes parameters for the twit's text, a photo path for
         * an attached photo, a hashtag, a possible recipient of the twit via
         * the at symbol, and the poster of the twit; if a twit is posted
         * without a picture, hashtag, or recipient, we will put a default value
         * in the parameter when we create the Twit.
         */
        Twit(String t, String p, String h, String r, String tp) {
                this.TwitText = t;
                this.TwitPhotoPath = " ";
                this.TwitIdentifier = 0.0;
                this.TwitDate = new java.util.Date();
                this.TwitHashtag = h;
                this.TwitRecipient = r;
                this.TwitPoster = tp;
	}
	
	public void loadTwit(String s) {
		String delimiter = “[;]+”;
		String[] d = s.split(delimiter);
		Account tempAccount = new Account(d[0]);
		Twit tempTwit = new Twit(d[1]);
		System.out.println("Array length: " + d.length);
		if (!(d[0].equals("~"))) tempTwit.setTwitPoster(d[0]); 
		if (!(d[2].equals("~"))) tempTwit.setTwitPhotoPath(d[2]); 
		if (!(d[3].equals("~"))) tempTwit.setTwitIdentifier(d[3]); 
		if (!(d[4].equals("~"))) tempTwit.setTwitDate(d[4]); 
		if (!(d[5].equals("~"))) tempTwit.setTwitHashtag(d[5]); 
		if (!(d[6].equals("~"))) tempTwit.setTwitRecipient(d[6]);
		if (!(d[7].equals("~"))) tempTwit.setTwitViewers(d[7]);
		// how to reference list of accounts
		for (int i = 0; i < accounts.size(); ++i) {
		// Search through list of accounts and find account that matches the temp account username;
		// If account usernames match, add the tempTwit to that account's list of Twits;
			if (tempAccount.getUserName == accounts.get(i).getUserName) {
			accounts.get(i).Twits.add(tempTwit);
			}
		}
	}
}
