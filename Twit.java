public class Twit {
	private String TwitText;
	private String TwitPhotoPath;
	private double TwitIdentifier;
	
	public String getTwitText() { return this.TwitText; }
	public void setTwitText(String s) { this.TwitText = s; }
	
	public String getTwitPhotoPath() { return this.TwitPhotoPath; }
	public void setTwitPhotoPath(String s) { this.TwitPhotoPath = s; }
	
	public double getTwitIdentifier() { return this.TwitIdentifier; }
	public void setTwitIdentifier(double d) { this.TwitIdentifier = d; }
	
	Twit() {
		this.TwitText = "default";
		this.TwitPhotoPath = " ";
	}
	
	Twit(String t) {
		this.TwitText = t;
		this.TwitPhotoPath = " ";
	}
	
	Twit(String t, String p) {
		this.TwitText = t;
		this.TwitPhotoPath = p;
	}
}