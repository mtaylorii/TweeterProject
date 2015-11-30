//package writingTester;

/**
 * Class Twit stores all of the data of a twit that a user might create.
 */

public class Twit {
	private String TwitAuthor; //the username of the account that created the Twit
	private String TwitText; //the text of the twit
	private String TwitPhotoPath; //the path of the photo
	private double TwitIdentifier; //a unique number for each twit per account that is used to identify an accounts twits
	private boolean TwitVisibility; //true = publicly viewable, false = viewable to subscribers only

	/**
	 * Accesor method for TwitText.
	 * @return the text of the twit
	 */
	public String getTwitText() { return this.TwitText; }

	/**
	 * Changes the TwitText.
	 * @param s the given text to be set
	 */
	public void setTwitText(String s) { this.TwitText = s; }

	/**
	 * Accessor method for TwitPhotoPath.
	 * @return the path of a twit's photo
	 */
	public String getTwitPhotoPath() { return this.TwitPhotoPath; }

	/**
	 * Changes the TwitPhotoPath.
	 * @param s the path of the photo to be set
	 */
	public void setTwitPhotoPath(String s) { this.TwitPhotoPath = s; }

	/**
	 * Accessor method for TwitIdentifier.
	 * @return the identifying number of a twit
	 */
	public double getTwitIdentifier() { return this.TwitIdentifier; }

	/**
	 * Changes the TwitIdentifier.
	 * @param d the identifying number to be set
	 */
	public void setTwitIdentifier(double d) { this.TwitIdentifier = d; }

	/**
	 * Accessor method for TwitVisibility.
	 * @return the visibility of the twit
	 */
	public boolean getTwitVisibility() { return this.TwitVisibility; }

	/**
	 * Changes the TwitVisibility.
	 * @param b the visibility to be set
	 */
	public void setTwitVisibility(boolean b) { this.TwitVisibility = b; }

	/**
	 * Accessor method for TwitAuthor.
	 * @return the author of the twit
	 */
	public String getTwitAuthor() { return this.TwitAuthor; }

	/**
	 * Changes the TwitAuthor.
	 * @param s the author to be set
	 */
	public void setTwitAuthor(String s) { this.TwitAuthor = s; }

	/**
	 * Checks to see if the TwitText contains a given string of characters.
	 * @param s the string to be searched for
	 * @return true if TwitText contains s, false if not
	 */
	public boolean hasTerm(String s) { return this.TwitText.contains(s); }

	/**
	 * Method to turn all of the data of a twit into a string.
	 * @return the data members of a twit in a string format
	 */
	public String toString() {
		String result = "";
		result += this.TwitIdentifier + ";"
				+ this.TwitText + ";"
				+ this.TwitPhotoPath + ";"
				+ this.TwitVisibility + ";";
		return result;
	}

	/**
	 * Method to turn all of the data of a twit into a string with "INFO:" at the beginning.
	 * @return the data members of a twit in a string format with "INFO:" at the beginning
	 */
	public String toString2() {
		String result = "INFO:";
		result += this.TwitIdentifier + ";"
				+ this.TwitText + ";"
				+ this.TwitVisibility + ";\n"
				+ this.TwitPhotoPath + "\n;";
		return result;
	}

	/**
	 * Prints the TwitText and then the photo of a twit in the form of ASCII characters.
	 * @return TwitText and photo in ASCII characters
	 */
	public void printTwit() {
		System.out.println(this.TwitText);
		ASCII img = new ASCII();
		img.goTime(this.TwitPhotoPath);
	}

	/**
	 * Prints the TwitText and then the path of the photo of the twit.
	 * @return TwitText and photo path
	 */
	public void printTwit2() {
		System.out.println(this.TwitText);
		System.out.println(this.TwitPhotoPath);
	}

	/**
	 * Default constructor for a twit.
	 */
	Twit() {
		this.TwitText = "~";
		this.TwitPhotoPath = "~";
		this.TwitAuthor = "~";
		this.TwitVisibility = true;
	}

}
