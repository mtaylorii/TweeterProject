//package writingTester;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/*Copyright (c) 2011 Aravind Rao
Modifications by Sam Barnum, 360Works 2012
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

public final class ASCII {
	boolean negative;

	public ASCII() {
		this(false);
	}

	public ASCII(final boolean negative) {
		this.negative = negative;
	}

	public String convert(final BufferedImage image) {
		StringBuilder sb = new StringBuilder((image.getWidth() + 1) * image.getHeight());
		for (int y = 0; y < image.getHeight(); y++) {
			if (sb.length() != 0) sb.append("\n");
			for (int x = 0; x < image.getWidth(); x++) {
				Color pixelColor = new Color(image.getRGB(x, y));
				double gValue = (double) pixelColor.getRed() * 0.2989 + (double) pixelColor.getBlue() * 0.5870 + (double) pixelColor.getGreen() * 0.1140;
				final char s = negative ? returnStrNeg(gValue) : returnStrPos(gValue);
				sb.append(s);
			}
		}
		return sb.toString();
	}

	/**
	 * Create a new string and assign to it a string based on the grayscale value.
	 * If the grayscale value is very high, the pixel is very bright and assign characters
	 * such as . and , that do not appear very dark. If the grayscale value is very lowm the pixel is very dark,
	 * assign characters such as # and @ which appear very dark.
	 *
	 * @param g grayscale
	 * @return char
	 */
	private char returnStrPos(double g)//takes the grayscale value as parameter
	{
		final char str;

		if (g >= 230.0) {
			str = ' ';
		} else if (g >= 200.0) {
			str = '.';
		} else if (g >= 180.0) {
			str = '*';
		} else if (g >= 160.0) {
			str = ':';
		} else if (g >= 130.0) {
			str = 'o';
		} else if (g >= 100.0) {
			str = '&';
		} else if (g >= 70.0) {
			str = '8';
		} else if (g >= 50.0) {
			str = '#';
		} else {
			str = '@';
		}
		return str; // return the character

	}

	/**
	 * Same method as above, except it reverses the darkness of the pixel. A dark pixel is given a light character and vice versa.
	 *
	 * @param g grayscale
	 * @return char
	 */
	private char returnStrNeg(double g) {
		final char str;

		if (g >= 230.0) {
			str = '@';
		} else if (g >= 200.0) {
			str = '#';
		} else if (g >= 180.0) {
			str = '8';
		} else if (g >= 160.0) {
			str = '&';
		} else if (g >= 130.0) {
			str = 'o';
		} else if (g >= 100.0) {
			str = ':';
		} else if (g >= 70.0) {
			str = '*';
		} else if (g >= 50.0) {
			str = '.';
		} else {
			str = ' ';
		}
		return str;
	}
	
	// http://stackoverflow.com/questions/15558202/how-to-resize-image-in-java shahadeo answered Dec 12 '14 at 14:21
	public BufferedImage resizeImg(BufferedImage img, int newW, int newH)
    {
	    int w = img.getWidth();
	    int h = img.getHeight();
	    BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
	    Graphics2D g = dimg.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
	    g.dispose();
	    return dimg;      
   }

	public void goTime(String s) {
		File f = new File(s);
		try {
			final BufferedImage image = ImageIO.read(f);
			if (image == null) throw new IllegalArgumentException(f + " is not a valid image.");
			BufferedImage imageScaled = image;
			if (image.getWidth() > 100 || image.getHeight() > 50) imageScaled = resizeImg(image, 100, 50);
			final String ascii = new ASCII().convert(imageScaled);
			System.out.println(ascii);
		}
		catch (Exception e) {
			
		}
	}
	
	public String returnString(String s) {
		String result = "";
		File f = new File(s);
		try {
			final BufferedImage image = ImageIO.read(f);
			if (image == null) throw new IllegalArgumentException(f + " is not a valid image.");
			BufferedImage imageScaled = image;
			if (image.getWidth() > 100 || image.getHeight() > 50) imageScaled = resizeImg(image, 100, 50);
			final String ascii = new ASCII().convert(imageScaled);
			result = ascii;
		}
		catch (Exception e) {
		}
		return result;
	}
}
