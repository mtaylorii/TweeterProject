package writingTester;

public class Twit {
	private String TwitAuthor;
	private String TwitText;
	private String TwitPhotoPath;
	private double TwitIdentifier;
	private boolean TwitVisibility;
	
	public String getTwitText() { return this.TwitText; }
	public void setTwitText(String s) { this.TwitText = s; }
	
	public String getTwitPhotoPath() { return this.TwitPhotoPath; }
	public void setTwitPhotoPath(String s) { this.TwitPhotoPath = s; }
	
	public double getTwitIdentifier() { return this.TwitIdentifier; }
	public void setTwitIdentifier(double d) { this.TwitIdentifier = d; }
	
	public boolean getTwitVisibility() { return this.TwitVisibility; }
	public void setTwitVisibility(boolean b) { this.TwitVisibility = b; }
	
	public String getTwitAuthor() { return this.TwitAuthor; }
	public void setTwitAuthor(String s) { this.TwitAuthor = s; }
	
	public boolean hasTerm(String s) { return this.TwitText.contains(s); }
	
	public String toString() {
		String result = "";
		result += this.TwitIdentifier + ";"
				+ this.TwitText + ";"
				+ this.TwitPhotoPath + ";"
				+ this.TwitVisibility + ";";
		return result;
	}
	
	public String toString2() {
		String result = "INFO:";
		result += this.TwitIdentifier + ";"
				+ this.TwitText + ";"
				+ this.TwitVisibility + ";\n"
				+ this.TwitPhotoPath + "\n;";
		return result;
	}
	
	public void printTwit() {
		System.out.println(this.TwitText);
		ASCII img = new ASCII();
		img.goTime(this.TwitPhotoPath);
	}
	
	public void printTwit2() {
		System.out.println(this.TwitText);
		System.out.println(this.TwitPhotoPath);
	}
	
	Twit() {
		this.TwitText = "~";
		this.TwitPhotoPath = "~";
		this.TwitAuthor = "~";
		this.TwitVisibility = true;
	}
	
	Twit(String t) {
		this.TwitText = t;
		this.TwitPhotoPath = "~";
		this.TwitAuthor = "~";
		this.TwitVisibility = true;
	}
	
	Twit(String t, String p) {
		this.TwitText = t;
		this.TwitPhotoPath = p;
		this.TwitAuthor = "~";
		this.TwitVisibility = true;
	}
}