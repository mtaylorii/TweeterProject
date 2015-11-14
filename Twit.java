import java.sql.Timestamp;
import java.util.Date;

public class Twit {
	private String TwitText;
	private String TwitPhotoPath;
	private double TwitIdentifier;
	private java.util.Date TwitDate;
	
	public String getTwitText() { return this.TwitText; }
	public void setTwitText(String s) { this.TwitText = s; }
	
	public String getTwitPhotoPath() { return this.TwitPhotoPath; }
	public void setTwitPhotoPath(String s) { this.TwitPhotoPath = s; }
	
	public double getTwitIdentifier() { return this.TwitIdentifier; }
	public void setTwitIdentifier(double d) { this.TwitIdentifier = d; }

	public Timestamp getTwitDate() { return new Timestamp(this.TwitDate.getTime()); }
	
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
}